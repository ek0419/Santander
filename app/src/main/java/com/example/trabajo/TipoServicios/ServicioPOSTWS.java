package com.example.trabajo.TipoServicios;

import com.example.trabajo.retrofit.RequestManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

public class ServicioPOSTWS {

    private String BASEURL;

    public ServicioPOSTWS() {
        BASEURL = "http://cbfe0b77.ngrok.io/";
    }


    public boolean executeServiciePOST() {
        JsonObject result = new JsonObject();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("nombre", "cadena del key NOmbre");

        RequestManager manager = new RequestManager(BASEURL);
        Call<JsonObject> call = manager.create(ServiciosInterfaceWS.class).ServicioPOST(jsonObject);
        Response response = null;
        try {
            response = call.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (response != null) {
            String cadenaJson = response.body().toString();
            new Gson().fromJson(result, JsonObject.class);
            return true;
        } else {
            return false;
        }
    }
}
