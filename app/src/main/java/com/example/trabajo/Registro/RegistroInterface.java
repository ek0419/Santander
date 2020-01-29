package com.example.trabajo.Registro;

import android.content.Context;

import com.example.trabajo.Utilerias.General.UsuarioModel;

public interface RegistroInterface {

    interface View {
        void mostrarProgress(boolean mostrar);
        void cambiarVista();
        void mostarMensaje(String mensaje);
    }

    interface Presenter {
        void consultarBase();
        void guardarUsuario(UsuarioModel user);
    }

    interface Model {
        void existeUsuario();
        int guardaUsuario(UsuarioModel usr, Context context);
    }
}
