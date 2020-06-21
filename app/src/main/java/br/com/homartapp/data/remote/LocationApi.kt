package br.com.homartapp.data.remote

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

public interface  LocationApi {

    @GET("/locations")
    fun getLocations(): Single<LocationResponse>?

    @GET("/locations/{id}")
    fun getLocationDetails(@Path("id") id: Integer?): Single<LocationDetailResponse?>?
}