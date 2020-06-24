package br.com.homartapp.di

import br.com.homartapp.data.model.LocationService
import br.com.homartapp.data.remote.LocationApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://hotmart-mobile-app.herokuapp.com"
    @Provides
    fun provideUsersApi(): LocationApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(LocationApi::class.java)
    }
    @Provides
    fun provideLocationService(): LocationService {
        return LocationService()
    }
}