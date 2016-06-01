package putitout.zipjetclone.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import putitout.zipjetclone.R;


/**
 * Created by Ehsan on 5/21/2016.
 */
public class SimpleLayout extends Activity implements View.OnClickListener {

    ImageView imageView2;
    Button buttonfirst;
    boolean visibility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setOnClickListener(this);
        buttonfirst = (Button) findViewById(R.id.buttonfirst);


    }

    public void onStart(View v){





    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.imageView2:
                if(visibility){
                    visibility=false;

                    buttonfirst.setVisibility(View.GONE);
                }else{
                    visibility=true;
                    buttonfirst.setVisibility(View.VISIBLE);

                }
                break;
        }

    }
}
