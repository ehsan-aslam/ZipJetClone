package putitout.zipjetclone.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.adapter.ImagePagerAdapter;

/**
 * Created by SA on 5/19/2016.
 */
public class PagerActivity extends Activity implements View.OnClickListener {

    private ViewPager galleryImageViewPager;
    private ImagePagerAdapter albumImagePagerAdapter;
    private ImageView startLogin;
    private int[] galleryImageFlag;
    LinearLayout ll;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
////        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
////        window.setStatusBarColor(Color.TRANSPARENT);
//
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        // enable status bar tint
//        tintManager.setStatusBarTintEnabled(true);
//        // enable navigation bar tint
//        tintManager.setNavigationBarTintEnabled(true);
//        tintManager.setTintColor(Color.parseColor("#99000FF"));

//        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//
//        getWindow().setStatusBarColor(Color.TRANSPARENT);

        initWidget();

//        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
//        ll.setPadding(0, config.getPixelInsetTop(), config.getPixelInsetRight(), config.getPixelInsetBottom());
        Toast.makeText(this,"Swipe Left And Right For Demo",Toast.LENGTH_LONG).show();

    }
    public void initWidget() {
        galleryImageFlag = new int[]{R.drawable.first_scren, R.drawable.second_scren,
                R.drawable.third_scren, R.drawable.fourth_scren
        };
        galleryImageViewPager = (ViewPager) findViewById(R.id.imageViewPager);
        albumImagePagerAdapter = new ImagePagerAdapter(this, galleryImageFlag);
        startLogin = (ImageView) findViewById(R.id.startLogin);
        startLogin.setOnClickListener(this);
        galleryImageViewPager.setAdapter(albumImagePagerAdapter);
        albumImagePagerAdapter.notifyDataSetChanged();
        galleryImageViewPager.setCurrentItem(0);

//        ll= (LinearLayout) findViewById(R.id.ll);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.startLogin:
                startActivity(new Intent(this,HomeActivity.class));
                break;
        }

    }
}
