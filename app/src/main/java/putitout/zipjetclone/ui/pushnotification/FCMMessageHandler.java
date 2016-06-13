package putitout.zipjetclone.ui.pushnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.activity.MainActivity;

/**
 * Created by SA on 6/13/2016.
 */
public class FCMMessageHandler extends FirebaseMessagingService {
    public static final int MESSAGE_NOTIFICATION_ID = 435345;
    String from;

    public static final String TAG = "FCMMessageHandler";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        String from = remoteMessage.getFrom();

        Log.d(TAG, "FCM Token from: " + from);

        Log.d(TAG, "FCM data: " + data);

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        createNotification(notification);
    }

    private void createNotification(RemoteMessage.Notification notification) {
        Context context = getBaseContext();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("noti",notification.getBody());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        long[] pattern = {500,500,500,500,500,500,500,500,500};
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.push_logo).setContentText(notification.getBody()).
                        setContentTitle("ZipJet").
                        setSound(defaultSoundUri).
                        setLights(Color.BLUE, 500, 500).
                        setVibrate(pattern).
                        setStyle(new NotificationCompat.InboxStyle()).
                        setAutoCancel(true).setContentIntent(pendingIntent);

        Log.d(TAG, "FCM data: " + notification.getBody());
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());


//        Notification notificatio = new Notification();
//        notificatio.flags |= Notification.FLAG_AUTO_CANCEL;
//
//        // Play default notification sound
//        notificatio.defaults |= Notification.DEFAULT_SOUND;
//
//        // Vibrate if vibrate is enabled
//        notificatio.defaults |= Notification.DEFAULT_VIBRATE;


    }
}