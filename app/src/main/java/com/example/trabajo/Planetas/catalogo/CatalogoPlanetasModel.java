package com.example.trabajo.Planetas.catalogo;

import com.example.trabajo.Planetas.PlanetasWS;

import java.util.ArrayList;

public class CatalogoPlanetasModel implements CatalogoPlanetasInterface.Model {

    private CatalogoPlanetasInterface.Presenter presenter;

    PlanetasWS ws;

    public CatalogoPlanetasModel(CatalogoPlanetasInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ArrayList<ItemPlaneta> getPlanetas() {
        ws = new PlanetasWS("https://swapi.co/");
        return ws.consultarPlanetas();
    }
}
