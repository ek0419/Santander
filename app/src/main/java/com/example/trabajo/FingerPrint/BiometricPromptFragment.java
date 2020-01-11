package com.example.trabajo.FingerPrint;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.trabajo.databinding.FragmentBiometricPromptBinding;


public class BiometricPromptFragment extends Fragment {

    FragmentBiometricPromptBinding binding;

    //reference https://www.androidauthority.com/add-fingerprint-authentication-app-biometricprompt-943784/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBiometricPromptBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

}
