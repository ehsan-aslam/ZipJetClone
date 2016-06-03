package putitout.zipjetclone.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import putitout.zipjetclone.R;


/**
 * Created by Ehsan on 5/21/2016.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private ImageView confirmImageView;
    private ImageView backImageView;
    private EditText emailEitText;
    private EditText passwordEditText;
    boolean visibility;

    private String email = "cross@zipjet.com";
    private String password = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backImageView = (ImageView) findViewById(R.id.backImageView);
        confirmImageView = (ImageView) findViewById(R.id.confirmImageView);
        emailEitText = (EditText) findViewById(R.id.emailLoginEitText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmImageView.setOnClickListener(this);
        backImageView.setOnClickListener(this);

    }

    public void onStart(View v){
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.backImageView:
                this.finish();
                break;
            case R.id.confirmImageView:
                String getEmail = emailEitText.getText().toString();
                String getPassword = passwordEditText.getText().toString();


                if(getEmail.isEmpty() && getPassword.isEmpty()){

                    Toast.makeText(this,"Please Enter the Email and Password",Toast.LENGTH_LONG).show();
                }else{

                if(getEmail.equalsIgnoreCase(email) && (getPassword.equalsIgnoreCase(password))){
                    confirmImageView.setImageResource(R.drawable.confirm_icon);
                    startActivity(new Intent(this, HomeActivity.class));
                    Toast.makeText(this,"Welcome To Zipjet",Toast.LENGTH_LONG).show();
                    this.finish();

                }else {
                    Toast.makeText(this,"Your email or password is incorrect",Toast.LENGTH_LONG).show();
                    confirmImageView.setImageResource(R.drawable.uncheck_confirm);
                }
            }
                break;
        }

    }
}


//                if(visibility){
//                    visibility=false;
//                    confirmImageView.setImageResource(R.drawable.uncheck_confirm);
//                }else{
//                    visibility=true;
//                    confirmImageView.setImageResource(R.drawable.confirm_icon);
//                }