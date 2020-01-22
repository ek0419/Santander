package com.example.trabajo.PokeApi;


import android.util.Log;

import com.example.trabajo.retrofit.RequestManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PokeApiWS {

    private String baseURL;
    private RequestManager requestManager;

    public PokeApiWS() {

        baseURL = "https://pokeapi.co/api/v2/";
    }

    public PokeApiModel consultaServicio() {

        PokeApiModel result = null;

        requestManager = new RequestManager(baseURL);

        Call<PokeApiModel> call = requestManager.create(pokeapInterfaceWS.class)
                .consultaPokemons(0, 10);

        try {

            Response<PokeApiModel> response = call.execute();
             result = response.body();

            Log.e("-->>>", result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}




