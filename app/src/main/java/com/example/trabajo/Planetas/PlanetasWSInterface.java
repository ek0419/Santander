package com.example.trabajo.Planetas;

import com.example.trabajo.Planetas.catalogo.PlanetasModel;
import com.example.trabajo.Planetas.detalle.PlanetasDetalleModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlanetasWSInterface {

    @GET("api/planets")
    Call<PlanetasModel> planetas();

    @GET("api/planets/{planeta}")
    Call<PlanetasDetalleModel> detalles(@Path("planeta") String planeta);

}
