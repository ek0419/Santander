package com.example.trabajo.Registro;

import android.content.Context;

import com.example.trabajo.Utilerias.General.UsuarioModel;
import com.example.trabajo.Utilerias.SQLite.UTDatabaseManager;
import com.example.trabajo.Utilerias.UTUtils;

public class RegistroModel implements RegistroInterface.Model {

    private RegistroInterface.Presenter presenter;

    public RegistroModel(RegistroInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void existeUsuario() {

    }

    @Override
    public int guardaUsuario(UsuarioModel usr, Context context) {
        UTDatabaseManager bd = new UTDatabaseManager();
        return  bd.insertUsuario(context, usr);

    }
}
