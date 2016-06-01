package putitout.zipjetclone.ui.util;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.text.Spanned;

import java.util.regex.Pattern;

import putitout.zipjetclone.R;

/**
 * Created by Ehsan on 5/29/2016.
 */
public class ZUtil {

    public static final String APP_ID = "Zipjet";


    public static boolean isEmpty(String string) {
        return (string.length() > 0 ? false : true);
    }

    public static Spanned getErrorHtmlFromString(String message) {
        return Html.fromHtml("<font color='red'>" + message + "</font>");
    }

    public static boolean emailValidator(String email) {
        Pattern EMAIL_ADDRESS_PATTERN = Pattern
                .compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                        + "[a-zA-Z0-9][a-zA-Z0-9\\-\\_]{0,64}" + "(" + "\\."
                        + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
//        Pattern pattern;
//        Matcher matcher;
//        final String EMAIL_PATTERN =
//        //"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//        if (email != null) email = email.trim();
//        pattern = Pattern.compile(EMAIL_PATTERN);
//        matcher = pattern.matcher(email);
//        return matcher.matches();
    }

    public static void showSaveDataAlert(Context context, DialogInterface.OnClickListener fromGallerydialogListner, DialogInterface.OnClickListener fromCameradialogListner) {
        new android.app.AlertDialog.Builder(context).setMessage(context.getString(R.string.saveData)).setCancelable(true)
                .setPositiveButton(context.getString(R.string.continueNext), fromGallerydialogListner)
                .setNegativeButton(context.getString(R.string.cancel), fromCameradialogListner).show();
    }

}
