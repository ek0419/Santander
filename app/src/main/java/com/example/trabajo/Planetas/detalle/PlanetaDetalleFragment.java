package com.example.trabajo.Planetas.detalle;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabajo.Utilerias.BaseFragment;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.FragmentPlanetaDetalleBinding;


public class PlanetaDetalleFragment extends BaseFragment implements PlanetaDetalleInterface.View {

    private FragmentPlanetaDetalleBinding binding;
    private PlanetaDetalleInterface.Presenter presenter;
    private String BASEURL;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        presenter = new PlanetaDetallePresenter(this);
    }

    public PlanetaDetalleFragment(String BASEURL) {
        this.BASEURL = BASEURL;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlanetaDetalleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.consultaDetallews(BASEURL);
    }

    @Override
    public void mostrarProgress(Boolean mostrar) {
        if (mostrar)
            UTUtils.mostrarProgressDialog("", "", getActivity());
        else
            UTUtils.esconderProgressDialog();
    }

    @Override
    public void setDetalles(PlanetasDetalleModel model) {
        binding.tvNombrePlanetaDetalle.setText(model.getName());
        binding.tvSetRotacion.setText(model.getRotationPeriod());
        binding.tvSurfaceWater.setText(model.getSurfaceWater());
        binding.tvGravedad.setText(model.getGravity());
        binding.tvClima.setText(model.getClimate());

    }

    @Override
    public void mostrarREsult(String result) {

        UTUtils.mostrarToas(getActivity(), result, false);
    }
}
