package com.example.trabajo.Loggin;

import androidx.annotation.Nullable;

import com.example.trabajo.Utilerias.ENUM;

public class LogginFragmentPresenter implements LogginFragmentInterface.Presenter {


    @Nullable
    private LogginFragmentInterface.View view;
    private LogginFragmentInterface.Model model;

    public LogginFragmentPresenter(LogginFragmentInterface.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LogginFragmentInterface.View view) {
        this.view = view;
    }

    @Override
    public void buttonCliketed(boolean esRegistro) {

        if (view != null) {
            if (esRegistro) {
                if (validaComposCompetos()) {
                    view.mostarError("Faltan campos por llenar");
                }
                else {
                    if (
                    model.crearUsuario("0",
                            view.getNombre().trim()
                            , view.getApellidos().trim()
                            , view.getEmail().trim()
                            , view.getPassword().trim())
                    ){
                        view.mostarError("Usuario Creado");
                        view.cerrarREgistro();
                    }
                }
            } else {
                if (view.getEmail().trim().isEmpty() || view.getPassword().trim().isEmpty()) {
                    view.mostarError("Ingresa tu usuario y contraseña");
                } else {
                    switch (model.validaUsuario(view.getEmail().trim(), view.getPassword().trim())) {
                        case ENUM.USUARIOINCORRECTO:
                            view.mostarError("Ingresa un usuario vàlido");
                            break;
                        case ENUM.CONTRASENIAINCONRECTA:
                            view.mostarError("Ingresa una contraseña valida");
                            break;
                        case ENUM.USUARIOENCONTRADO:
                            view.entrar();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        view.mostarError(mensaje);
    }


    @Override
    public void getUser() {
        ModelUser user = model.getUser();
        if (user == null) {
            if (view != null) {
                view.usuarioNoExiste();

            }
        } else {
            if (view != null) {
                view.setNombrer(user.getNombre());
                view.setApellidos(user.getApellidos());
            }
        }
    }

    private boolean validaComposCompetos() {
        return view.getNombre().trim().isEmpty()
                || view.getApellidos().trim().isEmpty()
                || view.getEmail().trim().isEmpty()
                || view.getPassword().trim().isEmpty();
    }
}


//contacto.representantes.mexico@avon.com

//curp
//credencial por ambos lados
//referencia por