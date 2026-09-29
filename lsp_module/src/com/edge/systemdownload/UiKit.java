package com.edge.systemdownload;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/** Small helpers for building a themed, card based UI without any library dependency. */
final class UiKit {

    private UiKit() {}

    static int dp(Context context, float value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }

    /** Resolves a theme attribute color, falling back to the given default. */
    static int themeColor(Context context, int attr, int fallback) {
        TypedValue value = new TypedValue();
        if (!context.getTheme().resolveAttribute(attr, value, true)) return fallback;
        if (value.type >= TypedValue.TYPE_FIRST_COLOR_INT
                && value.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            return value.data;
        }
        if (value.resourceId != 0) {
            // Theme text colors are usually ColorStateLists, not plain colors.
            try {
                android.content.res.ColorStateList list = context.getResources()
                        .getColorStateList(value.resourceId, context.getTheme());
                if (list != null) return list.getDefaultColor();
            } catch (Throwable ignored) {
            }
            try {
                return context.getResources().getColor(value.resourceId);
            } catch (Throwable ignored) {
            }
        }
        return fallback;
    }

    static int textPrimary(Context context) {
        return themeColor(context, android.R.attr.textColorPrimary, Color.BLACK);
    }

    static int textSecondary(Context context) {
        return themeColor(context, android.R.attr.textColorSecondary, Color.GRAY);
    }

    static int accent(Context context) {
        return themeColor(context, android.R.attr.colorAccent, 0xFF0F6CBD);
    }

    static int cardBackground(Context context) {
        int floating = themeColor(context, android.R.attr.colorBackgroundFloating, 0xFFF2F2F2);
        return floating;
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
        GradientDrawable background = new GradientDrawable();
        background.setColor(cardBackground(context));
        background.setCornerRadius(dp(context, 14));
        background.setStroke(dp(context, 1), blend(cardBackground(context), textSecondary(context), 0.25f));
        card.setBackground(background);
        card.setPadding(dp(context, 16), dp(context, 14), dp(context, 16), dp(context, 14));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = dp(context, 12);
        card.setLayoutParams(params);
        return card;
    }

    static TextView sectionTitle(Context context, String text) {
        TextView view = new TextView(context);
        view.setText(text);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        view.setTextColor(accent(context));
        view.setPadding(0, 0, 0, dp(context, 8));
        return view;
    }

    static TextView title(Context context, String text) {
        TextView view = new TextView(context);
        view.setText(text);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        view.setTextColor(textPrimary(context));
        return view;
    }

    static TextView body(Context context, String text) {
        TextView view = new TextView(context);
        view.setText(text);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        view.setTextColor(textPrimary(context));
        view.setLineSpacing(dp(context, 3), 1f);
        return view;
    }

    static TextView hint(Context context, String text) {
        TextView view = body(context, text);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        view.setTextColor(textSecondary(context));
        return view;
    }

    static View divider(Context context) {
        View view = new View(context);
        view.setBackgroundColor(blend(cardBackground(context), textSecondary(context), 0.2f));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, Math.max(1, dp(context, 0.6f)));
        params.topMargin = dp(context, 10);
        params.bottomMargin = dp(context, 10);
        view.setLayoutParams(params);
        return view;
    }

    static LinearLayout buttonRow(Context context, View... buttons) {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setPadding(0, dp(context, 10), 0, 0);
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

    static int blend(int base, int over, float ratio) {
        int r = (int) (Color.red(base) * (1 - ratio) + Color.red(over) * ratio);
        int g = (int) (Color.green(base) * (1 - ratio) + Color.green(over) * ratio);
        int b = (int) (Color.blue(base) * (1 - ratio) + Color.blue(over) * ratio);
        return Color.rgb(r, g, b);
    }
}
