package io.github.lswlc33.edge_download_change;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Visual system for the module app: a deliberately dark, GitHub-inspired look with
 * rounded cards, pill badges and two button styles. Everything is drawn with
 * GradientDrawable/RippleDrawable so no image assets or libraries are needed.
 */
final class UiKit {

    private static final int CARD = 0xFF161B22;
    private static final int STROKE = 0xFF30363D;
    private static final int TEXT = 0xFFE6EDF3;
    private static final int TEXT_DIM = 0xFF8B949E;
    private static final int ACCENT = 0xFF4493F8;
    private static final int ACCENT_DEEP = 0xFF1F6FEB;
    private static final int OK = 0xFF3FB950;
    private static final int BAD = 0xFFF85149;
    private static final int BTN = 0xFF21262D;

    private UiKit() {}

    static int dp(Context context, float value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }

    static int color(Context context, int resId) {
        return context.getResources().getColor(resId, context.getTheme());
    }

    private static GradientDrawable round(Context context, int fillColor, int radiusDp, int strokeColor) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(fillColor);
        d.setCornerRadius(dp(context, radiusDp));
        if (strokeColor != 0) {
            d.setStroke(dp(context, 1), strokeColor);
        }
        return d;
    }

    static LinearLayout content(Context context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(dp(context, 16), dp(context, 8), dp(context, 16), dp(context, 24));
        return layout;
    }

    static LinearLayout card(Context context) {
        LinearLayout card = new LinearLayout(context);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setBackground(round(context, color(context, R.color.card), 20, color(context, R.color.stroke)));
        card.setPadding(dp(context, 18), dp(context, 16), dp(context, 18), dp(context, 16));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = dp(context, 12);
        card.setLayoutParams(params);
        return card;
    }

    /** Small uppercase section header with letter spacing. */
    static TextView sectionTitle(Context context, String text) {
        TextView view = new TextView(context);
        view.setText(text);
        view.setAllCaps(true);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        view.setTypeface(Typeface.DEFAULT_BOLD);
        view.setLetterSpacing(0.08f);
        view.setTextColor(color(context, R.color.accent));
        view.setPadding(0, 0, 0, dp(context, 10));
        return view;
    }

    static TextView title(Context context, String text) {
        TextView view = new TextView(context);
        view.setText(text);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        view.setTextColor(TEXT);
        return view;
    }

    static TextView body(Context context, String text) {
        TextView view = new TextView(context);
        view.setText(text);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        view.setTextColor(TEXT);
        view.setLineSpacing(dp(context, 3), 1f);
        return view;
    }

    static TextView hint(Context context, String text) {
        TextView view = body(context, text);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        view.setTextColor(TEXT_DIM);
        return view;
    }

    static View divider(Context context) {
        View view = new View(context);
        view.setBackgroundColor(STROKE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, Math.max(1, dp(context, 1)));
        params.topMargin = dp(context, 14);
        params.bottomMargin = dp(context, 14);
        view.setLayoutParams(params);
        return view;
    }

    /** Status pill: tinted rounded background, colored dot and text. */
    static LinearLayout pill(Context context, String text, int statusColor) {
        LinearLayout pill = new LinearLayout(context);
        pill.setOrientation(LinearLayout.HORIZONTAL);
        pill.setGravity(Gravity.CENTER_VERTICAL);
        int tinted = Color.argb(36, Color.red(statusColor), Color.green(statusColor), Color.blue(statusColor));
        GradientDrawable bg = round(context, tinted, 999, withAlpha(statusColor, 0.45f));
        pill.setBackground(bg);
        pill.setPadding(dp(context, 10), dp(context, 4), dp(context, 10), dp(context, 4));

        View dot = new View(context);
        GradientDrawable dotBg = new GradientDrawable();
        dotBg.setShape(GradientDrawable.OVAL);
        dotBg.setColor(statusColor);
        dot.setBackground(dotBg);
        LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(dp(context, 8), dp(context, 8));
        dotParams.rightMargin = dp(context, 6);
        dot.setLayoutParams(dotParams);
        pill.addView(dot);

        TextView label = new TextView(context);
        label.setText(text);
        label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        label.setTypeface(Typeface.DEFAULT_BOLD);
        label.setTextColor(statusColor);
        pill.addView(label);
        return pill;
    }

    /** Rounded gradient monogram tile used in the header. */
    static TextView monogram(Context context) {
        TextView view = new TextView(context);
        view.setText("EDC");
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        view.setTypeface(Typeface.DEFAULT_BOLD);
        view.setTextColor(Color.WHITE);
        view.setGravity(Gravity.CENTER);
        GradientDrawable bg = new GradientDrawable();
        bg.setOrientation(GradientDrawable.Orientation.TL_BR);
        bg.setColors(new int[] {ACCENT, ACCENT_DEEP});
        bg.setCornerRadius(dp(context, 14));
        view.setBackground(bg);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp(context, 48), dp(context, 48));
        params.rightMargin = dp(context, 12);
        view.setLayoutParams(params);
        return view;
    }

    // ------------------------------------------------------------------ buttons

    private static void styleButton(Context context, Button button, boolean primary) {
        button.setAllCaps(false);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        button.setTypeface(Typeface.DEFAULT_BOLD);
        button.setMinHeight(0);
        button.setMinWidth(0);
        button.setPadding(dp(context, 12), dp(context, 9), dp(context, 12), dp(context, 9));
        GradientDrawable bg = round(context,
                primary ? color(context, R.color.accent_deep) : BTN, 10,
                primary ? 0 : STROKE);
        button.setTextColor(primary ? Color.WHITE : TEXT);
        RippleDrawable ripple = new RippleDrawable(
                ColorStateList.valueOf(withAlpha(Color.WHITE, 0.12f)), bg, bg);
        button.setBackground(ripple);
        button.setStateListAnimator(null);
    }

    static Button button(Context context, String text, boolean primary) {
        Button button = new Button(context);
        restyle(context, button, primary);
        button.setText(text);
        return button;
    }

    /** Restyles an existing button (e.g. when the activation state changes). */
    static void restyle(Context context, Button button, boolean primary) {
        styleButton(context, button, primary);
    }

    static LinearLayout buttonRow(Context context, View... buttons) {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setPadding(0, dp(context, 12), 0, dp(context, 10));
        for (View button : buttons) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            params.rightMargin = dp(context, 8);
            button.setLayoutParams(params);
            row.addView(button);
        }
        if (buttons.length > 0) {
            ((LinearLayout.LayoutParams) buttons[buttons.length - 1].getLayoutParams()).rightMargin = 0;
        }
        return row;
    }

    /** Terminal-like panel background for the log view. */
    static void terminalBg(Context context, View view) {
        view.setBackground(round(context, 0xFF0A0D12, 16, color(context, R.color.stroke)));
    }

    /**
     * Adds the system-bar insets to a view's padding.
     *
     * Apps targeting SDK 35 are drawn edge-to-edge on Android 15+, so without this the
     * content would slide under the status bar (and the navigation bar at the bottom).
     */
    static void applySystemBarPadding(final View view, final int left, final int top,
            final int right, final int bottom) {
        final Context context = view.getContext();
        view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                int insetLeft;
                int insetTop;
                int insetRight;
                int insetBottom;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    android.graphics.Insets bars = insets.getInsets(WindowInsets.Type.systemBars());
                    insetLeft = bars.left;
                    insetTop = bars.top;
                    insetRight = bars.right;
                    insetBottom = bars.bottom;
                } else {
                    insetLeft = insets.getSystemWindowInsetLeft();
                    insetTop = insets.getSystemWindowInsetTop();
                    insetRight = insets.getSystemWindowInsetRight();
                    insetBottom = insets.getSystemWindowInsetBottom();
                }
                v.setPadding(dp(context, left) + insetLeft, dp(context, top) + insetTop,
                        dp(context, right) + insetRight, dp(context, bottom) + insetBottom);
                return insets;
            }
        });
        view.requestApplyInsets();
    }

    /** Square tappable glyph button (used for the log page's back arrow). */
    static TextView iconButton(Context context, String glyph) {
        TextView view = new TextView(context);
        view.setText(glyph);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        view.setTextColor(TEXT);
        view.setGravity(Gravity.CENTER);
        view.setMinWidth(dp(context, 44));
        view.setMinHeight(dp(context, 44));
        view.setPadding(dp(context, 8), 0, dp(context, 8), 0);
        view.setClickable(true);
        view.setFocusable(true);
        GradientDrawable bg = round(context, BTN, 12, 0);
        view.setBackground(new RippleDrawable(
                ColorStateList.valueOf(withAlpha(Color.WHITE, 0.12f)), bg, bg));
        return view;
    }

    // ------------------------------------------------------------------ misc

    static int withAlpha(int color, float alpha) {
        return Color.argb(Math.round(alpha * 255), Color.red(color), Color.green(color), Color.blue(color));
    }
}
