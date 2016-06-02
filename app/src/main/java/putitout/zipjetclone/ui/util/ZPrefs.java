package putitout.zipjetclone.ui.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by SA on 6/2/2016.
 */
public class ZPrefs {

    public static final String KEY_RATE = "KEY_RATE";
    public static final String KEY_TOKEN = "KEY_TOKEN";
    public static final String KEY_PICK_UP_TIME = "KEY_PICK_UP_TIME";
    public static final String KEY_DROP_OFF_TIME = "KEY_DROP_OFF_TIME";
    public static final String KEY_PICK_UP_DATE = "KEY_PICK_UP_DATE";
    public static final String KEY_DROP_OFF_DATE = "KEY_DROP_OFF_DATE";
    public static final String KEY_USER_STATUS = "KEY_USER_STATUS";

    public static SharedPreferences sharePreference = null;

    public static SharedPreferences getSharedPreference(Context context) {
        if(sharePreference == null) {
            sharePreference = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return sharePreference;
    }

    public static void clearSharedPreferences(Context context) {
        getSharedPreference(context).edit().clear().commit();
    }


    public static void saveString(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key,String defaultValue) {
        return getSharedPreference(context).getString(key, defaultValue);
    }
}
