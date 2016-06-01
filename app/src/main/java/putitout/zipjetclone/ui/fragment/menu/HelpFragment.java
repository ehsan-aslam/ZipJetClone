package putitout.zipjetclone.ui.fragment.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.activity.HomeActivity;
import putitout.zipjetclone.ui.fragment.BaseFragment;

/**
 * Created by SA on 5/27/2016.
 */
public class HelpFragment extends BaseFragment {

    public static String TAG = HelpFragment.class.getSimpleName();
    private HomeActivity mHomeActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help,container,false);
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
