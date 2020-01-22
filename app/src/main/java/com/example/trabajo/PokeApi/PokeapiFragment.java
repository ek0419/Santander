package com.example.trabajo.PokeApi;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo.PokeApi.RecycleView.PokeApiAdapter;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.FragmentPokeapiBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PokeapiFragment extends Fragment {


     FragmentPokeapiBinding binding;
    CompositeDisposable mCompositeDisposable;
    private PokeApiAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPokeapiBinding.inflate(inflater, container, false);
        mCompositeDisposable = new CompositeDisposable();
        consultaPokeapi();
        return binding.getRoot();
    }

    private void consultaPokeapi() {
        UTUtils.mostrarProgressDialog("", "", getActivity());
        mCompositeDisposable.add(Observable.fromCallable(() -> {
            PokeApiWS ws = new PokeApiWS();
            return ws.consultaServicio();
        }).delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result != null) {
                        UTUtils.esconderProgressDialog();
                        mCompositeDisposable.clear();
                        adapter =  new PokeApiAdapter(result);
                        llenarRecycler();
                    }
                }));

    }

    private void llenarRecycler(){
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.rVPokemonones.setAdapter(adapter);
        binding.rVPokemonones.setItemAnimator(new DefaultItemAnimator());
        binding.rVPokemonones.hasFixedSize();
        binding.rVPokemonones.computeHorizontalScrollRange();
        binding.rVPokemonones.setLayoutManager(manager);
    }

}
