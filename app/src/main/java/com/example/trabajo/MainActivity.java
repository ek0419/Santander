package com.example.trabajo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.trabajo.BroadCastReceiver.OnChargeReceiver;
import com.example.trabajo.FingerPrint.BiometricPromptFragment;
import com.example.trabajo.GenericDialog.GenericDialogBuilder;
import com.example.trabajo.Loggin.LogginFragment;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.ActivityMainBinding;
import com.google.firebase.database.core.utilities.Utilities;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    BroadcastReceiver receiver;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        new GenericDialogBuilder
                .Builder(this)
                .setTitulo("Este es un titulo")
                .setMensaje("Contenido del mensaje")
                .setPositiveButton("Aceptar")
                .create()
                .show();

    }


    private void lanzarLoggin() {
        UTUtils.lanzarFragment(getSupportFragmentManager(), R.id.flContainer, new LogginFragment());


    }


    @Override
    public void remplazarFragmento(Fragment fragment) {
        UTUtils.remplazarFragment(getSupportFragmentManager(), R.id.flContainer, fragment);
    }


    private void lanzarFingerPrint() {
        UTUtils.lanzarFragment(getSupportFragmentManager(), R.id.flContainer, new BiometricPromptFragment());
    }


    private void lanzarReciber() {
        receiver = new OnChargeReceiver();
        IntentFilter filter = new IntentFilter(BATTERY_SERVICE);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        this.registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver); // destruye reciber
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(BATTERY_SERVICE);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, filter);
    }
}
