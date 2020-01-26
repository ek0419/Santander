package com.example.trabajo.Clima;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.trabajo.Clima.RecylerView.ClimaAdapter;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.FragmentClimaBinding;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class ClimaFragment extends Fragment implements ClimaAdapter.onItemSelectedClima {

    FragmentClimaBinding binding;
    CompositeDisposable mCompositeDisposable;
    ClimaWS ws;
    ClimaAdapter climaAdapter;
    private ArrayList<CrearCiudad> ciudades = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentClimaBinding.inflate(inflater, container, false);
        construirList();
        contruirVista();
        binding.btnConsultar.setOnClickListener(v -> ConsultaServicio());


        return binding.getRoot();
    }

    private void ConsultaServicio() {
        UTUtils.mostrarProgressDialog("", "", getActivity());
        mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(Observable.fromCallable(() -> {

            ws = new ClimaWS();
            return ws.consultaServicio();
        }).delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    UTUtils.esconderProgressDialog();
                    if (result != null) {
                        UTUtils.mostrarToas(getActivity(), "Se realizo consulta", false);
                    } else {
                        UTUtils.mostrarToas(getActivity(), "No se realizo consulta", false);
                    }
                }));
    }

    private void construirList() {

        ciudades.add(new CrearCiudad(707860, "Hurzuf"));
        ciudades.add(new CrearCiudad(519188, "Novinki RU"));
        ciudades.add(new CrearCiudad(1283378, "Gorkhā NP"));
        ciudades.add(new CrearCiudad(1270260, "State of Haryāna"));
        ciudades.add(new CrearCiudad(708546, "Holubynka"));

    }

    private void contruirVista() {
        climaAdapter = new ClimaAdapter(ciudades, this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        binding.rvRecyclerClima.setLayoutManager(manager);
        binding.rvRecyclerClima.setItemAnimator(new DefaultItemAnimator());
        binding.rvRecyclerClima.hasFixedSize();
        binding.rvRecyclerClima.computeHorizontalScrollRange();
        binding.rvRecyclerClima.setAdapter(climaAdapter);


    }

    @Override
    public void onItemClick(int position) {
        
    }
}
