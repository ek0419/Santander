package com.example.trabajo.Registro;

import android.content.Context;

import com.example.trabajo.Utilerias.General.UsuarioModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RegistroPresenter implements RegistroInterface.Presenter {

    RegistroInterface.View view;
    RegistroInterface.Model model;
    Context context;

    CompositeDisposable mCompositeDisposable;

    public RegistroPresenter(RegistroInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new RegistroModel(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void consultarBase() {

    }

    @Override
    public void guardarUsuario(UsuarioModel user) {
        view.mostrarProgress(true);
        if (validaRegistroVacio(user)) {
            mCompositeDisposable.add(Observable.fromCallable(() -> model.guardaUsuario(user, context)).observeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        mCompositeDisposable.clear();
                        view.mostrarProgress(false);
                        if (result == 1) {
                            view.cambiarVista();
                        } else {
                            view.mostarMensaje("Error al guardar Usuario");

                        }
                    }));

        } else {
            view.mostrarProgress(false);
            view.mostarMensaje("Favor de llenar todos los campos");

        }


    }

    private boolean validaRegistroVacio(UsuarioModel user) {
        if (user.getNombre().trim().isEmpty() ||
                user.getAPaterno().trim().isEmpty()
                || user.getAMaterno().trim().isEmpty()
                || user.getFechaNacimiento().trim().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }


}
