package com.example.trabajo.InitDaggerII;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application)
    {
        this.application = application;

    }

    @Provides
    @Singleton
    public Context privideContext()
    {
        return application;
    }
}
