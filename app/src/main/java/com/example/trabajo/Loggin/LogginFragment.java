package com.example.trabajo.Loggin;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.trabajo.InitDaggerII.App;
import com.example.trabajo.MainActivityInterface;
import com.example.trabajo.MenuComida.MenuComidaFragment;
import com.example.trabajo.R;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.FragmentLogginBinding;

import javax.inject.Inject;


public class LogginFragment extends Fragment implements LogginFragmentInterface.View {

    private FragmentLogginBinding binding;
    private MainActivityInterface mainActivityInterface;

    @Inject
    LogginFragmentInterface.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLogginBinding.inflate(inflater, container, false);
        binding.btnEnviarDatos.setOnClickListener(view -> presenter.buttonCliketed());
        binding.btnlevantarRegistro.setOnClickListener(view -> levantarRegistro());
        binding.btnCerrarRegistro.setOnClickListener(view -> cerrarREgistro());
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((App)context.getApplicationContext()).getComponet().inject(this);

        if(context instanceof MainActivityInterface)
        {
            mainActivityInterface = (MainActivityInterface) context;
        }
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getUser();
    }

    @Override
    public String getNombre() {
        return binding.tiNombre.getText().toString();
    }

    @Override
    public String getApellidos() {
        return binding.tiApellidos.getText().toString();
    }

    @Override
    public String getEmail() {
        return binding.tiEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return binding.tiPassword.getText().toString();
    }

    @Override
    public void usuarioNoExiste() {
        UTUtils.mostrarToas(getActivity(), "Usuario no disponible", false);
    }

    @Override
    public void mostarError(String mensaje) {
        UTUtils.mostrarToas(getActivity(), mensaje, false);
    }

    @Override
    public void entrar() {
        mainActivityInterface.remplazarFragmento(new MenuComidaFragment());
    }

    @Override
    public void mostrarUsuarioGuardado() {
        UTUtils.mostrarToas(getActivity(), "Usuario guardado con exito", false);
        cerrarREgistro();
    }

    @Override
    public void setNombrer(String nombre) {
        binding.tiNombre.setText(nombre);
    }

    @Override
    public void setApellidos(String apellidos) {
        binding.tiApellidos.setText(apellidos);
    }

    private void levantarRegistro()
    {
        binding.tilNombre.setVisibility(View.VISIBLE);
        binding.tieApellidos.setVisibility(View.VISIBLE);
        binding.btnlevantarRegistro.setVisibility(View.GONE);
        binding.tvtitleTipoVista.setText(getResources().getString(R.string.loggin_msg_title_REGISTRO));
        binding.clLoggin.setBackgroundColor(getResources().getColor(R.color.loggin_rosa));
        binding.btnCerrarRegistro.setVisibility(View.VISIBLE);
    }

    private void cerrarREgistro()
    {

        binding.tilNombre.setVisibility(View.GONE);
        binding.tieApellidos.setVisibility(View.GONE);
        binding.btnlevantarRegistro.setVisibility(View.GONE);
        binding.tvtitleTipoVista.setText(getResources().getString(R.string.loggin_msg_title_LOGIN));
        binding.clLoggin.setBackgroundColor(getResources().getColor(R.color.morado));
        binding.btnCerrarRegistro.setVisibility(View.GONE);
        binding.btnlevantarRegistro.setVisibility(View.VISIBLE);
    }

}
