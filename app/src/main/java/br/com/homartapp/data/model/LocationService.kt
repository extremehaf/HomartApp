package br.com.homartapp.data.model

import br.com.homartapp.data.remote.LocationApi
import br.com.homartapp.data.remote.LocationDetailResponse
import br.com.homartapp.data.remote.LocationResponse
import br.com.homartapp.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class LocationService {
    @Inject
    lateinit var api: LocationApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getLocation(): Single<LocationResponse>? {
        return api.getLocations()
    }

    fun getLocationDetail(id: Integer): Single<LocationDetailResponse?>? {
        return api.getLocationDetails(id)
    }
}