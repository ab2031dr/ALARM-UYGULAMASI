package com.example.akillialarm;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {

    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showNotification("Alarm Çalıyor!", "Alarm zamanı geldi.");
        return START_STICKY;
    }

    private void showNotification(String title, String text) {
        String channelId = "alarm_channel";
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Alarm Kanalı", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_alarm)
                .setSound(alarmSound)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        manager.notify(1, builder.build());
    }
}
