package putitout.zipjetclone.ui.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.interfaces.OnDateTimePickerListener;

/**
 * Created by SA on 5/27/2016.
 */
public abstract class BaseFragment extends Fragment {

    Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    public int datePickerYear = calendar.get(Calendar.YEAR);
    public int datePickerMonth = calendar.get(Calendar.MONTH);
    public int datePickerDay = calendar.get(Calendar.DAY_OF_MONTH);
    ProgressDialog progressDialog, messageDialog;
    private SwipeRefreshLayout swipeRefreshContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void showDatePickerDialog(DatePickerDialog.OnDateSetListener datePickerListener) {
        datePickerDialog = new DatePickerDialog(getActivity(), datePickerListener, datePickerYear, datePickerMonth, datePickerDay);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        Date minDate = new Date();
        minDate.setYear(minDate.getYear() - 8);
        datePickerDialog.getDatePicker().setMinDate(minDate.getTime());
        datePickerDialog.show();
    }

    public void showDateTimePickerDialog(final OnDateTimePickerListener datePickerListener) { //, KidlrDate thatDay
        final View dialogView = View.inflate(getActivity(), R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

//        DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
//        TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);
//        if(thatDay != null) {
//            datePicker.init(thatDay.getYear(), thatDay.getMonth(), thatDay.getMinute(), null);
//            timePicker.setCurrentHour(thatDay.getHour());
//            timePicker.setCurrentMinute(thatDay.getMinute());
//        }

        dialogView.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
                TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);

                Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute());

                String dateTime = invalidateValue(datePicker.getYear()) + "-" + invalidateValue(datePicker.getMonth() + 1)
                        + "-" + invalidateValue(datePicker.getDayOfMonth()) + " " + invalidateValue(timePicker.getCurrentHour())
                        + ":" + invalidateValue(timePicker.getCurrentMinute()) + ":" + invalidateValue(Calendar.getInstance().get(Calendar.SECOND));

                datePickerListener.onDateTimePicked(dateTime, calendar.getTimeInMillis());
                alertDialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.setView(dialogView);
        alertDialog.show();
    }

    private String invalidateValue(int value) {
        if(value > 0 && value < 10) {
            return "0" + value;
        } else {
            return "" + value;
        }
    }

    public void initWidget(View view) {
//        if(isPullToRefreshEnable()) {
//            swipeRefreshContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
//            if(swipeRefreshContainer != null) {
//                // Setup refresh listener which triggers new data loading
//                swipeRefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        onStartRefresh();
//                    }
//                });
//                // Configure the refreshing colors
//                swipeRefreshContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
//                        android.R.color.holo_green_light,
//                        android.R.color.holo_orange_light,
//                        android.R.color.holo_red_light);
//            }
//        }
    }

    public void replaceFragment(int contentId, Fragment fragment, String fragmentTag, boolean addToBackStack) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(contentId, fragment, fragmentTag);
        if(addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentTag);
        }
        fragmentTransaction.commit();
    }

    public ProgressDialog getProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        return progressDialog;
    }

    public ProgressDialog getMessageProgressDialog(String message, Context context) {
        messageDialog = new ProgressDialog(context);
        messageDialog.setMessage(message);
        messageDialog.setCancelable(false);
        messageDialog.setCanceledOnTouchOutside(false);
        return messageDialog;
    }

    public boolean isRefreshing() {
        if(swipeRefreshContainer != null) {
            return swipeRefreshContainer.isRefreshing();
        } else {
            return false;
        }
    }



    public void stopRefreshing() {
        if(swipeRefreshContainer != null) {
            swipeRefreshContainer.setRefreshing(false);
        }
    }

    public abstract boolean isPullToRefreshEnable();
    public abstract void onStartRefresh();
    public abstract void onVisible();
}


