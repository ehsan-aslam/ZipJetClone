package putitout.zipjetclone.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import putitout.zipjetclone.R;

/**
 * Created by Ehsan on 5/29/2016.
 */
public class ZUtil {

    public static final String APP_ID = "Zipjet";
    public static final String KEY_APPLICATION_PACKAGE_NAME = "putitout.zipjetclone";
    public static final String KEY_TAG = "Zipjet";
    public static final String KEY_ENCODING_TYPE = "SHA";

    public static final String KEY_SERVER_NO_RESPONSE = "null";

    public static final int KEY_FRIEND = 1;
    public static final int KEY_FAMILY = 2;
    public static final int KEY_NO_RELATIONSHIP = 3;
    public static final SimpleDateFormat PARSE_SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final String START_DATE = "2015-01-01 00:00:00";
    public static boolean isLogin = false;
    public static boolean isUpdated = false;

    public static final String AUTH_TOKEN_KEY = "{sPjZze9s@4hyBAieLdWJFz2juAdgnnRhsTVC>Wih))J9WT(kr";

    // Keys for User Model
    public static final String KEY_USERNAME = "username";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_MIDDLE_NAME = "middle_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_REGISTRATION_TYPE = "registration_origin";
    public static final String KEY_STATUS = "status";
    public static final String KEY_TYPE = "type";
    public static final String KEY_GENDER_MALE = "male";
    public static final String KEY_GENDER_FEMALE = "female";
    public static final String KEY_REGISTRATION_FACEBOOK = "facebook";
    public static final String KEY_REGISTRATION_GOOGLE = "google";
    public static final String KEY_STANDARD = "standard";
    public static final String KEY_NORMAL = "1";
    public static final String KEY_INVITEES = "invitees";
    public static final String KEY_SOCIAL_ID = "social_id";
    public static final String KEY_SOCIAL_EMAIL = "email";
    public static final String KEY_SOCIAL_REGISTRATION_ORIGIN = "registration_origin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_CHILDREN_IDS = "children_ids";
    public static final String KEY_SHARED_WITH = "shared_with";
    public static final String KEY_VISIBILITY= "visibility";
    public static final String KEY_START_DATE= "start_date";
    public static final String KEY_END_DATE= "end_date";


    public static final String KEY_MOBILE = "mobileNumber";


    public static final String KEY_LATITUDE = "lat";

    public static final String KEY_LONGITUDE = "long";

    public static final String KEY_RATE = "selectedRate";

    public static final String KEY_LITE = "LITE PACKAGE";
    public static final String KEY_PLUS = "PLUS PACKAGE";
    public static final String KEY_EXPRESS = "EXPRESS PACKAGE";



    public static final String KEY_SERVER_RESPONSE_CHILD_ID = "child_id";
    public static final String KEY_SERVER_RESPONSE_CHILDREN = "children";
    public static final String KEY_SERVER_RESPONSE_ID = "id";
    public static final String KEY_SERVER_RESPONSE_BIRTHDAY = "birthday";
    public static final String KEY_SERVER_RESPONSE_WEIGHT = "weight";
    public static final String KEY_SERVER_RESPONSE_HEIGHT = "height";
    public static final String KEY_SERVER_RESPONSE_AGE = "age";
    public static final String KEY_SERVER_RESPONSE_YEARS = "years";
    public static final String KEY_SERVER_RESPONSE_MONTHS = "months";
    public static final String KEY_SERVER_RESPONSE_DAYS = "days";
    public static final String KEY_SERVER_RESPONSE_EXTENSION = "extension";
    public static final String KEY_SERVER_RESPONSE_NOTIFICATION_TYPE = "notification_type";

    public static final String KEY_SERVER_RESPONSE_ORIGINAL_NAME = "original_name";
    public static final String KEY_SERVER_RESPONSE_IS_POST = "is_post";
    public static final String KEY_SERVER_RESPONSE_IS_MILESTONE = "is_milestone";
    public static final String KEY_SERVER_RESPONSE_POST_TYPE = "post_type";
    public static final String KEY_SERVER_RESPONSE_MILESTONE_TYPE = "milestone_type";
    public static final String KEY_SERVER_RESPONSE_URL = "url";
    public static final String KEY_SERVER_RESPONSE_THUMB = "thumb";



    public static final String KEY_USERS = "users";
    public static final String KEY_EVENTS = "events";
    public static final String KEY_MEDICAL_REPORTS = "medical_reports";
    public static final String KEY_GIFTS = "gifts";
    public static final String KEY_QUESTIONS = "questions";
    public static final String KEY_MILESTONES = "milestones";
    public static final String KEY_VENDORS = "vendors";
    public static final String KEY_POSTS = "posts";
    public static final String KEY_MAIN_CATEGORY = "main_image";
    public static final String KEY_MAIN_CATEGORY_VIDEO = "main_video";
    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_RELATION = "relation";
    public static final String KEY_REQUEST_ID = "request_id";
    public static final String KEY_SEARCH_FRIEND = "name_string";
    public static final String KEY_FRIEND_ID = "friend_id";
    public static final String KEY_IS_FRIEND = "friend";

    public static final String KEY_CURRENT_PASSWORD= "current_password";
    public static final String KEY_NEW_PASSWORD= "new_password";

    //Api Service Keys
    public static final String KEY_SERVER_RESPONSE_STATUS = "status";
    public static final String KEY_SERVER_RESPONSE_MESSAGE = "message";
    public static final String KEY_SERVER_RESPONSE_DATA = "data";
    public static final String KEY_SERVER_RESPONSE_FAILURE = "failure";
    public static final String KEY_SERVER_RESPONSE_SUCCESS = "success";
    public static final String KEY_SERVER_RESPONSE_TOKEN = "access_token";
    public static final String KEY_SERVER_RESPONSE_USER_ID = "user_id";
    public static final String KEY_SERVER_RESPONSE_DETAILS = "details";
    public static final String KEY_SERVER_RESPONSE_REGISTRATION_ORIGIN = "registration_origin";
    public static final String KEY_SERVER_RESPONSE_FIRST_NAME = "first_name";
    public static final String KEY_SERVER_RESPONSE_MIDDLE_NAME = "middle_name";
    public static final String KEY_SERVER_RESPONSE_LAST_NAME = "last_name";
    public static final String KEY_SERVER_RESPONSE_GENDER = "gender";
    public static final String KEY_SERVER_RESPONSE_USER_STATUS = "status";
    public static final String KEY_SERVER_RESPONSE_TYPE = "type";
    public static final String KEY_SERVER_RESPONSE_DATE_CREATED = "date_created";
    public static final String KEY_SERVER_RESPONSE_DATE_MODIFIED = "date_modified";
    public static final String KEY_SERVER_RESPONSE_BABY_ID = "baby_id";
    public static final String KEY_SERVER_RESPONSE_CONNECTION = "connections";
    public static final String KEY_SERVER_RESPONSE_FRIEND_REQUESTS = "friend_requests";
    public static final String KEY_SERVER_RESPONSE_ALBUM_ID = "album_id";
    public static final String KEY_SERVER_RESPONSE_VIDEO_ID = "video_id";
    public static final String KEY_SERVER_RESPONSE_ALBUM_MEDIA_IDS = "album_media_ids";
    public static final String KEY_SERVER_RESPONSE_MEDICAL_ID = "medical_id";
    public static final String KEY_SERVER_RESPONSE_POST_ID = "post_id";
    public static final String KEY_SERVER_RESPONSE_MEDIA_SYSTEM = "media_system_name";
    public static final String KEY_SERVER_RESPONSE_REQUESTS = "requests";
    public static final String KEY_SERVER_RESPONSE_REQUEST_ID = "request_id";
    public static final String KEY_SERVER_RESPONSE_SENDER_ID = "sender_id";
    public static final String KEY_SERVER_RESPONSE_INVITE_SENT_ON = "invite_sent_on";
    public static final String KEY_SERVER_RESPONSE_NAME = "name";
    public static final String KEY_SERVER_RESPONSE_RELATION = "relation";
    public static final String KEY_SERVER_RESPONSE_IMAGE = "img";
    public static final String KEY_SERVER_RESPONSE_READ_STATUS = "read_status";
    public static final String KEY_SERVER_RESPONSE_NOTIFY_USER = "notify_user";
    public static final String KEY_SERVER_RESPONSE_RELATED_TO = "related_to";
    public static final String KEY_SERVER_RESPONSE_RELATED_ID = "related_id";
    public static final String KEY_SERVER_RESPONSE_ADDITIONAL_INFO = "additional_info";
    public static final String KEY_SERVER_RESPONSE_IMAGES = "images";

    public static final String KEY_SERVER_RESPONSE_INVITEE_DECIDED_ON = "invitee_decided_on";
    public static final String KEY_SERVER_RESPONSE_RECEIVER_ID = "receiver_id";
    public static final String KEY_SERVER_RESPONSE_USER_PROFILE = "user_profile";
    public static final String KEY_SERVER_RESPONSE_CHILDREN_PROFILES = "children_profiles";
    public static final String KEY_SERVER_RESPONSE_FRIEND_SUGGESTION = "friend_suggestions";
    public static final String KEY_SERVER_RESPONSE_REQUEST_SENDER = "request_sender";
    public static final String KEY_SERVER_RESPONSE_REQUEST_RECEIVER = "request_receiver";
    public static final String KEY_SERVER_RESPONSE_FRIENDS = "friends";
    public static final String KEY_SERVER_RESPONSE_FRIEND_AS_RECEIVER = "friend_as_receiver";
    public static final String KEY_SERVER_RESPONSE_FRIEND_AS_SENDER = "friend_as_sender";
    public static final String KEY_SERVER_RESPONSE_USERS = "users";
    public static final String KEY_SERVER_RESPONSE_EMAIL = "email";
    public static final String KEY_SERVER_RESPONSE_ALL_FRIENDS = "all_friends";
    public static final String KEY_SERVER_RESPONSE_DESCRIPTION = "description";
    public static final String KEY_SERVER_RESPONSE_PICTURE_COUNT = "total_no_of_pictures";
    public static final String KEY_SERVER_RESPONSE_VISIBILITY = "visibility";
    public static final String KEY_SERVER_RESPONSE_SHARING = "sharings";
    public static final String KEY_SERVER_RESPONSE_MIME_TYPE = "mime_type";
    public static final String KEY_SERVER_RESPONSE_SIZE = "size";
    public static final String KEY_SERVER_RESPONSE_SYSTEM_NAME = "system_name";
    public static final String KEY_SERVER_RESPONSE_ALBUMS_ID = "albums_id";
    public static final String KEY_SERVER_RESPONSE_SMILE_COUNT = "smile_count";
    public static final String KEY_SERVER_RESPONSE_COMMENT_COUNT = "comment_count";
    public static final String KEY_SERVER_RESPONSE_AUTHOR = "author";
    public static final String KEY_SERVER_RESPONSE_POST_FROM = "post_from";
    public static final String KEY_SERVER_RESPONSE_NO_OF_POSTS = "no_of_posts";



    /**
     * BroadCast
     */
    public static final String BROADCAST_ACTION_LOGOUT = "BROADCAST_ACTION_LOGOUT";
    public static final String BROADCAST_ACTION_KILL_PREVIOUS_ACTIVITIES = "BROADCAST_ACTION_KILL_PREVIOUS_ACTIVITIES";
    public static final String BROADCAST_ACTION_IMAGE_UPLOADING = "BROADCAST_ACTION_IMAGE_UPLOADING";
    public static final String BROADCAST_ACTION_IMAGE_UPLOADING_INDEX = "BROADCAST_ACTION_IMAGE_UPLOADING_INDEX";
    public static final String BROADCAST_ACTION_IMAGE_UPLOADING_TOTAL_SIZE = "BROADCAST_ACTION_IMAGE_UPLOADING_TOTAL_SIZE";
    public static final String BROADCAST_ACTION_UPLOADING_COMPLETE = "BROADCAST_ACTION_UPLOADING_COMPLETE";
    public static final String BROADCAST_ACTION_UPLOADING_PROGRESS = "BROADCAST_ACTION_UPLOADING_PROGRESS";
    public static final String BROADCAST_ACTION_UPDATE_ON_UPLOADING_COMPLETE = "BROADCAST_ACTION_UPDATE_ON_UPLOADING_COMPLETE";
    public static final String BROADCAST_ACTION_TRIMMING_COMPLETE = "BROADCAST_ACTION_TRIMMING_COMPLETE";



    public static boolean isJsonResponse(String response) {
        try {
            JSONObject parseJsonResponse = new JSONObject(response);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void generateHashKey(Context context, String encodingType) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    KEY_APPLICATION_PACKAGE_NAME, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance(encodingType);
                md.update(signature.toByteArray());
                Log.d(ZUtil.KEY_TAG,
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String convertStringToSHA256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void showAlert(Context context, String message) {
        new android.app.AlertDialog.Builder(context).setMessage(message).setCancelable(false)
                .setNegativeButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void doSoftInputHide(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

//    public static void hideSoftKeyboard(){
////  View view = activity.findViewById(android.R.id.content);
//InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//    inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }


    public static void showNetworkErrorAlertDialog(Context context) {
        ZDialog.showAlert(context,
                context.getString(R.string.noInternet),
                context.getString(R.string.ok));
    }

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
//        if (email_off_icon != null) email_off_icon = email_off_icon.trim();
//        pattern = Pattern.compile(EMAIL_PATTERN);
//        matcher = pattern.matcher(email_off_icon);
//        return matcher.matches();
    }

    public static void showSaveDataAlert(Context context, DialogInterface.OnClickListener continueButton, DialogInterface.OnClickListener cancelButton) {
        new android.app.AlertDialog.Builder(context).setMessage(context.getString(R.string.saveData)).setCancelable(true)
                .setPositiveButton(context.getString(R.string.continueNext), continueButton)
                .setNegativeButton(context.getString(R.string.cancel), cancelButton).show();
    }

}
