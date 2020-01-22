package com.example.trabajo.PokeApi

import com.google.gson.annotations.SerializedName


data class PokeApiModel(
        @SerializedName("results") val resul: List<Result>? = null

)

data class Result(
        @SerializedName("name") val name: String? = null,
        @SerializedName("url") val url: String? = null
)

data class Pokemones(val nombre:String? , val url: String?)
