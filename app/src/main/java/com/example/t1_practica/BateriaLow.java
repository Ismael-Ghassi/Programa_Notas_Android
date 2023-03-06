package com.example.t1_practica;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Esta clase nos servira para avisar al usuario de que le queda poca bateria.
 */
public class BateriaLow extends BroadcastReceiver {
    //boolean crear=false;

    /**
     *Este metodo comprobara en todo momenro si ha bajado la bateria.
     * Si ha bajado emitira el aviso.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())){
            Toast.makeText(context,"Aviso, bateria baja", Toast.LENGTH_LONG).show();
        }
        //Notas bateria = new Notas("bateria"+"Pon el telefono a cargar."+""+""); // Es la nota de bateria baja
}


/*
    public boolean getCrear(){
        return crear;
    }

 */
}
