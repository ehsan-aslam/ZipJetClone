package putitout.zipjetclone.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.interfaces.OnWebServiceResponse;
import putitout.zipjetclone.ui.model.Parser;
import putitout.zipjetclone.ui.network.NetworkMananger;
import putitout.zipjetclone.ui.network.NetworkUtil;
import putitout.zipjetclone.ui.util.ZLog;
import putitout.zipjetclone.ui.util.ZPrefs;
import putitout.zipjetclone.ui.util.ZUtil;


/**
 * Created by Ehsan on 5/21/2016.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener,OnWebServiceResponse {

    public static final String TAG = LoginActivity.class.getSimpleName();

    private static final int LOGIN_USER = 1;

    private Button confirmLoginButton;
    private ImageView backImageView;
    private EditText emailEditText;
    private EditText passwordEditText;
    ProgressDialog progressDialog;
    boolean visibility;

    private String email = "cross@zipjet.com";
    private String password = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidget();
    }

    @Override
    public void initWidget() {

        ZPrefs.saveBoolean(LoginActivity.this,"isLogin",false);
        ZPrefs.saveBoolean(LoginActivity.this,"isUpdated",false);

        ZUtil.generateHashKey(this, ZUtil.KEY_ENCODING_TYPE);
        backImageView = (ImageView) findViewById(R.id.backImageView);
        confirmLoginButton = (Button) findViewById(R.id.confirmLoginButton);
        emailEditText = (EditText) findViewById(R.id.emailLoginEitText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmLoginButton.setOnClickListener(this);
        backImageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backImageView:
                this.finish();
                break;
            case R.id.confirmLoginButton:
                validateFields();
                break;
        }
    }

    private void validateFields() {
        if (ZUtil.isEmpty(emailEditText.getText().toString())) {
            emailEditText.setError(ZUtil.getErrorHtmlFromString(getString(R.string.emailAddressErrorMessageEmpt)));
            confirmLoginButton.setBackgroundResource(R.drawable.save_contact_details_off);
        } else {
            emailEditText.setError(null);
        }
        if (ZUtil.isEmpty(passwordEditText.getText().toString())) {
            passwordEditText.setError(ZUtil.getErrorHtmlFromString(getString(R.string.passwordErrorMessageEmpty)));
            confirmLoginButton.setBackgroundResource(R.drawable.save_contact_details_off);
        } else {
            passwordEditText.setError(null);
        }
        if (!ZUtil.emailValidator(emailEditText.getText().toString())) {
            emailEditText.setError(ZUtil.getErrorHtmlFromString(getString(R.string.emailAddressInvalidMessage)));
            confirmLoginButton.setBackgroundResource(R.drawable.save_contact_details_off);
        } else {
            emailEditText.setError(null);
        }
        if (!ZUtil.isEmpty(emailEditText.getText().toString())
                && !ZUtil.isEmpty(passwordEditText.getText().toString())
                && ZUtil.emailValidator(emailEditText.getText().toString())) {
            login(emailEditText.getText().toString(), passwordEditText.getText().toString());
        }
    }

    private void login(String email, String password) {
       if (NetworkUtil.checkIfNetworkAvailable(this)) {
        NetworkMananger.loginUserApi(this, email, password, this, LOGIN_USER);
    } else {
        ZUtil.showNetworkErrorAlertDialog(this);
    }
    }

    @Override
    public void onStartWebService(int requestCode) {
        progressDialog = getProgressDialog();
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void onCompletedWebService(String response, int requestCode, boolean isValidResponse) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (isValidResponse) {
            Parser parser = new Parser(response);
            switch (requestCode) {
                case LOGIN_USER:
                    ZLog.info(response);
                    parseUserData(parser);
                    break;
            }
        }
    }

    private void parseUserData(Parser parser) {
        if (parser.getStatus().equals(ZUtil.KEY_SERVER_RESPONSE_FAILURE)) {
            ZUtil.showAlert(this, parser.getMessage());
        } else if (parser.getStatus().equals(ZUtil.KEY_SERVER_RESPONSE_SUCCESS)) {
            saveUserData(this, parser);
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void saveUserData(Context context, Parser parseData) {
        String token = parseData.getToken();
        String id = parseData.getId();
        String firstName = parseData.getFirstName();
        String lastName = parseData.getLastName();
        String getMiddleName = parseData.getMiddleName();
        String registrationOrigin = parseData.getRegistrationOrigin();
        String gender = parseData.getGender();
        String type = parseData.getType();
        String dateModified = parseData.getDateModified();
        String userStatus = parseData.getUserStatus();
        String email = parseData.getEmail();
        String image = parseData.getImage();


        ZPrefs.saveString(context, ZPrefs.KEY_TOKEN, token);
        ZPrefs.saveString(context, ZPrefs.KEY_USER_ID, id);
        ZPrefs.saveString(context, ZPrefs.KEY_FIRST_NAME, firstName);
        ZPrefs.saveString(context, ZPrefs.KEY_LAST_NAME, lastName);
        ZPrefs.saveString(context, ZPrefs.KEY_MIDDLE_NAME, getMiddleName);
        ZPrefs.saveString(context, ZPrefs.KEY_REGISTRATION_ORIGIN, registrationOrigin);
        ZPrefs.saveString(context, ZPrefs.KEY_GENDER, gender);
        ZPrefs.saveString(context, ZPrefs.KEY_TYPE, type);
        ZPrefs.saveString(context, ZPrefs.KEY_DATE_MODIFIED, dateModified);
        ZPrefs.saveString(context, ZPrefs.KEY_USER_STATUS, userStatus);
        ZPrefs.saveString(context, ZPrefs.KEY_IMAGE, image);
        ZPrefs.saveString(context, ZPrefs.KEY_EMAIL, email);
    }
}


//                if(visibility){
//                    visibility=false;
//                    confirmLoginButton.setImageResource(R.drawable.uncheck_confirm);
//                }else{
//                    visibility=true;
//                    confirmLoginButton.setImageResource(R.drawable.confirm_icon);
//                }

//                String getEmail = emailEditText.getText().toString();
//                String getPassword = passwordEditText.getText().toString();
//
//                if(getEmail.isEmpty() && getPassword.isEmpty()){
//
//                    Toast.makeText(this,"Please Enter the Email and Password",Toast.LENGTH_LONG).show();
//                }else{
//
//                if(getEmail.equalsIgnoreCase(email) && (getPassword.equalsIgnoreCase(password))){
//                    confirmLoginButton.setImageResource(R.drawable.confirm_icon);
//                    startActivity(new Intent(this, HomeActivity.class));
//                    Toast.makeText(this,"Welcome To Zipjet",Toast.LENGTH_LONG).show();
//                    this.finish();
//
//                }else {
//                    Toast.makeText(this,"Your email_off_icon or password is incorrect",Toast.LENGTH_LONG).show();
//                    confirmLoginButton.setImageResource(R.drawable.uncheck_confirm);
//                }
//            }

//        ConfirmationFragment confirmationFragment = new ConfirmationFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("firstName", email);
//        bundle.putString("lastName", password);
//        bundle.putString("email_off_icon", email);
//
//        confirmLoginButton.setBackgroundResource(R.drawable.save_contact_details);