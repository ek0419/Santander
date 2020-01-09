package com.example.trabajo.MenuComida;

import dagger.Module;
import dagger.Provides;

@Module
public class MenuComidaModuleDagger {

    @Provides
    public MenuComidaFragmentInterface.Presenter providePresenter(MenuComidaFragmentInterface.Model model) {
        return new MenuComidaPresenter(model);
    }

    @Provides
    public MenuComidaFragmentInterface.Model provideModel() {
        return new MenuComidaModel();
    }
}


