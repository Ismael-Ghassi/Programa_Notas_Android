package com.example.t1_practica;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

/**
 * Con esta clase iniciaremos la alarma.
 */
public class Alarma extends Service {
    MediaPlayer aviso; //Es el sonido de la alarma

    public void onCreate(){
        super.onCreate();
        aviso= MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        aviso.setLooping(true); //Para que la alarma de repita
    }


    /**
     *Este metodo inicia la alarma
     * @return devuelve un Start_sticky que revive el servicio
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        aviso.start();
        return START_STICKY;
    }


    /**
     * Este metodo sirve para parar la alarma
     */
    public void onDestroy(){
        super.onDestroy();
        aviso.stop();
    }





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
