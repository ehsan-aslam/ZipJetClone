package putitout.zipjetclone.ui.fragment.contactinfo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.activity.HomeActivity;
import putitout.zipjetclone.ui.fragment.BaseFragment;
import putitout.zipjetclone.ui.fragment.confirmation.ConfirmationFragment;
import putitout.zipjetclone.ui.util.ZUtil;

/**
 * Created by SA on 6/1/2016.
 */
public class AddContactInfoFragment extends BaseFragment implements View.OnClickListener,View.OnTouchListener {

    public static final String TAG = AddContactInfoFragment.class.getSimpleName();

    private Button saveContactButton;
    private ImageView checkImageView;
    private ImageView firstPersonImageView;
    private ImageView emailImageView;
    private ImageView lastNameImageView;

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
        lastNameImageView = (ImageView) v.findViewById(R.id.lastNameImageView);
        firstPersonImageView= (ImageView) v.findViewById(R.id.firstPersonImageView);
        emailImageView = (ImageView) v.findViewById(R.id.emailImageView);


        firstNameEditText = (EditText) v.findViewById(R.id.firstNameEditText);
        firstNameEditText.requestFocus();
        firstPersonImageView.setImageResource(R.drawable.person_face_highlighted);
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(firstNameEditText, 0);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        LastNameEditText = (EditText) v.findViewById(R.id.LastNameEditText);
        phoneEditText = (EditText) v.findViewById(R.id.phoneEditText);
        emailEditText = (EditText) v.findViewById(R.id.emailEditText);

        firstNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    firstPersonImageView.setImageResource(R.drawable.person_face_off);
                    emailImageView.setImageResource(R.drawable.email_off_icon);
                    lastNameImageView.setImageResource(R.drawable.person_face_highlighted);
                    LastNameEditText.requestFocus();
                    return true;
                }
                return false;
            }
        });

        LastNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    firstPersonImageView.setImageResource(R.drawable.person_face_off);
                    lastNameImageView.setImageResource(R.drawable.person_face_off);
                    emailImageView.setImageResource(R.drawable.email_on_icon);
                    emailEditText.requestFocus();

                }
                return false;
            }
        });

        emailEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    firstPersonImageView.setImageResource(R.drawable.person_face_off);
                    lastNameImageView.setImageResource(R.drawable.person_face_off);
                    emailImageView.setImageResource(R.drawable.email_off_icon);

                }
                return false;
            }
        });

        phoneEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT){
                    firstPersonImageView.setImageResource(R.drawable.person_face_off);
                    lastNameImageView.setImageResource(R.drawable.person_face_off);
                    emailImageView.setImageResource(R.drawable.email_off_icon);

                }
                return false;
            }
        });

        LastNameEditText.setOnTouchListener(this);
        firstNameEditText.setOnTouchListener(this);
        emailEditText.setOnTouchListener(this);
        phoneEditText.setOnTouchListener(this);
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
            login(firstNameEditText.getText().toString(), LastNameEditText.getText().toString(),
                    emailEditText.getText().toString(),phoneEditText.getText().toString());
        }
    }

    private void login(String firstName, String lastName ,String email, String phoneNumber) {
//        if (NetworZUtil.checkIfNetworkAvailable(this)) {
//            NetworkMananger.loginUserApi(this, email_off_icon, password, this, LOGIN_USER);
//        } else {
//            ZUtil.showNetworkErrorAlertDialog(this);
//        }
        ConfirmationFragment confirmationFragment = new ConfirmationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("firstName", firstName);
        bundle.putString("lastName", lastName);
        bundle.putString("email_off_icon", email);
        bundle.putString("phoneNumber", phoneNumber);
        confirmationFragment.setArguments(bundle);
        replaceFragment(R.id.fragmentContainerLayout,confirmationFragment,ConfirmationFragment.TAG,true);
        saveContactButton.setBackgroundResource(R.drawable.save_contact_details);
//        showConfirmDetailsDialog();
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
            case R.id.saveContactButton:
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
                replaceFragment(R.id.fragmentContainerLayout,new ConfirmationFragment(),ConfirmationFragment.TAG,true);
                dialog.dismiss();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.firstNameEditText:
                firstPersonImageView.setImageResource(R.drawable.person_face_highlighted);
                lastNameImageView.setImageResource(R.drawable.person_face_off);
                emailImageView.setImageResource(R.drawable.email_off_icon);
                break;
            case R.id.LastNameEditText:
                firstPersonImageView.setImageResource(R.drawable.person_face_off);
                emailImageView.setImageResource(R.drawable.email_off_icon);
                lastNameImageView.setImageResource(R.drawable.person_face_highlighted);
                break;
            case R.id.emailEditText:
                firstPersonImageView.setImageResource(R.drawable.person_face_off);
                emailImageView.setImageResource(R.drawable.email_on_icon);
                lastNameImageView.setImageResource(R.drawable.person_face_off);
                break;
            case R.id.phoneEditText:
                firstPersonImageView.setImageResource(R.drawable.person_face_off);
                lastNameImageView.setImageResource(R.drawable.person_face_off);
                emailImageView.setImageResource(R.drawable.email_off_icon);
                break;
        }
        return false;
    }
}
