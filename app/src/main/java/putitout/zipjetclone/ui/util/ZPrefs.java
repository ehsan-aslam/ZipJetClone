package putitout.zipjetclone.ui.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by SA on 6/2/2016.
 */
public class ZPrefs {

    public static final String KEY_RATE = "KEY_RATE";

    public static final String KEY_PICK_UP_TIME = "KEY_PICK_UP_TIME";
    public static final String KEY_DROP_OFF_TIME = "KEY_DROP_OFF_TIME";
    public static final String KEY_PICK_UP_DATE = "KEY_PICK_UP_DATE";
    public static final String KEY_DROP_OFF_DATE = "KEY_DROP_OFF_DATE";
    public static final String KEY_LATITUDE = "KEY_LATITUDE";
    public static final String KEY_LONGITUDE = "KEY_LONGITUDE";
    public static final String KEY_ADDRESS = "KEY_ADDRESS";
    public static final String KEY_USER_ID = "KEY_USER_ID";
    public static final String KEY_TOKEN = "KEY_TOKEN";
    public static final String KEY_FIRST_NAME = "KEY_FIRST_NAME";
    public static final String KEY_LAST_NAME = "KEY_LAST_NAME";
    public static final String KEY_MIDDLE_NAME = "KEY_MIDDLE_NAME";
    public static final String KEY_DATE_MODIFIED = "KEY_DATE_MODIFIED";
    public static final String KEY_USER_STATUS = "KEY_USER_STATUS";
    public static final String KEY_REGISTRATION_ORIGIN = "KEY_REGISTRATION_ORIGIN";
    public static final String KEY_TYPE = "KEY_TYPE";
    public static final String KEY_GENDER = "KEY_GENDER";
    public static final String KEY_BABY_ID = "KEY_BABY_ID";
    public static final String KEY_NOTIFICATION = "KEY_NOTIFICATION";
    public static final String KEY_REMEMBER_ME = "KEY_REMEMBER_ME";
    public static final String KEY_EMAIL = "KEY_EMAIL";
    public static final String KEY_IMAGE = "KEY_IMAGE";

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

    public static void saveBoolean(Context context, String key,boolean value) {
        getSharedPreference(context).edit().putBoolean(key, value).commit();
    }

    public static Boolean getBoolean(Context context, String key,Boolean defaultValue) {
        return getSharedPreference(context).getBoolean(key, defaultValue);
    }

    public static void saveString(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key,String defaultValue) {
        return getSharedPreference(context).getString(key, defaultValue);
    }

    public static void saveInt(Context context, String key, int value) {
        getSharedPreference(context).edit().putInt(key, value).commit();
    }

    public static Long getLong(Context context, String key, long defaultValue) {
        return getSharedPreference(context).getLong(key, defaultValue);
    }

    public static void saveLong(Context context, String key, long value) {
        getSharedPreference(context).edit().putLong(key, value).commit();
    }
//    prefsEditor.putLong("Latitude", Double.doubleToLongBits(location.getLatitude()));


}
