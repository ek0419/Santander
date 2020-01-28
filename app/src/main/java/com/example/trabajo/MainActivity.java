package com.example.trabajo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.trabajo.BroadCastReceiver.OnChargeReceiver;
import com.example.trabajo.Clima.ClimaFragment;
import com.example.trabajo.FingerPrint.BiometricPromptFragment;
import com.example.trabajo.Loggin.LogginFragment;
import com.example.trabajo.Planetas.catalogo.CatalogoPlanetasFragment;
import com.example.trabajo.PokeApi.PokeapiFragment;
import com.example.trabajo.Registro.RegistroFragment;
import com.example.trabajo.TipoServicios.ServicesFragment;
import com.example.trabajo.Utilerias.BaseActivity;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    BroadcastReceiver receiver;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
     // lanzarCatalogoPlanetas();
        lanzarRegistro();
    }

    private void lanzarServices()
    {
        UTUtils.lanzarFragment(getSupportFragmentManager(),R.id.flContainer,new ServicesFragment(),false);
    }

    private void lanzarCatalogoPlanetas() {
        UTUtils.lanzarFragment(getSupportFragmentManager(), R.id.flContainer, new CatalogoPlanetasFragment(), false);

    }

    private void lanzarRegistro() {
        UTUtils.lanzarFragment(getSupportFragmentManager(), R.id.flContainer, new RegistroFragment(), false);
    }

    private void lanzarClima() {
        UTUtils.lanzarFragment(getSupportFragmentManager(), R.id.flContainer, new ClimaFragment(), false);
    }

    private void lanzarLoggin() {
        UTUtils.lanzarFragment(getSupportFragmentManager(), R.id.flContainer, new LogginFragment(), false);
    }

    private void lanzarPokeapi() {
        UTUtils.lanzarFragment(getSupportFragmentManager(), R.id.flContainer, new PokeapiFragment(), false);
    }


    private void lanzarFingerPrint() {
        UTUtils.lanzarFragment(getSupportFragmentManager(), R.id.flContainer, new BiometricPromptFragment(), false);
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
//        unregisterReceiver(receiver); // destruye reciber
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  IntentFilter filter = new IntentFilter(BATTERY_SERVICE);
        //filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        //registerReceiver(receiver, filter);
    }

}
