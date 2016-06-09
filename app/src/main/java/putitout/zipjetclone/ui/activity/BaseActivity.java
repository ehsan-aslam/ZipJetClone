package putitout.zipjetclone.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.interfaces.OnDateTimePickerListener;

/**
 * Created by SA on 5/27/2016.
 */
public abstract class BaseActivity extends FragmentActivity {

    public static final int REQUEST_CAMERA = 1;
    public static final int FROM_GALLERY = 2;
    public static final int PICTURE_CROP = 3;


    public ProgressDialog progressDialog;
    private static FragmentActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);

//        addUncaughtException();
    }

//    private void addUncaughtException() {
//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
//                ZUtil.writeToFile(paramThrowable.getStackTrace().toString());
//                KLog.info("KIDLR-EXC: "+paramThrowable.getStackTrace().toString());
//                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//            }
//        });
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        activity = this;
    }

    public abstract void initWidget();

    public void replaceFragment(int contentId, Fragment fragment, String fragmentTag, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(contentId, fragment, fragmentTag);
        if(addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentTag);
        }
        fragmentTransaction.commit();
    }

    public ProgressDialog getProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setCancelable(true);
        }
        return progressDialog;
    }

    public static FragmentActivity getActivity() {
        return activity;
    }

    public void takePictureFromCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    public void takePictureFromGalleryIntent() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(
                Intent.createChooser(intent, "Select File"),
                FROM_GALLERY);
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


}
