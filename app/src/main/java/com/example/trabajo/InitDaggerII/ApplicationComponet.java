package com.example.trabajo.InitDaggerII;

import com.example.trabajo.Loggin.LogginFragment;
import com.example.trabajo.Loggin.LogginModuleDagger;
import com.example.trabajo.MenuComida.MenuComidaFragment;
import com.example.trabajo.MenuComida.MenuComidaModuleDagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LogginModuleDagger.class, MenuComidaModuleDagger.class})


public interface ApplicationComponet {
    void inject(LogginFragment target);

    void inject(MenuComidaFragment menucomida);

}




