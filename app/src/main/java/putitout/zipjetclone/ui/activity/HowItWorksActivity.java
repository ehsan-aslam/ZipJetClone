package putitout.zipjetclone.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.adapter.ImagePagerAdapter;

/**
 * Created by SA on 6/9/2016.
 */
public class HowItWorksActivity extends Activity implements View.OnClickListener {

    private ViewPager imageViewPager;
    private ImagePagerAdapter imagePagerAdapter;
    private ImageView startNowImageView;
    private int[] sliderImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_it_works);
        initWidget();
    }

    public void initWidget() {
        sliderImages = new int[]{R.drawable.first_scren, R.drawable.second_scren,
                R.drawable.third_scren, R.drawable.fourth_scren
        };
        imageViewPager = (ViewPager) findViewById(R.id.imageViewPager);
        imagePagerAdapter = new ImagePagerAdapter(this, sliderImages);
        startNowImageView = (ImageView) findViewById(R.id.startNowImageView);
        startNowImageView.setOnClickListener(this);
        imageViewPager.setAdapter(imagePagerAdapter);
        imagePagerAdapter.notifyDataSetChanged();
        imageViewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startNowImageView:
                startActivity(new Intent(this, HomeActivity.class));
                HowItWorksActivity.this.finish();
                break;
        }
    }
}