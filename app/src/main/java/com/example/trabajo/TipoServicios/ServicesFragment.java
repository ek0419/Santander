package com.example.trabajo.TipoServicios;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajo.Utilerias.BaseFragment;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.FragmentServicesBinding;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ServicesFragment extends BaseFragment {

    FragmentServicesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentServicesBinding.inflate(inflater, container, false);

        ejecutaServicio();
        return binding.getRoot();
    }


    private void ejecutaServicio() {
        UTUtils.mostrarProgressDialog("", "", getActivity());
        mCompositeDisposable.add(Single.fromCallable(() -> {
            ServicioPOSTWS ws = new ServicioPOSTWS();
            return ws.executeServiciePOST();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    UTUtils.esconderProgressDialog();
                    UTUtils.mostrarToas(getActivity(), result.toString(), false);
                }));
    }
}
