package com.example.trabajo.Planetas.catalogo

import com.google.gson.annotations.SerializedName


class PlanetasModel(

        @SerializedName("count") var count: Int? = null,
        @SerializedName("next") var next: String? = null,
        @SerializedName("previous") var previous: Any? = null,
        @SerializedName("results") var results: List<Result>? = null

)

class Result(

        @SerializedName("name") var name: String? = null,
        @SerializedName("rotation_period") var rotationPeriod: String? = null,
        @SerializedName("orbital_period") var orbitalPeriod: String? = null,
        @SerializedName("diameter") var diameter: String? = null,
        @SerializedName("climate") var climate: String? = null,
        @SerializedName("gravity") var gravity: String? = null,
        @SerializedName("terrain") var terrain: String? = null,
        @SerializedName("surface_water") var surfaceWater: String? = null,
        @SerializedName("population") var population: String? = null,
        @SerializedName("residents") var residents: List<String>? = null,
        @SerializedName("films") var films: List<String>? = null,
        @SerializedName("created") var created: String? = null,
        @SerializedName("edited") var edited: String? = null,
        @SerializedName("url") var url: String? = null

)


data class ItemPlaneta(val nombre: String? = null,
                   val rotacion: String? = null,
                   val orbita: String? = null,
                   val diametro: String? = null,
                   val clima: String? = null,
                   val gravedad: String? = null,
                   val superficie: String? = null,
                   val url: String? = null)