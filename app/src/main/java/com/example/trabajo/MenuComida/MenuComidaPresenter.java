package com.example.trabajo.MenuComida;

import androidx.annotation.Nullable;

public class MenuComidaPresenter implements MenuComidaFragmentInterface.Presenter {

    @Nullable
    private MenuComidaFragmentInterface.View view;
    private MenuComidaFragmentInterface.Model model;

    public MenuComidaPresenter(MenuComidaFragmentInterface.Model model) {
        this.model = model;
    }


    @Override
    public void setView(MenuComidaFragmentInterface.View view) {
        this.view = view;
    }
}
