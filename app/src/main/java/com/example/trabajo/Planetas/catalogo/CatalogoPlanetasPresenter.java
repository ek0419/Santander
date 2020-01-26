package com.example.trabajo.Planetas.catalogo;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CatalogoPlanetasPresenter implements CatalogoPlanetasInterface.Presenter {

    private CatalogoPlanetasInterface.View view;
    private CatalogoPlanetasInterface.Model model;
    private CompositeDisposable disposable;

    public CatalogoPlanetasPresenter(CatalogoPlanetasInterface.View view) {
        this.view = view;
        disposable = new CompositeDisposable();
        model = new CatalogoPlanetasModel(this);
    }

    @Override
    public void consultaServicio() {
        if (view != null) {
            view.progress(true);
            disposable.add(Observable.fromCallable(() -> model.getPlanetas())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(resul -> {
                        view.progress(false);
                        view.MostrarRecycler(resul);
                    }));
        }

    }

    @Override
    public void mensaje(String mensaje) {
        if (view != null) {
            view.mostrarMensaje(mensaje);
        }
    }
}
