package putitout.zipjetclone.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.util.ZPrefs;
import putitout.zipjetclone.ui.util.ZUtil;

/**
 * Created by SA on 5/19/2016.
 */
public class SplashActivity extends Activity {

    private static final long SPLASH_MILLIS = 5000;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initWidget();
    }
    public void initWidget() {
        ZUtil.doSoftInputHide(this);
        checkFlowAndProceed();
    }

    private void checkFlowAndProceed() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (ZPrefs.getString(SplashActivity.this, ZPrefs.KEY_TOKEN, "").equals("")) {
                    startActivity(new Intent(SplashActivity.this, PagerActivity.class));

                } else {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                }
                finish();
            }
        }, SPLASH_MILLIS);
    }
}

//        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        window.setStatusBarColor(this.getResources().getColor(R.color.greenBarColor));