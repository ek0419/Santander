package com.example.trabajo.TipoServicios;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiciosInterfaceWS {
    @Headers("Content-type:application/json")
    @POST("MiPost")
    Call<JsonObject>ServicioPOST(@Body JsonObject jsonObject);
}
