package com.example.trabajo.Loggin;

public interface LogginFragmentInterface {


    interface View {
        String getNombre();

        String getApellidos();

        String getEmail();

        String getPassword();

        void usuarioNoExiste();

        void mostarError(String msj);

        void entrar();

        void mostrarUsuarioGuardado();

        void setNombrer(String nombre);

        void setApellidos(String apellidos);

        void cerrarREgistro();

    }

    interface Presenter {
        void setView(LogginFragmentInterface.View view);

        void buttonCliketed(boolean tipoVista);

        void mostrarMensaje(String mensaje);

        void getUser();

    }

    interface Model {

        boolean crearUsuario(String id, String nombre, String Apellidos, String email, String password);

        ModelUser getUser();


        int validaUsuario(String email,String password);
    }

    interface LogginRepository {

        void saveUserRepository(ModelUser user);

        ModelUser getUserRepository();
    }

}
