package putitout.zipjetclone.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import putitout.zipjetclone.R;


public class ImagePagerAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    Context context;
    String[] rank;
    String[] country;
    String[] population;
    int[] imagesFlag;

    public ImagePagerAdapter(Context context, int[] imagesFlag) {
        this.context = context;
        this.imagesFlag = imagesFlag;
    }

    @Override
    public int getCount() {
        return imagesFlag.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Declare Variables
        ImageView galleryImageView;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.image_pager, container,
                false);
        // Locate the ImageView in viewpager_item.xml
        galleryImageView = (ImageView) itemView.findViewById(R.id.imageDetailImageView);
        // Capture position and set to the ImageView
        galleryImageView.setImageResource(imagesFlag[position]);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((LinearLayout) object);

    }
}