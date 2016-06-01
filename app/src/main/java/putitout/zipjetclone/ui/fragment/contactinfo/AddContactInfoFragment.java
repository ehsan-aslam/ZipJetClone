package putitout.zipjetclone.ui.fragment.contactinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.activity.HomeActivity;
import putitout.zipjetclone.ui.fragment.BaseFragment;

/**
 * Created by SA on 6/1/2016.
 */
public class AddContactInfoFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = AddContactInfoFragment.class.getSimpleName();

    private ImageView saveContactImageView;
    private ImageView checkImageView;

    boolean isClick = true;

    private HomeActivity mHomeActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contact_info,container,false);
        initWidgets(view);
        return view;
    }

    public void initWidgets(View v){

        mHomeActivity = (HomeActivity) getActivity();

        saveContactImageView = (ImageView) v.findViewById(R.id.saveContactImageView);
        saveContactImageView.setOnClickListener(this);
        checkImageView= (ImageView) v.findViewById(R.id.checkImageView);
        checkImageView.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomeActivity.changeViewsVisibility();

    }


    @Override
    public boolean isPullToRefreshEnable() {
        return false;
    }

    @Override
    public void onStartRefresh() {

    }

    @Override
    public void onVisible() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveContactImageView:
                Toast.makeText(getActivity(),"Save Details",Toast.LENGTH_LONG).show();
                break;
            case R.id.checkImageView:
                if(isClick){
                    checkImageView.setImageResource(R.drawable.uncheck_icon);
                    isClick=false;
                }else{
                    checkImageView.setImageResource(R.drawable.check_icon);
                    isClick=true;
                }
                break;
        }
    }
}
