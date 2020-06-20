package br.com.homartapp.data.remote

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  LocationService {

    @GET("/locations")
    fun getLocations(): Call<List<LocationResponse>>?

    @GET("/locations/{id}")
    fun getLocationDetails(@Path("id") id: Integer?): Call<LocationDetailResponse?>?
}