package com.example.tarea2_api_retrofil_y_google_volley;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_JSON {
    @GET("users")
    Call<List<EnviarNEXT>> getEnviarNEXT ();
}
