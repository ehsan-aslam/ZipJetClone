package putitout.zipjetclone.ui.util;

import android.support.design.BuildConfig;
import android.util.Log;

/**
 * Created by Ehsan on 5/29/2016.
 */
public class ZLog {


    public static boolean loggingEnabled() {
        return BuildConfig.DEBUG;
    }

    public static void info(String message) {
        if (loggingEnabled()) {
            Log.v(ZUtil.APP_ID, "**** Zipjet ****: "+message);
        }
    }
}
