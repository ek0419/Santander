package com.example.trabajo.Clima

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose


data class ModelClima(
        @SerializedName("cod")
        var cod: String? = null,
        @SerializedName("message")
        var message: Double? = null,
        @SerializedName("cnt")
        var cnt: Int? = null,
        @SerializedName("list")
        var list: List<list>? = null,
        @SerializedName("city")
        var city: City? = null
)


class City {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("coord")
    var coord: Coord? = null
    @SerializedName("country")
    var country: String? = null

}

class Clouds {

    @SerializedName("all")
    var all: Int? = null

}


class Coord {

    @SerializedName("lat")
    var lat: Double? = null
    @SerializedName("lon")
    var lon: Double? = null

}


class list(

        @SerializedName("dt")
        var dt: Int? = null,
        @SerializedName("main")
        var main: Main? = null,
        @SerializedName("weather")
        var weather: List<Weather>? = null,
        @SerializedName("clouds")
        var clouds: Clouds? = null,
        @SerializedName("wind")
        var wind: Wind? = null,
        @SerializedName("snow")
        var snow: Snow? = null,
        @SerializedName("sys")
        var sys: Sys? = null,
        @SerializedName("dt_txt")
        var dtTxt: String? = null

)


class Main {

    @SerializedName("temp")
    var temp: Double? = null
    @SerializedName("temp_min")
    var tempMin: Double? = null
    @SerializedName("temp_max")
    var tempMax: Double? = null
    @SerializedName("pressure")
    var pressure: Double? = null
    @SerializedName("sea_level")
    var seaLevel: Double? = null
    @SerializedName("grnd_level")
    var grndLevel: Double? = null
    @SerializedName("humidity")
    var humidity: Int? = null
    @SerializedName("temp_kf")
    var tempKf: Double? = null

}

class Snow {

    @SerializedName("3h")
    var h: Double? = null

}

class Sys {

    @SerializedName("pod")
    var pod: String? = null

}

class Weather(

        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("main")
        var main: String? = null,
        @SerializedName("description")
        var description: String? = null,
        @SerializedName("icon")
        var icon: String? = null

)

class Wind {

    @SerializedName("speed")
    var speed: Double? = null
    @SerializedName("deg")
    var deg: Double? = null

}


data class ClimaFinal(val inico: String? = null, val description: String? = null)

data class CrearCiudad(val id: Int? = null, val Nombre: String? = null)