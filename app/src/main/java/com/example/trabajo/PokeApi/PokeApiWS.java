package com.example.trabajo.PokeApi;


import android.util.Log;

import com.example.trabajo.retrofit.RequestManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class PokeApiWS {

    private String baseURL;
    private RequestManager requestManager;

    public PokeApiWS() {

        baseURL = "https://pokeapi.co/api/v2/";
    }

    public ArrayList<Pokemones> consultaServicio(int offset) {

        ArrayList<Pokemones> result = new ArrayList<>();


        requestManager = new RequestManager(baseURL);

        Call<PokeApiModel> call = requestManager.create(pokeapInterfaceWS.class)
                .consultaPokemons(offset, 20);
        try {
            Response<PokeApiModel> response = call.execute();
            PokeApiModel lista = response.body();
            if (lista != null)
                for (int i = 0; i < lista.component1().size(); i++) {
                    result.add(new Pokemones(lista.component1().get(i).getName(),lista.component1().get(i).getUrl()));
                }

            Log.e("-->>>", result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}




