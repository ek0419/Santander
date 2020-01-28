package com.example.trabajo.Planetas.detalle;

import com.example.trabajo.Planetas.PlanetasWS;

public class PlanetaDetalleModel implements PlanetaDetalleInterface.Model {

    PlanetaDetalleInterface.Presenter presenter;

    public PlanetaDetalleModel(PlanetaDetalleInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public PlanetasDetalleModel getDetalles(String url) {

        PlanetasWS ws = new PlanetasWS("https://swapi.co/");

        return ws.getDetallePlanetas(url);
    }
}
