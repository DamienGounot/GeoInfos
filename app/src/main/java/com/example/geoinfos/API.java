package com.example.geoinfos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface API {
    @GET("all")
    Call<List<Country>> getCountries();
}
