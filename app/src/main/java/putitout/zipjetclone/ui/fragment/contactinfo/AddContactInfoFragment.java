package putitout.zipjetclone.ui.fragment.contactinfo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.activity.HomeActivity;
import putitout.zipjetclone.ui.fragment.BaseFragment;
import putitout.zipjetclone.ui.fragment.confirmation.ConfirmationFragment;
import putitout.zipjetclone.ui.util.ZUtil;

/**
 * Created by SA on 6/1/2016.
 */
public class AddContactInfoFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = AddContactInfoFragment.class.getSimpleName();

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
        View view = inflater.inflate(R.layout.fragment_add_contact_info,container,false);
        initWidgets(view);
        return view;
    }

    public void initWidgets(View v){

        mHomeActivity = (HomeActivity) getActivity();

        saveContactButton = (Button) v.findViewById(R.id.saveContactButton);
        saveContactButton.setOnClickListener(this);
        checkImageView= (ImageView) v.findViewById(R.id.checkImageView);
        checkImageView.setOnClickListener(this);

        firstNameEditText = (EditText) v.findViewById(R.id.firstNameEditText);
        firstNameEditText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(firstNameEditText, 0);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        LastNameEditText = (EditText) v.findViewById(R.id.LastNameEditText);
        phoneEditText = (EditText) v.findViewById(R.id.phoneEditText);
        emailEditText = (EditText) v.findViewById(R.id.emailEditText);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomeActivity.changeViewsVisibility();

    }

    private void validateFields() {
        if (ZUtil.isEmpty(firstNameEditText.getText().toString())) {
            firstNameEditText.setError(ZUtil.getErrorHtmlFromString(getString(R.string.emailAddressErrorMessageEmpty)));
            saveContactButton.setBackgroundResource(R.drawable.save_contact_details_off);
        } else {
            firstNameEditText.setError(null);
        }
        if (ZUtil.isEmpty(LastNameEditText.getText().toString())) {
            LastNameEditText.setError(ZUtil.getErrorHtmlFromString(getString(R.string.passwordErrorMessageEmpty)));
            saveContactButton.setBackgroundResource(R.drawable.save_contact_details_off);
        } else {
            LastNameEditText.setError(null);
        }
        if (ZUtil.isEmpty(emailEditText.getText().toString())) {
            emailEditText.setError(ZUtil.getErrorHtmlFromString(getString(R.string.emailAddressErrorMessageEmpty)));
            saveContactButton.setBackgroundResource(R.drawable.save_contact_details_off);
        } else {
            emailEditText.setError(null);
        }
        if (!ZUtil.emailValidator(emailEditText.getText().toString())) {
            emailEditText.setError(ZUtil.getErrorHtmlFromString(getString(R.string.emailAddressInvalidMessage)));
            saveContactButton.setBackgroundResource(R.drawable.save_contact_details_off);
        } else {
            emailEditText.setError(null);
        }
        if (ZUtil.isEmpty(phoneEditText.getText().toString())) {
            phoneEditText.setError(ZUtil.getErrorHtmlFromString(getString(R.string.phoneInvalidMessage)));
            saveContactButton.setBackgroundResource(R.drawable.save_contact_details_off);
        } else {
            phoneEditText.setError(null);
        }

        if (!ZUtil.isEmpty(phoneEditText.getText().toString())
                && !ZUtil.isEmpty(LastNameEditText.getText().toString())
                && !ZUtil.isEmpty(firstNameEditText.getText().toString())
                && !ZUtil.isEmpty(emailEditText.getText().toString())
                && ZUtil.emailValidator(emailEditText.getText().toString())) {
            login(firstNameEditText.getText().toString(), LastNameEditText.getText().toString());
        }
    }


    private void login(String firstName, String lastName) {
//        if (NetworkUtil.checkIfNetworkAvailable(this)) {
//            NetworkMananger.loginUserApi(this, email, password, this, LOGIN_USER);
//        } else {
//            KUtil.showNetworkErrorAlertDialog(this);
//        }

        Toast.makeText(getActivity(),""+firstName+""+lastName,Toast.LENGTH_LONG).show();
        saveContactButton.setBackgroundResource(R.drawable.save_contact_details);

        showConfirmDetailsDialog();
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
            case R.id.saveContactButton:
                Toast.makeText(getActivity(),"Save Details",Toast.LENGTH_LONG).show();
                validateFields();
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


    private void showConfirmDetailsDialog() {
        ZUtil.showSaveDataAlert(getActivity(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(
//                        Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/*");
//                startActivityForResult(
//                        Intent.createChooser(intent, "Select File"),
//                        FROM_GALLERY);
                replaceFragment(R.id.fragmentContainerLayout,new ConfirmationFragment(),ConfirmationFragment.TAG,true);
                dialog.dismiss();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, REQUEST_CAMERA);
                dialog.dismiss();
            }
        });
    }
}
