package com.example.laptoptcc.firebasenotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by laptopTCC on 3/19/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static String TAG = MyFirebaseMessagingService.class.toString();
    public void onMessageReceived(RemoteMessage remoteMessage){
        Log.d(TAG,"From: "+ remoteMessage.getFrom());
        if(remoteMessage.getData().size() > 0){
            Log.d(TAG,"Message data payload: "+remoteMessage.getData());
        }

        if(remoteMessage.getNotification() != null){
            Log.d(TAG,"Message notification body:"+ remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String body) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        //set sound of notificaiton
        Uri notificaitonSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("Firebase clound messaging")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificaitonSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }


    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
