package com.example.pranay.todo_4;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReciever extends BroadcastReceiver {
    static int i=0;
    @Override
    public void onReceive(Context context, Intent intent) {
      //  Toast.makeText(context, "inside on Recieve", Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle("My Notification")
                .setAutoCancel(false)
                .setContentText("Alarm !!!");

        Intent resultintent = new Intent(context,MainActivity.class);
        PendingIntent resultpendingintent = PendingIntent.getActivity(context,i++,resultintent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(resultpendingintent);
        NotificationManager manager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.notify(i++,builder.build());

    }
}
