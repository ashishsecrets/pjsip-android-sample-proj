package com.example.freeswitchandroid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;

public class RingTonePlayingService extends Service
{
    private Ringtone ringtone;
    private RingtoneManager ringtoneManager;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Uri ringtoneUri = null;
        ringtoneManager = new RingtoneManager(getApplicationContext());
        ringtoneManager.setType(RingtoneManager.TYPE_RINGTONE);
        if(Settings.System.canWrite(getApplicationContext()))
            ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);
        ringtone.play();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        ringtone.stop();
        ringtoneManager.stopPreviousRingtone();
    }
}
