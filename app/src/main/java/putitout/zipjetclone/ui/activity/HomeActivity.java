package putitout.zipjetclone.ui.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.fragment.BaseFragment;
import putitout.zipjetclone.ui.fragment.confirmation.ConfirmationFragment;
import putitout.zipjetclone.ui.fragment.contactinfo.AddContactInfoFragment;
import putitout.zipjetclone.ui.fragment.menu.HelpFragment;
import putitout.zipjetclone.ui.fragment.menu.OrderFragment;
import putitout.zipjetclone.ui.fragment.menu.PricingFragment;
import putitout.zipjetclone.ui.fragment.menu.PrivacyFragment;
import putitout.zipjetclone.ui.fragment.menu.TCFragment;
import putitout.zipjetclone.ui.interfaces.OnDateTimePickerListener;
import putitout.zipjetclone.ui.util.ZLog;
import putitout.zipjetclone.ui.util.ZPrefs;
import putitout.zipjetclone.ui.util.ZUtil;
import putitout.zipjetclone.ui.widgets.TypefaceTextView;

/**
 * Created by SA on 5/20/2016.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener,OnDateTimePickerListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private ImageView menuImageView;
    private ImageView pricingImageView;
    private ImageView backImageView;


    private TypefaceTextView headerTextView;

    private TextView menuOrderTextView;
    private TextView menuPricingTextView;
    private TextView menuHelpTextView;
    private TextView menuHowItWorksTextView;
    private TextView menuPrivacyTextView;
    private TextView menuTCTextView;
    private TextView menuLogoutTextView;

    private LinearLayout rateLinearLayout;
    private DrawerLayout drawerLayout;
    private LinearLayout drawerMenuLinearLayout;
    private FrameLayout fragmentContainerLayout;
    private String fragmentName;
    private double latitude ;
    private double longitude ;

    long timeInMillis;
    private AlertDialog logoutDialog;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ProgressDialog dialog = new ProgressDialog(this,R.style.full_screen_dialog){
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.fill_dialog);
                getWindow().setLayout(RelativeLayout.LayoutParams.FILL_PARENT,
                        RelativeLayout.LayoutParams.FILL_PARENT);
            }
        };

        dialog.setCancelable(false);
        dialog.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1000);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerMenuLinearLayout = (LinearLayout) findViewById(R.id.menuDrawer);
        fragmentContainerLayout = (FrameLayout) findViewById(R.id.fragmentContainerLayout);

        menuImageView = (ImageView) findViewById(R.id.menuImageView);
        menuImageView.setOnClickListener(this);
        pricingImageView = (ImageView) findViewById(R.id.pricingImageView);
        pricingImageView.setOnClickListener(this);
        backImageView = (ImageView) findViewById(R.id.backImageView);
        backImageView.setOnClickListener(this);

        headerTextView = (TypefaceTextView) findViewById(R.id.headerTextView);

        //Menu Layout Views
        menuOrderTextView = (TextView) findViewById(R.id.menuOrderTextView);
        menuOrderTextView.setOnClickListener(this);
        menuPricingTextView = (TextView) findViewById(R.id.menuPricingTextView);
        menuPricingTextView.setOnClickListener(this);
        menuHelpTextView = (TextView) findViewById(R.id.menuHelpTextView);
        menuHelpTextView.setOnClickListener(this);
        menuHowItWorksTextView = (TextView) findViewById(R.id.menuHowItWorksTextView);
        menuHowItWorksTextView.setOnClickListener(this);
        menuPrivacyTextView = (TextView) findViewById(R.id.menuPrivacyTextView);
        menuPrivacyTextView.setOnClickListener(this);
        menuTCTextView = (TextView) findViewById(R.id.menuTCTextView);
        menuTCTextView.setOnClickListener(this);
        menuLogoutTextView = (TextView) findViewById(R.id.menuLogoutTextView);
        menuLogoutTextView.setOnClickListener(this);

        getLatLgCoordinates();
        registerBackStackListener();

//        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        window.setStatusBarColor(this.getResources().getColor(R.color.greenBarColor));

//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ZUtil.BROADCAST_ACTION_KILL_PREVIOUS_ACTIVITIES);
//        registerReceiver(broadcastReceiver,intentFilter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initWidget() {}

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void changeViewsVisibility(){
//        headerTextView.setText(R.string.newOrder);
//        pricingImageView.setVisibility(View.VISIBLE);
    }

    public void getLatLgCoordinates(){

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) {
            latitude = bundle.getDouble(ZUtil.KEY_LATITUDE);
            longitude = bundle.getDouble(ZUtil.KEY_LONGITUDE);
        } else {}

        Bundle mBundle = new Bundle();

        mBundle.putDouble(ZUtil.KEY_LATITUDE,latitude);
        mBundle.putDouble(ZUtil.KEY_LONGITUDE,longitude);

        ConfirmationFragment orderFragment = new ConfirmationFragment();
        orderFragment.setArguments(mBundle);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragmentContainerLayout,new OrderFragment(),OrderFragment.TAG).
                commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menuImageView:
                toggleMenu();
                Toast.makeText(this,"menuImageView",Toast.LENGTH_LONG).show();
                break;
            case R.id.pricingImageView:
                Toast.makeText(this,"pricingImageView",Toast.LENGTH_LONG).show();
                replaceFragment(R.id.fragmentContainerLayout,new PricingFragment(),PricingFragment.TAG,true);
                break;
            case R.id.menuOrderTextView:
                clearPreviousBackStackTillHomeActivity();
                toggleMenu();
                replaceFragment(R.id.fragmentContainerLayout,new OrderFragment(),OrderFragment.TAG,true);
                break;
            case R.id.menuPricingTextView:
                clearPreviousBackStackTillHomeActivity();
                Toast.makeText(this,"Pricing",Toast.LENGTH_LONG).show();
                toggleMenu();
                replaceFragment(R.id.fragmentContainerLayout,new PricingFragment(),PricingFragment.TAG,true);
                break;
            case R.id.menuHelpTextView:
                clearPreviousBackStackTillHomeActivity();
                Toast.makeText(this,"Help",Toast.LENGTH_LONG).show();
                toggleMenu();
                replaceFragment(R.id.fragmentContainerLayout,new HelpFragment(),HelpFragment.TAG,true);
                break;
            case R.id.menuHowItWorksTextView:
                clearPreviousBackStackTillHomeActivity();
                toggleMenu();
                startActivity(new Intent(this,HowItWorksActivity.class));
                Toast.makeText(this,"How it works",Toast.LENGTH_LONG).show();
                HomeActivity.this.finish();
                break;
            case R.id.menuPrivacyTextView:
                Toast.makeText(this,"Privacy",Toast.LENGTH_LONG).show();
                toggleMenu();
                replaceFragment(R.id.fragmentContainerLayout,new PrivacyFragment(),PrivacyFragment.TAG,true);
                break;
            case R.id.menuTCTextView:
                clearPreviousBackStackTillHomeActivity();
                Toast.makeText(this,"Terms & Conditions",Toast.LENGTH_LONG).show();
                toggleMenu();
                replaceFragment(R.id.fragmentContainerLayout,new TCFragment(),TCFragment.TAG,true);
                break;
            case R.id.menuLogoutTextView:
                showLogoutAlert();
                break;
            case R.id.backImageView:
                getSupportFragmentManager().popBackStack();
                break;
        }

    }

    public void showLogoutAlert() {
        if(logoutDialog!=null && logoutDialog.isShowing()) return;
        logoutDialog = new AlertDialog.Builder(getActivity()).setMessage(getResources().getString(R.string.logoutAlert))
                .setCancelable(true)
                .setPositiveButton(getResources().getString(R.string.logOut), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logOut();
                        dialog.dismiss();
                    }
                }).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        logoutDialog.show();
    }


    public void logOut() {

        try {
            ZPrefs.clearSharedPreferences(this);
        } catch (Exception e) {}
        Intent intent = new Intent(this, PagerActivity.class);
        startActivity(intent);
        finish();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(ZUtil.BROADCAST_ACTION_KILL_PREVIOUS_ACTIVITIES)){
            }
        }
    };

    private void toggleMenu() {
        if (drawerLayout.isDrawerOpen(drawerMenuLinearLayout)) {
            drawerLayout.closeDrawer(drawerMenuLinearLayout);
        } else {
            drawerLayout.openDrawer(drawerMenuLinearLayout);
        }
    }

    @Override
    public void onDateTimePicked(String date, long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    private void clearPreviousBackStackTillHomeActivity() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        ZUtil.doSoftInputHide(this);
    }

    public void registerBackStackListener() {
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int count = getSupportFragmentManager().getBackStackEntryCount();
                if (count > 0) {
                    FragmentManager.BackStackEntry backStackEntry = getSupportFragmentManager().getBackStackEntryAt(count - 1);

                    //Pop previous fragment if needed
                    if (fragmentName != null && (fragmentName == OrderFragment.TAG ||
                            fragmentName == PricingFragment.TAG || fragmentName == PrivacyFragment.TAG ||
                            fragmentName == TCFragment.TAG || fragmentName == HelpFragment.TAG)) {
                    }
                    fragmentName = backStackEntry.getName();
                    BaseFragment baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(fragmentName);
                    if(baseFragment != null) {
                        baseFragment.onVisible();
                    }

                    ZLog.info("Top Fragment: " + fragmentName);

                    if (fragmentName.equals(OrderFragment.TAG)) {
                        headerTextView.setText(R.string.newOrder);
                    } else if (fragmentName.equals(PricingFragment.TAG)) {
                        headerTextView.setText(R.string.pricing);
                    } else if (fragmentName.equals(HelpFragment.TAG)) {
                        headerTextView.setText(R.string.help);
                    } else if(fragmentName.equals(TCFragment.TAG)){
                        headerTextView.setText(R.string.terms);
                    } else if(fragmentName.equals(PrivacyFragment.TAG)){
                        headerTextView.setText(R.string.privacy);
                    } else if (fragmentName.equals(AddContactInfoFragment.TAG)){
                        headerTextView.setText(R.string.addInfo);
                    } else if (fragmentName.equals(ConfirmationFragment.TAG)){
                        headerTextView.setText(R.string.confirmOrder);
                    }
                    else {}
                    if(fragmentName.equals(AddContactInfoFragment.TAG) ||
                            (fragmentName.equals(ConfirmationFragment.TAG))){
                        backImageView.setVisibility(View.VISIBLE);
                        menuImageView.setVisibility(View.INVISIBLE);
                    }else {
                        backImageView.setVisibility(View.INVISIBLE);
                        menuImageView.setVisibility(View.VISIBLE);
                    }
                    if (fragmentName.equals(HelpFragment.TAG) ||
                            fragmentName.equals(PricingFragment.TAG) ||
                            fragmentName.equals(PrivacyFragment.TAG) ||
                            fragmentName.equals(TCFragment.TAG) ||
                            fragmentName.equals(AddContactInfoFragment.TAG)||
                            fragmentName.equals(ConfirmationFragment.TAG)) {
                        pricingImageView.setVisibility(View.INVISIBLE);
                    } else {
                        pricingImageView.setVisibility(View.VISIBLE);
                        menuImageView.setVisibility(View.VISIBLE);
                        backImageView.setVisibility(View.INVISIBLE);
                        headerTextView.setText(R.string.newOrder);
                    }
                } else {
                    pricingImageView.setVisibility(View.VISIBLE);
                    menuImageView.setVisibility(View.VISIBLE);
                    backImageView.setVisibility(View.INVISIBLE);
                    headerTextView.setText(R.string.newOrder);
                }
            }
        });
    }
}
