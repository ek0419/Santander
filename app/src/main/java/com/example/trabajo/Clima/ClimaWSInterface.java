package com.example.trabajo.Clima;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClimaWSInterface {
    @GET("data/2.5/forecast")
    Call<ModelClima> getClima(@Query("id") int id,
                              @Query("appid") String appid);

}
