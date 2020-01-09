package com.example.trabajo.InitDaggerII;

import android.app.Application;

import com.example.trabajo.Loggin.LogginModuleDagger;
import com.example.trabajo.MenuComida.MenuComidaModuleDagger;


public class App extends Application {

    private ApplicationComponet componet;


    @Override
    public void onCreate() {
        super.onCreate();
        componet = DaggerApplicationComponet
                .builder()
                .applicationModule(new ApplicationModule(this))
                .logginModuleDagger(new LogginModuleDagger())
                .menuComidaModuleDagger(new MenuComidaModuleDagger())
                .build();
    }

    public ApplicationComponet getComponet() {
        return componet;
    }
}
