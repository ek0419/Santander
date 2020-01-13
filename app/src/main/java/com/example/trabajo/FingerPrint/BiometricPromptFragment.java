package com.example.trabajo.FingerPrint;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;

import com.example.trabajo.R;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.FragmentBiometricPromptBinding;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Scheduler;


public class BiometricPromptFragment extends Fragment {

    FragmentBiometricPromptBinding binding;
    private BiometricPrompt.PromptInfo autentication;

    //reference https://www.androidauthority.com/add-fingerprint-authentication-app-biometricprompt-943784/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBiometricPromptBinding.inflate(inflater, container, false);
        binding.btnHuellaAutentication.setOnClickListener(view -> initHuellaAutentication());


        return binding.getRoot();
    }


    private void initHuellaAutentication() {
        autentication = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(getResources().getString(R.string.msj_huella_autenticacion_title))
                .setSubtitle(getResources().getString(R.string.msj_huella_autenticacion_escanea))
                .setDescription(getResources().getString(R.string.msj_huella_autenticacion_toca_sensor))
                .setNegativeButtonText(getResources().getString(R.string.msj_generico_cencelar))
                .build();

        Executor newExecutor = Executors.newSingleThreadExecutor();

        final BiometricPrompt biometricPrompt = new BiometricPrompt(getActivity(), newExecutor,new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_CANCELED) {
                    UTUtils.mostrarToas(getActivity(), "Usuario cancelo", false);
                } else {
                    UTUtils.mostrarToas(getActivity(), "Se produjo un error", false);
                }
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                UTUtils.mostrarToas(getActivity(), "Huella autenticada con exito", false);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();

                UTUtils.mostrarToas(getActivity(), "Huella no reconocida", false);
            }
        });

        biometricPrompt.authenticate(autentication);
    }

}
