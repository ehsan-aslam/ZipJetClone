package putitout.zipjetclone.ui.fragment.confirmation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.activity.HomeActivity;
import putitout.zipjetclone.ui.fragment.BaseFragment;
import putitout.zipjetclone.ui.util.ZLog;
import putitout.zipjetclone.ui.util.ZPrefs;

/**
 * Created by SA on 6/1/2016.
 */
public class ConfirmationFragment extends BaseFragment implements View.OnClickListener {


    public static final String TAG = ConfirmationFragment.class.getSimpleName();

    private Button saveContactButton;
    private ImageView checkImageView;

    private TextView firstNameTextView;
    private TextView LastNameTextView;
    private TextView emailTextView;
    private TextView phoneNumberTextView;
    private TextView rateTextView;
    private TextView pickUpDateTexView;
    private TextView dropOffDateTextView;
    private TextView locationTextView;

    private Button saveOrderButton;

    private double lat;
    private double lon;

    String selectedRate;

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

        firstNameTextView = (TextView) v.findViewById(R.id.firstNameTextView);
        LastNameTextView = (TextView) v.findViewById(R.id.LastNameTextView);
        emailTextView = (TextView) v.findViewById(R.id.emailTextView);
        phoneNumberTextView = (TextView) v.findViewById(R.id.phoneNumberTextView);
        rateTextView = (TextView) v.findViewById(R.id.rateTextView);
        pickUpDateTexView = (TextView) v.findViewById(R.id.pickUpDateTexView);
        dropOffDateTextView = (TextView) v.findViewById(R.id.dropOffDateTextView);
        locationTextView = (TextView) v.findViewById(R.id.locationTextView);

        saveOrderButton = (Button) v.findViewById(R.id.saveOrderButton);
        saveOrderButton.setOnClickListener(this);


        Bundle bundle = this.getArguments();
        String firstName = bundle.getString("firstName");
        String lastName = bundle.getString("lastName");
        String email = bundle.getString("email_off_icon");
        String phoneNumber = bundle.getString("phoneNumber");

        firstNameTextView.setText(firstName);
        LastNameTextView.setText(lastName);
        emailTextView.setText(email);
        phoneNumberTextView.setText(phoneNumber);


        String selectedRate = ZPrefs.getString(getActivity(),ZPrefs.KEY_RATE,"");
        String pickUpTime = ZPrefs.getString(getActivity(),ZPrefs.KEY_PICK_UP_TIME,"");
        String pickUpDate = ZPrefs.getString(getActivity(),ZPrefs.KEY_PICK_UP_DATE,"");
        String dropOffTime = ZPrefs.getString(getActivity(),ZPrefs.KEY_DROP_OFF_TIME,"");
        String dropOffDate = ZPrefs.getString(getActivity(),ZPrefs.KEY_DROP_OFF_DATE,"");
        String address = ZPrefs.getString(getActivity(),ZPrefs.KEY_ADDRESS,"");
        rateTextView.setText(selectedRate);
        pickUpDateTexView.setText(pickUpDate+" "+"-"+pickUpTime);
        dropOffDateTextView.setText(dropOffDate+" "+"-"+dropOffTime);

        ZLog.info(""+TAG + "address:" + address);
        ZLog.info(""+TAG + "pickUpTime: " + pickUpTime);
        ZLog.info(""+TAG + "dropOffTime:" + dropOffTime);

        Toast.makeText(getActivity(),"Following are your details",Toast.LENGTH_LONG).show();

        lat = Double.longBitsToDouble(ZPrefs.getLong(getActivity(),ZPrefs.KEY_LATITUDE,0));
        lon = Double.longBitsToDouble(ZPrefs.getLong(getActivity(),ZPrefs.KEY_LONGITUDE,0));

        locationTextView.setText(address + "," + "Lahore");
//        +"Lahore " + "--" + "  "+"Lat: " + lat + " , " + " Long: " + lon
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
    public void onStartRefresh() {}

    @Override
    public void onVisible() {}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveOrderButton:
                Toast.makeText(getActivity(),"Your Order have been confirmed,Thanks",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
