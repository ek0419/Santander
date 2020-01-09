package com.example.trabajo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.trabajo.Loggin.LogginFragment;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainActivityInterface{

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        lanzarLoggin();


    }


    private void lanzarLoggin()
    {
     UTUtils.lanzarFragment(getSupportFragmentManager(),R.id.flContainer,new LogginFragment());
    }


    @Override
    public void remplazarFragmento(Fragment fragment) {
       UTUtils.remplazarFragment(getSupportFragmentManager(),R.id.flContainer,fragment);
    }
}
