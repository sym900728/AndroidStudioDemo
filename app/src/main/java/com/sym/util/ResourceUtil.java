package com.sym.util;

import android.content.Context;

/**
 * Created by Sym on 2015/12/2.
 */
public class ResourceUtil {

    public static int getLayoutId(Context context, String string) {
        return context.getResources().getIdentifier(string, "layout",
                context.getPackageName());
    }

    public static int getStringId(Context context, String string) {
        return context.getResources().getIdentifier(string, "string",
                context.getPackageName());
    }

    public static int getDrawableId(Context context, String string) {
        return context.getResources().getIdentifier(string,
                "drawable", context.getPackageName());
    }

    public static int getStyleId(Context context, String string) {
        return context.getResources().getIdentifier(string,
                "style", context.getPackageName());
    }

    public static int getId(Context context, String string) {
        return context.getResources().getIdentifier(string, "id", context.getPackageName());
    }

    public static int getColorId(Context context, String string) {
        return context.getResources().getIdentifier(string,
                "color", context.getPackageName());
    }

    public static int getArrayId(Context context, String string) {
        return context.getResources().getIdentifier(string,
                "array", context.getPackageName());
    }

}
