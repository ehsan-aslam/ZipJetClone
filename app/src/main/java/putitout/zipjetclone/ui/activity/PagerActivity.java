package putitout.zipjetclone.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.adapter.ImagePagerAdapter;

/**
 * Created by SA on 5/19/2016.
 */
public class PagerActivity extends Activity implements View.OnClickListener {

    private ViewPager imageViewPager;
    private ImagePagerAdapter imagePagerAdapter;
    private ImageView startImageView;
    private ImageView loginImageView;
    private int[] sliderImages;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        initWidget();
        Toast.makeText(this,"Swipe Left And Right For Demo",Toast.LENGTH_LONG).show();
    }

    public void initWidget() {

        sliderImages = new int[]{R.drawable.first_scren, R.drawable.second_scren,
                R.drawable.third_scren, R.drawable.fourth_scren};

        imageViewPager = (ViewPager) findViewById(R.id.imageViewPager);
        imagePagerAdapter = new ImagePagerAdapter(this, sliderImages);
        startImageView = (ImageView) findViewById(R.id.startImageView);
        loginImageView = (ImageView) findViewById(R.id.loginImageView);
        loginImageView.setOnClickListener(this);
        startImageView.setOnClickListener(this);
        imageViewPager.setAdapter(imagePagerAdapter);
        imagePagerAdapter.notifyDataSetChanged();
        imageViewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.startImageView:
                startActivity(new Intent(this,HomeActivity.class));
                break;
            case R.id.loginImageView:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }
}


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

//        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
//        ll.setPadding(0, config.getPixelInsetTop(), config.getPixelInsetRight(), config.getPixelInsetBottom());