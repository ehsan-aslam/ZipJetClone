package putitout.zipjetclone.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import putitout.zipjetclone.R;

/**
 * Created by SA on 5/19/2016.
 */
@SuppressWarnings("ALL")
public class OrderActivity extends Activity implements View.OnClickListener{

    ImageView continueImageView;
    ImageView liteImageView,expressImageView,plusImageView,rateImageView;
    boolean liteRate,plusRate,expressRate;
    boolean isfistTime;
    private static ProgressDialog progressDialog;
    private static final long SPLASH_MILLIS = 2000;
    private int counter = 1;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        continueImageView = (ImageView) findViewById(R.id.continueImageView);
        plusImageView = (ImageView) findViewById(R.id.plusImageView);
        expressImageView = (ImageView) findViewById(R.id.expressImageView);
        expressImageView.setOnClickListener(this);
        plusImageView.setOnClickListener(this);
        liteImageView = (ImageView) findViewById(R.id.liteImageView);
        continueImageView.setOnClickListener(this);
        liteImageView.setOnClickListener(this);
        rateImageView = (ImageView) findViewById(R.id.rateImageView);
        rateImageView.setOnClickListener(this);


//        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        window.setStatusBarColor(this.getResources().getColor(R.color.greenBarColor));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continueImageView:
                Toast.makeText(this,"continue",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,HomeActivity.class));
                break;

            case R.id.liteImageView:

                if(counter==0){

                    liteImageView.setImageResource(R.drawable.lite_icon);
                    checkFlowAndProceed();
                    counter+=1;

                }

                if(isfistTime){
                    isfistTime=false;
                    Toast.makeText(this,"lite",Toast.LENGTH_LONG).show();
                } else{
                    isfistTime=true;
                    rateImageView.setImageResource(R.drawable.lite_rate);
                }

                plusImageView.setImageResource(R.drawable.plus_off);
                expressImageView.setImageResource(R.drawable.express_off);

                if(liteRate){
                    liteRate=false;
                    rateImageView.setVisibility(View.VISIBLE);
                }
                else {
                    liteRate=true;
                    rateImageView.setVisibility(View.GONE);
//                    progressDialog.dismiss();
                }

//                checkFlowAndProceed();

                break;

            case R.id.plusImageView:
                counter--;
                Toast.makeText(this,"plus",Toast.LENGTH_LONG).show();
                plusImageView.setImageResource(R.drawable.plus_image);
                liteImageView.setImageResource(R.drawable.lite_off);
                expressImageView.setImageResource(R.drawable.express_off);
                rateImageView.setImageResource(R.drawable.plus_rate);

                if(plusRate){
                    plusRate=false;
                    rateImageView.setVisibility(View.VISIBLE);
                    checkFlowAndProceed();


                    }
                else {
                    plusRate=true;
                    rateImageView.setVisibility(View.GONE);
                }


                break;
            case R.id.expressImageView:
                Toast.makeText(this,"express",Toast.LENGTH_LONG).show();
//                expressImageView.setVisibility(View.VISIBLE);
//                liteImageView.setVisibility(View.INVISIBLE);
//                plusImageView.setVisibility(View.INVISIBLE);
                expressImageView.setImageResource(R.drawable.express_image);
                liteImageView.setImageResource(R.drawable.lite_off);
                plusImageView.setImageResource(R.drawable.plus_off);
                rateImageView.setImageResource(R.drawable.express_rate);
                if(expressRate){
                    expressRate=false;
                    rateImageView.setVisibility(View.VISIBLE);
                    checkFlowAndProceed();
                }
                else {
                    expressRate=true;
                    rateImageView.setVisibility(View.GONE);
//                    progressDialog.dismiss();
                }

                break;

        }

    }

    private void checkFlowAndProceed() {
        progressDialog = ProgressDialog.show(this, "", "", true);
//        progressDialog.setContentView(R.layout.activity_home);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, SPLASH_MILLIS);
    }
}
