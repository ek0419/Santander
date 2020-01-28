package com.example.trabajo.Planetas;

import android.util.Log;

import com.example.trabajo.Planetas.catalogo.ItemPlaneta;
import com.example.trabajo.Planetas.catalogo.PlanetasModel;
import com.example.trabajo.Planetas.detalle.PlanetasDetalleModel;
import com.example.trabajo.retrofit.RequestManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class PlanetasWS {

    private String baseUrl;
    private RequestManager manager;
    private static final String TAG = PlanetasWS.class.getSimpleName();

    public PlanetasWS(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ArrayList<ItemPlaneta> consultarPlanetas() {
        ArrayList<ItemPlaneta> list = new ArrayList<>();
        PlanetasModel planeta;

        manager = new RequestManager(baseUrl);
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

    public PlanetasDetalleModel getDetallePlanetas(String parametro) {
        manager = new RequestManager(baseUrl);
        PlanetasDetalleModel model = new PlanetasDetalleModel();
        String aux = parametro.replace("https://swapi.co/api/planets/", "");
        String algo = aux.replace("/", "");
        try {
            Call<PlanetasDetalleModel> call = manager.create(PlanetasWSInterface.class).detalles(algo);
            Response<PlanetasDetalleModel> response = call.execute();
            return response.body();

        } catch (Exception e) {
            Log.e(TAG, "error al consumir servicio " + e.getMessage());
            return model;
        }
    }

}
