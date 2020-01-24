package com.example.trabajo.Clima;

import com.example.trabajo.retrofit.RequestManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ClimaWS {

    private String BASEURL;
    private String CLAVEIP = "b1b15e88fa797225412429c1c50c122a1";
    private RequestManager manager;
    private int claveCiudad = 524901;


       //     manager.create(ClimaWSInterface.class).getClima("524901",CLAVEIP)

    public ClimaWS() {
        BASEURL = "https://samples.openweathermap.org/";
    }

    public ClimaFinal consultaServicio() {
        ModelClima object = new ModelClima();
        ClimaFinal clima = null;

        manager = new RequestManager(BASEURL);
        Call<ModelClima> call = manager.create(ClimaWSInterface.class).getClima(claveCiudad, CLAVEIP);

        try {
            Response<ModelClima> response = call.execute();
            object = response.body();
            clima = new ClimaFinal(object.getList().get(0).getWeather().get(0).getMain(),object.getList().get(0).getWeather().get(0).getDescription());

        } catch (IOException e) {
            e.printStackTrace();
            return clima;
        }

        return clima;

    }


}
