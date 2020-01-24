package com.example.trabajo.Clima;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajo.R;
import com.example.trabajo.databinding.FragmentCiudadBinding;


public class CiudadFragment extends Fragment {

    FragmentCiudadBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCiudadBinding.inflate(inflater, container, false);
        enviarDatos();
        return binding.getRoot();
    }

    private void enviarDatos()
    {

    }

}
