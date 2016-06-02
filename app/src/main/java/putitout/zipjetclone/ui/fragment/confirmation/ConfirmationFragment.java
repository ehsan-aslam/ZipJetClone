package putitout.zipjetclone.ui.fragment.confirmation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.activity.HomeActivity;
import putitout.zipjetclone.ui.fragment.BaseFragment;

/**
 * Created by SA on 6/1/2016.
 */
public class ConfirmationFragment extends BaseFragment {


    public static final String TAG = ConfirmationFragment.class.getSimpleName();

    private Button saveContactButton;
    private ImageView checkImageView;

    private EditText firstNameEditText;
    private EditText LastNameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;

    boolean isClick = true;

    private HomeActivity mHomeActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirmation,container,false);
        initWidgets(view);
        return view;
    }

    public void initWidgets(View v){

        mHomeActivity = (HomeActivity) getActivity();



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
}
