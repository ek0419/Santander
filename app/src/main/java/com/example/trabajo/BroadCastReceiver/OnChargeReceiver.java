package com.example.trabajo.BroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.trabajo.Utilerias.UTUtils;

public class OnChargeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Conectado cargando", Toast.LENGTH_SHORT).show();

    }
}
