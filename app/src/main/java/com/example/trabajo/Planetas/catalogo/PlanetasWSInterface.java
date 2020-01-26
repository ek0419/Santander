package com.example.trabajo.Planetas.catalogo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlanetasWSInterface {

    @GET("api/planets")
    Call<PlanetasModel> planetas();
}
