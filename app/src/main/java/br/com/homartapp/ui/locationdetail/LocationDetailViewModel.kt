package br.com.homartapp.ui.locationdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.homartapp.data.model.*
import br.com.homartapp.data.remote.LocationDetailResponse
import br.com.homartapp.di.DaggerApiComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationDetailViewModel : ViewModel() {
    @Inject
    lateinit var locationService: LocationService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val locationsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val _locationsDetail = MutableLiveData<LocationDetail>()
    val locationDetail: LiveData<LocationDetail> = _locationsDetail


    private fun loadLocationDetail(id: Int) {
        loading.value = true
        locationService.getLocationDetail(id)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<LocationDetailResponse>() {
                override fun onSuccess(data: LocationDetailResponse) {
                    Log.d("error ", "" + data)
                    _locationsDetail.value = LocationDetail(
                        id = data.id,
                        name = data.name,
                        review = data.review,
                        about = data.about,
                        adress = data.adress,
                        phone = data.phone,
                        schedule = Schedule(

//                            sunday = Day(data.schedule.sunday.open, data.schedule.sunday.close),
//                            monday = Day(data.schedule.monday.open, data.schedule.monday.close),
//                            tuesday = Day(data.schedule.tuesday.open, data.schedule.tuesday.close),
//                            wednesday = Day(data.schedule.wednesday.open, data.schedule.wednesday.close),
//                            thursday = Day(data.schedule.thursday.open, data.schedule.thursday.close),
//                            friday = Day(data.schedule.friday.open, data.schedule.friday.close),
//                            saturday = Day(data.schedule.saturday.open, data.schedule.saturday.close)

                        ),
                        type = data.type
                    )

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

    fun load(id:Int) {
        loadLocationDetail(id)
    }
}

