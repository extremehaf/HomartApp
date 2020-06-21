package br.com.homartapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import br.com.homartapp.data.model.Location
import br.com.homartapp.data.model.LocationService
import br.com.homartapp.data.remote.LocationResponse
import br.com.homartapp.di.DaggerApiComponent

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class HomeViewModel : ViewModel() {

    @Inject
    lateinit var locationService: LocationService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val locationsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> = _locations

    private fun loadLocations() {
        loading.value = true
        locationService.getLocation()
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<LocationResponse>() {
                override fun onSuccess(data: LocationResponse) {
                    Log.d("error ", "" + data)
                    _locations.value = data.listLocations.map { locationResponse ->
                        Location(
                            name = locationResponse.name,
                            review = locationResponse.review,
                            type = locationResponse.type,
                            id = locationResponse.id
                        )
                    }
                    locationsLoadError.value = false
                    loading.value = false
                }

                override fun onError(e: Throwable) {
                    locationsLoadError.value = true
                    loading.value = false
                    Log.d("error ", "" + e.printStackTrace())
                }
            })?.let {
                disposable.add(
                    it
                )
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun refresh() {
        loadLocations()
    }
}