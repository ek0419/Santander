package com.example.trabajo.Planetas.catalogo;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo.Planetas.detalle.PlanetaDetalleFragment;
import com.example.trabajo.R;
import com.example.trabajo.Utilerias.BaseFragment;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.FragmentCatalogoPlanetasBinding;

import java.util.ArrayList;


public class CatalogoPlanetasFragment extends BaseFragment implements CatalogoPlanetasInterface.View, PlanetasAdapter.OnItemSelectedInterface {

    FragmentCatalogoPlanetasBinding binding;
    CatalogoPlanetasInterface.Presenter presenter;
    private ArrayList<ItemPlaneta> list;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        presenter = new CatalogoPlanetasPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCatalogoPlanetasBinding.inflate(inflater, container, false);
        binding.btnConsultaWS.setOnClickListener(v -> presenter.consultaServicio());

        return binding.getRoot();
    }


    @Override
    public void mostrarMensaje(String mensaje) {
        UTUtils.mostrarToas(getActivity(), mensaje, false);
    }

    @Override
    public void progress(boolean mostrar) {
        if (mostrar)
            UTUtils.mostrarProgressDialog("", "", getActivity());
        else
            UTUtils.esconderProgressDialog();
    }

    @Override
    public void MostrarRecycler(ArrayList<ItemPlaneta> list) {
        this.list = list;
        PlanetasAdapter adapter = new PlanetasAdapter(list, this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.rvPlanetas.setLayoutManager(manager);
        binding.rvPlanetas.hasFixedSize();
        binding.rvPlanetas.computeHorizontalScrollRange();
        binding.rvPlanetas.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position) {
        Fragment fragment = new PlanetaDetalleFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.flip_in,R.anim.flip_h);
        transaction.replace(R.id.flContainer,fragment).commit();
    }

}