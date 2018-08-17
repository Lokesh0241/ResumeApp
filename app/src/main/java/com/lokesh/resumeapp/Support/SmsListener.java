package com.lokesh.resumeapp.Support;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kishumewara on 13/08/18.
 */

public class SmsListener extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification_id";
    public static String NOTIFICATION = "notification";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("messageBody", intent.getAction());
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            try {
                String messageBody = "";
                for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    messageBody = smsMessage.getMessageBody();
                }
               /* Intent messageReceived = new Intent(SVPreferences.SMS_RECEIVED);
                messageReceived.putExtra("sms", messageBody);
                context.sendBroadcast(messageReceived); */// when receiving it somewhere in your app, subString the additional text and leave only the code, then place it in the editText and do your verification
            } catch (Exception ex) {
                ex.printStackTrace();
            }


            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


            Notification notification = intent.getParcelableExtra(NOTIFICATION);
            int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);

            String title = intent.getStringExtra("title");
            String heading = intent.getStringExtra("heading");
            String date = intent.getStringExtra("date");
            String time = intent.getStringExtra("time");
            String message = intent.getStringExtra("message");
            String Remind_id = intent.getStringExtra("Remind_id");
            String day__date_str = intent.getStringExtra("day__date_str");

            notificationManager.notify(notificationId, notification);


            //  Utils.showRemindDialog_notification(heading,message, title,date,time,Remind_id,day__date_str);


        /*List<NotificationItemModel> notificationItemModels = new ArrayList<>();
        notificationItemModels = MyApplication.getInstance().getNotificationItemModels();
        notificationItemModels.add(new NotificationItemModel("title","heading","date"));
        MyApplication.getInstance().setNotificationItemModels(notificationItemModels);*/


        }
    }
}