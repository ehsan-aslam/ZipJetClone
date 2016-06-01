package putitout.zipjetclone.ui.fragment.menu;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.adapter.TimeListAdapter;
import putitout.zipjetclone.ui.fragment.BaseFragment;
import putitout.zipjetclone.ui.fragment.contactinfo.AddContactInfoFragment;
import putitout.zipjetclone.ui.widgets.TypefaceTextView;

/**
 * Created by SA on 5/27/2016.
 */
@SuppressWarnings("deprecation")
public class OrderFragment extends BaseFragment implements View.OnClickListener{

    public static final String TAG = OrderFragment.class.getSimpleName();

    private ImageView continueImageView;
    private ImageView liteImageView;
    private ImageView expressImageView;
    private ImageView plusImageView;
    private ImageView rateImageView;
    private ImageView tapImageView;
    private ImageView pricingImageView;
    private LinearLayout pickUpLinearLayout;
    private LinearLayout dropOffLinearLayout;

    private TextView placeTextView;
    private TextView pickUpTimeTexView;
    private TextView pickUpDateTexView;
    private TextView dropOffTimeTexView;
    private TextView dropOffDateTexView;

    private TypefaceTextView liteTextView;
    private TypefaceTextView plusTextView;
    private TypefaceTextView expressTextView;

    private Dialog moreOptionsDialog;


    boolean liteRate;
    boolean plusRate = true;
    boolean expressRate = true;

    private String[] pickUpDateList;
    private String[] pickUpTimeList;
    private String[] dropOffDateList;
    private String[] dropOffTimeList;

    private int counter = 1;
    private static ProgressDialog progressDialog;
    private static final long SPLASH_MILLIS = 2000;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        widgets(view);
        return view;
    }

    public void widgets(View v){
        continueImageView = (ImageView) v.findViewById(R.id.continueImageView);
        plusImageView = (ImageView) v.findViewById(R.id.plusImageView);
        expressImageView = (ImageView) v.findViewById(R.id.expressImageView);
        expressImageView.setOnClickListener(this);
        plusImageView.setOnClickListener(this);
        liteImageView = (ImageView) v.findViewById(R.id.liteImageView);
        continueImageView.setOnClickListener(this);
        liteImageView.setOnClickListener(this);
        rateImageView = (ImageView) v.findViewById(R.id.rateImageView);
        rateImageView.setOnClickListener(this);
        pickUpLinearLayout = (LinearLayout) v.findViewById(R.id.pickUpLinearLayout);
        dropOffLinearLayout = (LinearLayout) v.findViewById(R.id.dropOffLinearLayout);
        dropOffLinearLayout.setOnClickListener(this);
        pickUpLinearLayout.setOnClickListener(this);

        placeTextView = (TextView) v.findViewById(R.id.placeTextView);
        placeTextView.setOnClickListener(this);

        liteTextView = (TypefaceTextView) v.findViewById(R.id.liteTextView);
        liteTextView.setOnClickListener(this);
        plusTextView = (TypefaceTextView) v.findViewById(R.id.plusTextView);
        plusTextView.setOnClickListener(this);
        expressTextView = (TypefaceTextView) v.findViewById(R.id.expressTextView);
        expressTextView.setOnClickListener(this);
        pickUpDateTexView = (TextView) v.findViewById(R.id.pickUpDateTexView);
        pickUpTimeTexView= (TextView) v.findViewById(R.id.pickUpTimeTexView);
        dropOffTimeTexView = (TextView) v.findViewById(R.id.dropOffTimeTexView);
        dropOffDateTexView = (TextView) v.findViewById(R.id.dropOffDateTexView);

        pickUpDateList = new String[]{"12:00 - 14:00","14:00 - 16:00","16:00 - 18:00","18:00 - 20:00","20:00 - 22:00"};
        pickUpTimeList = new String[]{"16:00-18:00","18:30 - 20:00","20:30 - 23:00"};

        dropOffDateList = new String[]{"08:00 - 10:00","10:00 - 12:00","12:00 - 14:00","14:00 - 16:00","16:00 - 18:00","18:00 - 20:00","20:00 - 22:00"};
        dropOffTimeList = new String[]{"14:00 - 16:00","16:00-18:00","18:30 - 20:00","20:30 - 23:00"};
        calculateDays();

    }
    @Override
    public boolean isPullToRefreshEnable() {
        return false;
    }

    public void calculateDays(){
        SimpleDateFormat curFormater = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        GregorianCalendar date = new GregorianCalendar();
        String[] dateStringArray = new String[14];
        for (int day = 0; day < 14; day++) {
            dateStringArray[day] = curFormater.format(date.getTime());
            date.roll(Calendar.DAY_OF_YEAR, true);
        }
    }


    public void showCustomProgressDialog(){

        final ProgressDialog dialog = new ProgressDialog(getActivity(),R.style.full_screen_dialog){
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
    }

    @Override
    public void onStartRefresh() {}

    @Override
    public void onVisible() {}

    private void checkFlowAndProceed() {
        progressDialog = ProgressDialog.show(getActivity(), "", "", true);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, SPLASH_MILLIS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continueImageView:
                replaceFragment(R.id.fragmentContainerLayout,new AddContactInfoFragment(),AddContactInfoFragment.TAG,true);
                break;
            case R.id.liteImageView:
                if(counter==0){
                    liteImageView.setImageResource(R.drawable.lite_imageview);
                    showCustomProgressDialog();
                    counter+=1;
                }
                plusRate = true;
                expressRate = true;
//                liteImageView.setImageResource(R.drawable.lite_imageview);
                plusImageView.setImageResource(R.drawable.plus_off);
                expressImageView.setImageResource(R.drawable.express_off);
                rateImageView.setImageResource(R.drawable.lite_rate);
                expressTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                plusTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                if (liteRate) {
                    liteRate = false;
                    rateImageView.setVisibility(View.VISIBLE);
                    liteTextView.setTextColor(getResources().getColor(R.color.greenColor));
                } else {
                    liteRate = true;
                    rateImageView.setVisibility(View.GONE);
                }
                break;
            case R.id.plusImageView:
                if(counter==1){
                    counter--;
                }
                liteRate = true;
                expressRate = true;
                plusImageView.setImageResource(R.drawable.plus_image);
                liteImageView.setImageResource(R.drawable.lite_off);
                expressImageView.setImageResource(R.drawable.express_off);
                rateImageView.setImageResource(R.drawable.plus_rate);
                liteTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                expressTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                if (plusRate) {
                    plusRate = false;
                    rateImageView.setVisibility(View.VISIBLE);
                    plusTextView.setTextColor(getResources().getColor(R.color.greenColor));
                } else {
                    plusRate = true;
                    rateImageView.setVisibility(View.GONE);
                }
                break;
            case R.id.expressImageView:
                liteRate = true;
                plusRate = true;
                expressImageView.setImageResource(R.drawable.express_image);
                liteImageView.setImageResource(R.drawable.lite_off);
                plusImageView.setImageResource(R.drawable.plus_off);
                rateImageView.setImageResource(R.drawable.express_rate);
                liteTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                plusTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                if (expressRate) {
                    expressRate = false;
                    rateImageView.setVisibility(View.VISIBLE);
                    expressTextView.setTextColor(getResources().getColor(R.color.greenColor));
                } else {
                    expressRate = true;
                    rateImageView.setVisibility(View.GONE);
                }
                break;
            case R.id.placeTextView:
                selectLocationDialog();
                break;
            case R.id.dropOffLinearLayout:
                showDropOffDialog();
                break;
            case R.id.pickUpLinearLayout:
                showPickUpTimeDialog();
                break;
            case R.id.liteTextView:
                plusRate = true;
                expressRate = true;
                liteImageView.setImageResource(R.drawable.lite_imageview);
                plusImageView.setImageResource(R.drawable.plus_off);
                expressImageView.setImageResource(R.drawable.express_off);
                rateImageView.setImageResource(R.drawable.lite_rate);
                expressTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                plusTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                if (liteRate) {
                    liteRate = false;
                    rateImageView.setVisibility(View.VISIBLE);
                    liteTextView.setTextColor(getResources().getColor(R.color.greenColor));
                } else {
                    liteRate = true;
                    rateImageView.setVisibility(View.GONE);
                }
                break;
            case R.id.plusTextView:
                liteRate = true;
                expressRate = true;
                plusImageView.setImageResource(R.drawable.plus_image);
                liteImageView.setImageResource(R.drawable.lite_off);
                expressImageView.setImageResource(R.drawable.express_off);
                rateImageView.setImageResource(R.drawable.plus_rate);
                liteTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                expressTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                if (plusRate) {
                    plusRate = false;
                    rateImageView.setVisibility(View.VISIBLE);
                    plusTextView.setTextColor(getResources().getColor(R.color.greenColor));
                } else {
                    plusRate = true;
                    rateImageView.setVisibility(View.GONE);
                }
                break;
            case R.id.expressTextView:
                liteRate = true;
                plusRate = true;
                expressImageView.setImageResource(R.drawable.express_image);
                liteImageView.setImageResource(R.drawable.lite_off);
                plusImageView.setImageResource(R.drawable.plus_off);
                rateImageView.setImageResource(R.drawable.express_rate);
                liteTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                plusTextView.setTextColor(getResources().getColor(R.color.darkGreen));
                if (expressRate) {
                    expressRate = false;
                    rateImageView.setVisibility(View.VISIBLE);
                    expressTextView.setTextColor(getResources().getColor(R.color.greenColor));
                } else {
                    expressRate = true;
                    rateImageView.setVisibility(View.GONE);
                }
                break;
        }
    }

    public void selectLocationDialog() {

        moreOptionsDialog = new Dialog(getActivity(),R.style.full_screen_dialog);
        moreOptionsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        moreOptionsDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.more_options_layout, null);
        Window window = moreOptionsDialog.getWindow();
        WindowManager.LayoutParams windowsLayoutParams = window.getAttributes();

        windowsLayoutParams.copyFrom(window.getAttributes());
        windowsLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowsLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        windowsLayoutParams.gravity = Gravity.BOTTOM;
        windowsLayoutParams.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowsLayoutParams);

        moreOptionsDialog.setTitle(null);
        moreOptionsDialog.setContentView(v);

        Button lahoreButton = (Button) v.findViewById(R.id.lahoreButton);
        lahoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeTextView.setText(R.string.lahore);
                moreOptionsDialog.dismiss();
            }
        });
        Button bhlButton = (Button) v.findViewById(R.id.bhlButton);
        bhlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeTextView.setText(R.string.bahalwapur);
                moreOptionsDialog.dismiss();
            }
        });
        Button chisButton = (Button) v.findViewById(R.id.chisButton);
        chisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeTextView.setText(R.string.chishtian);
                moreOptionsDialog.dismiss();
            }
        });
        moreOptionsDialog.show();
    }


    public void showPickUpTimeDialog() {

        moreOptionsDialog = new Dialog(getActivity(),R.style.full_screen_dialog);
        moreOptionsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        moreOptionsDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.time_listview_layout, null);
        Window window = moreOptionsDialog.getWindow();
        WindowManager.LayoutParams windowsLayoutParams = window.getAttributes();

        windowsLayoutParams.copyFrom(window.getAttributes());
        windowsLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowsLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        windowsLayoutParams.gravity = Gravity.BOTTOM;
        windowsLayoutParams.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowsLayoutParams);

        moreOptionsDialog.setTitle(null);
        Button doneButton = (Button) v.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreOptionsDialog.dismiss();
            }
        });

        SimpleDateFormat curFormater = new SimpleDateFormat("dd.MMMM ");
        GregorianCalendar date = new GregorianCalendar();
        String[] dateStringArray = new String[14];

        for (int day = 0; day < 14; day++) {
            dateStringArray[day] = curFormater.format(date.getTime());
            date.roll(Calendar.DAY_OF_YEAR, true);
        }

        final ListView calendarDateListView = (ListView) v.findViewById(R.id.calendarDateListView);
        final ListView calendarTimeListView = (ListView) v.findViewById(R.id.calendarTimeListView);
        final TimeListAdapter adapter = new TimeListAdapter(getContext(),dateStringArray);
        calendarDateListView.setAdapter(adapter);
        calendarDateListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        calendarTimeListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



        calendarDateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                String selectedFromList = (String) calendarDateListView.getItemAtPosition(position);
                System.out.println("List Position = : " + selectedFromList);
//                Toast.makeText(getActivity(),""+selectedFromList,Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(),parent.getItemIdAtPosition(position)+"isSelected",Toast.LENGTH_LONG).show();
                calendarTimeListView.setItemChecked(position, true);
                adapter.notifyDataSetChanged();
                Calendar c1 = Calendar.getInstance();
                pickUpDateTexView.setText(selectedFromList);

                TimeListAdapter pickUpDefaultAdapter = new TimeListAdapter(getActivity(), pickUpDateList);
                calendarTimeListView.setAdapter(pickUpDefaultAdapter);
//                calendarTimeListView.setSelection(0);
                calendarDateListView.setItemChecked(0, true);
//                calendarTimeListView.getSelectedView().setSelected(true);
                switch (position){
                    case 0:
                        TimeListAdapter pickUpListAdapterOne = new TimeListAdapter(getActivity(), pickUpDateList);
                        calendarTimeListView.setAdapter(pickUpListAdapterOne);
                        pickUpDateTexView.setText("Today");
                        break;
                    case 1:
                        TimeListAdapter pickUpListAdapterTwo = new TimeListAdapter(getActivity(), pickUpTimeList);
                        calendarTimeListView.setAdapter(pickUpListAdapterTwo);
                        pickUpDateTexView.setText("Tomorrow");
                        break;
                }
            }
        });

        calendarTimeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                String selectedFromPickUpList = (String) calendarTimeListView.getItemAtPosition(position);
                System.out.println("List Position = : " + selectedFromPickUpList);
//                Toast.makeText(getActivity(),""+selectedFromList,Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(),parent.getItemIdAtPosition(position)+"isSelected",Toast.LENGTH_LONG).show();
                calendarTimeListView.setItemChecked(position, true);
                pickUpTimeTexView.setText(selectedFromPickUpList);
            }
        });
        moreOptionsDialog.setContentView(v);
        moreOptionsDialog.show();

    }

    public void showDropOffDialog() {

        moreOptionsDialog = new Dialog(getActivity(),android.R.style.Theme_WallpaperSettings);
        moreOptionsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

//        moreOptionsDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        moreOptionsDialog.getWindow().getAttributes().height = WindowManager.LayoutParams.WRAP_CONTENT;
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.time_listview_layout, null);
        Window window = moreOptionsDialog.getWindow();
        WindowManager.LayoutParams windowsLayoutParams = window.getAttributes();

        windowsLayoutParams.copyFrom(window.getAttributes());
//        windowsLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        windowsLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        windowsLayoutParams.gravity = Gravity.BOTTOM;
        windowsLayoutParams.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowsLayoutParams);

        moreOptionsDialog.setTitle(null);
        Button doneButton = (Button) v.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreOptionsDialog.dismiss();
            }
        });

        SimpleDateFormat curFormater = new SimpleDateFormat("dd.MMMM ");
        GregorianCalendar date = new GregorianCalendar();
        date.set(Calendar.DAY_OF_MONTH,03);

        String[] dateStringArray = new String[14];

        for (int day = 0; day < 14; day++) {
            dateStringArray[day] = curFormater.format(date.getTime());
            date.roll(Calendar.DAY_OF_YEAR , true);
        }

        final ListView calendarDateListView = (ListView) v.findViewById(R.id.calendarDateListView);
        final ListView calendarTimeListView = (ListView) v.findViewById(R.id.calendarTimeListView);
        final TimeListAdapter adapter = new TimeListAdapter(getContext(),dateStringArray);
        calendarDateListView.setAdapter(adapter);
        calendarDateListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        calendarTimeListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        calendarDateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                String selectedFromList = (String) calendarDateListView.getItemAtPosition(position);
                System.out.println("List Position = : " + selectedFromList);
//                Toast.makeText(getActivity(),""+selectedFromList,Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(),parent.getItemIdAtPosition(position)+"isSelected",Toast.LENGTH_LONG).show();
                calendarDateListView.setItemChecked(position, true);
                adapter.notifyDataSetChanged();
                Calendar c1 = Calendar.getInstance();
                dropOffDateTexView.setText(selectedFromList);

                TimeListAdapter dropOffDefaultAdapter = new TimeListAdapter(getActivity(), dropOffDateList);
                calendarTimeListView.setAdapter(dropOffDefaultAdapter);
                switch (position){
                    case 0:
                        TimeListAdapter dropOffFristAdapter = new TimeListAdapter(getActivity(), dropOffDateList);
                        calendarTimeListView.setAdapter(dropOffFristAdapter);
                        break;
                    case 1:
                        TimeListAdapter dropOffSecondAdapter = new TimeListAdapter(getActivity(), dropOffTimeList);
                        calendarTimeListView.setAdapter(dropOffSecondAdapter);
                        break;
                }
            }
        });

        calendarTimeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                String selectedFromDropOffList = (String) calendarTimeListView.getItemAtPosition(position);
                System.out.println("List Position = : " + selectedFromDropOffList);
//                Toast.makeText(getActivity(),""+selectedFromList,Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(),parent.getItemIdAtPosition(position)+"isSelected",Toast.LENGTH_LONG).show();
                calendarTimeListView.setItemChecked(position, true);
                dropOffTimeTexView.setText(selectedFromDropOffList);
            }
        });
        moreOptionsDialog.setContentView(v);
        moreOptionsDialog.show();
    }

}