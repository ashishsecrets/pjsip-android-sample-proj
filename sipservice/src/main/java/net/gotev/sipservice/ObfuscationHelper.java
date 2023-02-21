package net.gotev.sipservice;

import android.content.Context;

/**
 * connect
 *
 *
 *
 */
public class ObfuscationHelper {
    public static String getValue(Context context, String string) {
        return SharedPreferencesHelper.getInstance(context).isObfuscationEnabled() ? obfuscate(string) : string;
    }

    private static String obfuscate(String string) {
        return string.length() > 5
                ? repeat(string.length() - 3) + string.substring(string.length() - 3)
                : repeat(string.length() - 1) + string.substring(string.length() - 1);
    }

    private static String repeat(int n) {
        return new String(new char[n]).replace("\0", "*");
    }
}
