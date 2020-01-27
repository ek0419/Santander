package com.example.trabajo.Planetas.detalle;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajo.Utilerias.BaseFragment;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.FragmentPlanetaDetalleBinding;


public class PlanetaDetalleFragment extends BaseFragment {

    private FragmentPlanetaDetalleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlanetaDetalleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
