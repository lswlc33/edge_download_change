#!/usr/bin/env bash
# Build the LSPosed module APK without Gradle, using only the Android SDK build-tools.
#
# Requirements: JDK 17, Android SDK (platforms;android-35, build-tools;35.0.0), python3.
# Usage:        bash build.sh
#
# Signing (env vars, optional - defaults suit local builds):
#   SIGNING_KEYSTORE_BASE64  base64 encoded keystore (used by CI secrets)
#   SIGNING_KEYSTORE_FILE    path to a keystore file (default: module.keystore)
#   SIGNING_STORE_PASSWORD   keystore password      (default: edgesysdl)
#   SIGNING_KEY_ALIAS        key alias              (default: edgesysdl)
#   SIGNING_KEY_PASSWORD     key password           (default: edgesysdl)
# Artifact name: <APP>-<versionName><APK_SUFFIX>.apk
set -e
cd "$(dirname "$0")"

APP="edge_download_change"
VER="$(grep -o 'android:versionName="[^"]*"' AndroidManifest.xml | head -1 | cut -d'"' -f2)"
SUFFIX="${APK_SUFFIX:-}"
OUT="${APP}-${VER}${SUFFIX}.apk"

# ---------------------------------------------------------------- Android SDK
if [ -n "$ANDROID_SDK_ROOT" ]; then SDK="$ANDROID_SDK_ROOT"
elif [ -n "$ANDROID_HOME" ]; then SDK="$ANDROID_HOME"
elif [ -n "$LOCALAPPDATA" ]; then SDK="$(cygpath -m "$LOCALAPPDATA")/Android/Sdk"
elif [ -d "$HOME/Android/Sdk" ]; then SDK="$HOME/Android/Sdk"
elif [ -d "$HOME/Library/Android/sdk" ]; then SDK="$HOME/Library/Android/sdk"
else echo "error: Android SDK not found (set ANDROID_SDK_ROOT)" >&2; exit 1; fi

case "$(uname -s)" in
  MINGW*|MSYS*|CYGWIN*) EXE=".exe"; CP_SEP=";" ;;
  *) EXE=""; CP_SEP=":" ;;
esac

BT_VER="${BUILD_TOOLS_VERSION:-35.0.0}"
PLATFORM="${ANDROID_PLATFORM:-android-35}"
BT="$SDK/build-tools/$BT_VER"
AJ="$SDK/platforms/$PLATFORM/android.jar"
[ -x "$BT/aapt2$EXE" ] || { echo "error: build-tools $BT_VER not found in $SDK" >&2; exit 1; }

# ---------------------------------------------------------------- keystore
KS_FILE="${SIGNING_KEYSTORE_FILE:-module.keystore}"
TMP_KS=""
if [ -n "$SIGNING_KEYSTORE_BASE64" ]; then
  TMP_KS="$(mktemp -t edc-keystore.XXXXXX)"
  printf '%s' "$SIGNING_KEYSTORE_BASE64" | base64 -d > "$TMP_KS"
  KS_FILE="$TMP_KS"
fi
KS_PASS="${SIGNING_STORE_PASSWORD:-edgesysdl}"
KEY_ALIAS="${SIGNING_KEY_ALIAS:-edgesysdl}"
KEY_PASS="${SIGNING_KEY_PASSWORD:-edgesysdl}"

rm -rf build
mkdir -p build/apistub build/gen build/classes build/dex

echo "== 1/6 compile libxposed API stub (compile-only) =="
javac -encoding UTF-8 --release 17 -nowarn -cp "$AJ" -d build/apistub \
  $(find xposed_api/src xposed_api/annstub -name '*.java')

echo "== 2/6 aapt2 compile resources =="
"$BT/aapt2$EXE" compile --dir res -o build/res.zip

echo "== 3/6 aapt2 link =="
"$BT/aapt2$EXE" link -o build/base-unsigned.apk \
  -I "$AJ" \
  --manifest AndroidManifest.xml \
  -R build/res.zip \
  --java build/gen \
  --min-sdk-version 26 --target-sdk-version 35 \
  --auto-add-overlay

echo "== 4/6 javac =="
javac -encoding UTF-8 --release 8 -nowarn \
  -cp "$AJ${CP_SEP}build/apistub" \
  -d build/classes \
  $(find src build/gen -name '*.java')

echo "== 5/6 d8 dex =="
java -cp "$BT/lib/d8.jar" com.android.tools.r8.D8 \
  --release --min-api 26 --lib "$AJ" \
  --output build/dex \
  $(find build/classes -name '*.class')

echo "== 6/6 package =="
python package_apk.py

"$BT/zipalign$EXE" -f -p 4 build/module-unsigned.apk build/module-aligned.apk

if [ ! -f "$KS_FILE" ]; then
  # Local self-signed key so releases can be installed over each other.
  # CI uses the base64 secret instead; keep this file (and back it up) for local builds.
  keytool -genkeypair -keystore "$KS_FILE" -storepass "$KS_PASS" -keypass "$KEY_PASS" \
    -alias "$KEY_ALIAS" -keyalg RSA -keysize 2048 -validity 10950 \
    -dname "CN=$APP, O=Local, C=CN" > /dev/null 2>&1
  echo "note: generated a new keystore at $KS_FILE"
fi

java -jar "$BT/lib/apksigner.jar" sign \
  --ks "$KS_FILE" --ks-pass "pass:$KS_PASS" --key-pass "pass:$KEY_PASS" \
  --out "$OUT" build/module-aligned.apk
java -jar "$BT/lib/apksigner.jar" verify "$OUT"

[ -n "$TMP_KS" ] && rm -f "$TMP_KS"
echo "done: $OUT"
