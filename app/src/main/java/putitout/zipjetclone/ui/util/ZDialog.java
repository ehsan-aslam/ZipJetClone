package putitout.zipjetclone.ui.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import putitout.zipjetclone.ui.activity.BaseActivity;
import putitout.zipjetclone.ui.interfaces.OnDialogButtonClickListener;

/**
 * Created by SA on 6/9/2016.
 */
public class ZDialog {
    private static AlertDialog alert;

    public static void showAlert(Context context, String message,
                                 String buttonNegativeText) {
        if(alert!=null && alert.isShowing()) {
            return;
        } else {
            alert = new AlertDialog.Builder(context)
                    .setMessage(message)
                    .setCancelable(true)
                    .setNegativeButton(buttonNegativeText, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            alert.show();
        }
    }

    public static void showAlert(int messageId,
                                 int buttonNegativeTextId) {
        if(alert!=null && alert.isShowing()) {
            return;
        } else {
            alert = new AlertDialog.Builder(BaseActivity.getActivity()).setMessage(BaseActivity.getActivity().getString(messageId))
                    .setCancelable(true)
                    .setNegativeButton(BaseActivity.getActivity().getString(buttonNegativeTextId), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            alert.show();
        }
    }

    public static void showAlert(Context context, String message,
                                 String buttonPositiveText, String buttonNegativeText,
                                 final OnDialogButtonClickListener dialogListener,
                                 final int requestCode) {
        if(alert!=null && alert.isShowing()) {
            return;
        } else {
            alert = new AlertDialog.Builder(context).setMessage(message)
                    .setCancelable(true)
                    .setPositiveButton(buttonPositiveText, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialogListener.onDialogPositiveButtonClick(requestCode);
                            dialog.dismiss();
                        }
                    }).setNegativeButton(buttonNegativeText, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialogListener.onDialogNegativeButtonClick(requestCode);
                            dialog.dismiss();
                        }
                    }).create();
            alert.show();
        }
    }

    public static void showAlert(Context context, String message,
                                 String buttonPositiveText,
                                 final OnDialogButtonClickListener dialogListener,
                                 final int requestCode) {
        if(alert!=null && alert.isShowing()) {
            return;
        } else {
            alert = new AlertDialog.Builder(context).setMessage(message)
                    .setCancelable(true)
                    .setPositiveButton(buttonPositiveText, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialogListener.onDialogPositiveButtonClick(requestCode);
                            dialog.dismiss();
                        }
                    }).create();
            alert.show();
        }
    }
}
