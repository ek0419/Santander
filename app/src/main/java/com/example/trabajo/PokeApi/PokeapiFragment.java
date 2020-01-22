package com.example.trabajo.PokeApi;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
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
    private int offset = 0;
    private boolean sePuedeCargar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPokeapiBinding.inflate(inflater, container, false);
        mCompositeDisposable = new CompositeDisposable();
        adapter = new PokeApiAdapter();
        consultaPokeapi();
        return binding.getRoot();
    }

    private void consultaPokeapi() {
        sePuedeCargar = true;
        UTUtils.mostrarProgressDialog("", "", getActivity());
        mCompositeDisposable.add(Observable.fromCallable(() -> {
            PokeApiWS ws = new PokeApiWS();
            return ws.consultaServicio(offset);
        }).delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result != null) {
                        UTUtils.esconderProgressDialog();
                        mCompositeDisposable.clear();
                            adapter.addLista(result);
                        llenarRecycler();
                    } else {
                        UTUtils.mostrarToas(getActivity(), "Eror al consultar servicio", false);
                    }
                }));

    }

    private void llenarRecycler() {
        // LinearLayoutManager manager =  new LinearLayoutManager(getActivity());
        // manager.setOrientation(RecyclerView.VERTICAL);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        binding.rVPokemonones.setLayoutManager(manager);
        binding.rVPokemonones.setItemAnimator(new DefaultItemAnimator());
        binding.rVPokemonones.hasFixedSize();
        binding.rVPokemonones.computeHorizontalScrollRange();
        binding.rVPokemonones.setAdapter(adapter);
        binding.rVPokemonones.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = manager.getChildCount();
                    int totalItemCount = manager.getItemCount();
                    int pastVisbleItems = manager.findFirstVisibleItemPosition();
                    if (sePuedeCargar) {
                        if (visibleItemCount + pastVisbleItems >= totalItemCount) {
                            Log.e("FINAL", "llegamos al fia¡nal");
                            sePuedeCargar = false;
                            offset = offset + 20;
                            consultaPokeapi();
                        }
                    }
                }
            }
        });
    }

}
