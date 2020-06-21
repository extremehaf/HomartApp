package br.com.homartapp.data.repository


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.com.homartapp.data.remote.LocationDetailResponse
import br.com.homartapp.data.remote.LocationResponse

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory;

class LocationRepository {
    val BASE_URL = "https://hotmart-mobile-app.herokuapp.com"

    //private lateinit var locationWebService: LocationService
    private val instance: LocationRepository? = null
    //private var locationLiveData: MutableLiveData<List<LocationResponse?>?>?
    //private var locationDetailsLiveData: MutableLiveData<LocationDetailResponse?>?


//    init {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = (HttpLoggingInterceptor.Level.BODY)
//
//        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
//        locationWebService = this.getLocationWebService()
//        locationLiveData = MutableLiveData<List<LocationResponse?>?>()
//        locationDetailsLiveData = MutableLiveData<LocationDetailResponse?>()
//    }
//
//    fun getLocations() {
//        locationWebService.getLocations()
//            .enqueue(object : Callback<List<LocationResponse?>> {
//
//                override fun onResponse(
//                    call: Call<List<LocationResponse?>>,
//                    response: Response<List<LocationResponse?>>
//                ) {
//                    locationLiveData?.postValue(response.body())
//                }
//
//                override fun onFailure(call: Call<List<LocationResponse?>>, t: Throwable) {
//
//                }
//            })
//    }
//
//    fun getLocationsDetails(id: Integer?) {
//        locationWebService.getLocationDetails(id)
//            .enqueue(object : Callback<LocationDetailResponse?> {
//                override fun onResponse(
//                    call: Call<LocationDetailResponse?>?,
//                    response: Response<LocationDetailResponse?>
//                ) {
//                    locationDetailsLiveData?.postValue(response.body())
//                }
//
//                override fun onFailure(
//                    call: Call<LocationDetailResponse?>?,
//                    t: Throwable?
//                ) {
//                }
//            })
//    }
//
//    fun getCitiesLiveData(): MutableLiveData<List<LocationResponse?>?>? {
//        return locationLiveData
//    }
//
//    fun getCityDetailsLiveData(): MutableLiveData<LocationDetailResponse?>? {
//        return locationDetailsLiveData
//    }
//
//    fun getLocationWebService(): LocationService {
//        if (locationWebService == null) {
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = (HttpLoggingInterceptor.Level.BODY)
//
//            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
//            locationWebService = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(LocationService::class.java)
//        }
//        return locationWebService
//    }
}

private fun <T> Call<T>?.enqueue(any: Any) {

}
