package putitout.zipjetclone.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import putitout.zipjetclone.R;


/**
 * Created by Ehsan on 5/21/2016.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    ImageView confirmImageView;
    ImageView backImageView;
    boolean visibility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        backImageView = (ImageView) findViewById(R.id.backImageView);
        confirmImageView = (ImageView) findViewById(R.id.confirmImageView);

        confirmImageView.setOnClickListener(this);
        backImageView.setOnClickListener(this);

//        imageView2 = (ImageView) findViewById(R.id.imageView2);
//        imageView2.setOnClickListener(this);
//        buttonfirst = (Button) findViewById(R.id.buttonfirst);


    }

    public void onStart(View v){





    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.backImageView:
                finish();

//                if(visibility){
//                    visibility=false;
//
//                    buttonfirst.setVisibility(View.GONE);
//                }else{
//                    visibility=true;
//                    buttonfirst.setVisibility(View.VISIBLE);
//
//                }f
                break;

            case R.id.confirmImageView:

                if(visibility){
                    visibility=false;

                    confirmImageView.setImageResource(R.drawable.uncheck_confirm);
                }else{
                    visibility=true;
                    confirmImageView.setImageResource(R.drawable.confirm_icon);

                }
                break;
        }

    }
}
