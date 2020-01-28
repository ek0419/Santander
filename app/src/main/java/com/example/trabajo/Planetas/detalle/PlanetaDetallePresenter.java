package com.example.trabajo.Planetas.detalle;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PlanetaDetallePresenter implements PlanetaDetalleInterface.Presenter {

    private PlanetaDetalleInterface.View view;
    private PlanetaDetalleInterface.Model model;
    CompositeDisposable mCompositeDisposable;

    public PlanetaDetallePresenter(PlanetaDetalleInterface.View view) {

        this.view = view;
        this.model = new PlanetaDetalleModel(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void consultaDetallews(String url) {
        view.mostrarProgress(true);
        mCompositeDisposable.add(Observable.fromCallable(() ->
        {
            return model.getDetalles(url);
        }).delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    mCompositeDisposable.clear();
                    view.mostrarProgress(false);
                    if (result == null) {
                        view.mostrarREsult("Error al consultar Servicio");
                    }else
                    {
                        view.setDetalles(result);
                    }


                }));
    }
}
