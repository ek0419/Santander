package com.example.trabajo.PokeApi;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface pokeapInterfaceWS {

    @GET("pokemon/")
    Call<PokeApiModel> consultaPokemons(
            @Query("offset") int offset,
            @Query("limit") int limit);

}
