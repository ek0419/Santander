package com.example.trabajo.Loggin;

import com.example.trabajo.Utilerias.ENUM;

public class LogginFragmentModel implements LogginFragmentInterface.Model {

    private LogginFragmentInterface.LogginRepository repository;


    public LogginFragmentModel(LogginFragmentInterface.LogginRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean crearUsuario(String id, String nombre, String apellidos, String email, String password) {
        //aqui va la logica de negocio
        repository.saveUserRepository(new ModelUser(id, nombre, apellidos, email, password));
        return true;
    }

    @Override
    public ModelUser getUser() {
        //logica de negocio
        return repository.getUserRepository();
    }

    @Override
    public int validaUsuario(String email, String password) {

        ModelUser user = repository.getUserRepository();

        if (user.getEmail().equals(email)) {
            if (user.getPassword().equals(password)) {
                return ENUM.USUARIOENCONTRADO;
            } else {
                return ENUM.CONTRASENIAINCONRECTA;
            }
        } else {
            return ENUM.USUARIOINCORRECTO;
        }

    }
}
