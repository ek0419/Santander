package com.example.trabajo.Planetas.catalogo;

import com.example.trabajo.retrofit.RequestManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class PlanetasWS {

    private String BASEURL;
    private RequestManager manager;

    public PlanetasWS() {
        BASEURL = "https://swapi.co/";
    }

    ArrayList<ItemPlaneta> consultarPlanetas() {
        ArrayList<ItemPlaneta> list = new ArrayList<>();
        PlanetasModel planeta;

        manager = new RequestManager(BASEURL);
        Call<PlanetasModel> call = manager.create(PlanetasWSInterface.class).planetas();
        try {
            Response<PlanetasModel> response = call.execute();
            planeta = response.body();

            if (response != null)
                for (int i = 0; i < planeta.getResults().size(); i++) {
                    list.add(new ItemPlaneta(
                            planeta.getResults().get(i).getName(),
                            planeta.getResults().get(i).getRotationPeriod(),
                            planeta.getResults().get(i).getOrbitalPeriod(),
                            planeta.getResults().get(i).getDiameter(),
                            planeta.getResults().get(i).getClimate(),
                            planeta.getResults().get(i).getGravity(),
                            planeta.getResults().get(i).getSurfaceWater(),
                            planeta.getResults().get(i).getUrl()));
                }
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
        return list;
    }

}
