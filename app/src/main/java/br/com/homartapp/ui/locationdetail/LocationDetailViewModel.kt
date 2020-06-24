package br.com.homartapp.ui.locationdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.homartapp.data.model.*
import br.com.homartapp.data.remote.LocationDetailResponse
import br.com.homartapp.di.DaggerApiComponent
import br.com.homartapp.util.Util
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject
import kotlin.random.Random

class LocationDetailViewModel : ViewModel() {

    @Inject
    lateinit var locationService: LocationService

    init {
        DaggerApiComponent.create().inject(this)

    }

    private val disposable = CompositeDisposable()

    val locationsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val locationDetail: MutableLiveData<LocationDetail> = MutableLiveData<LocationDetail>()
    val fotosList = MutableLiveData<List<String>>()
    val reviewList = MutableLiveData<List<LocationReview>>()


    private fun loadLocationDetail(id: Int) {
        loading.value = true
        locationService.getLocationDetail(id)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<LocationDetailResponse>() {
                override fun onSuccess(data: LocationDetailResponse) {
                    val detail = LocationDetail(
                        id = data.id,
                        name = data.name,
                        review = data.review,
                        about = data.about,
                        adress = data.adress,
                        phone = data.phone,
                        schedule = Schedule(),
                        type = data.type
                    )
                    try {
                        if (data.schedule is ArrayList<*>) {
                            val json = Util.toJson(data.schedule)
                            val schedules = Util.fromJson<ArrayList<Schedule>>(json)
                            schedules.let {
                                val schedule = schedules[0]
                                detail.schedule = Schedule(

                                    sunday = Day(schedule.sunday?.open, schedule.sunday?.close),
                                    monday = Day(schedule.monday?.open, schedule.monday?.close),
                                    tuesday = Day(schedule.tuesday?.open, schedule.tuesday?.close),
                                    wednesday = Day(
                                        schedule.wednesday?.open,
                                        schedule.wednesday?.close
                                    ),
                                    thursday = Day(
                                        schedule.thursday?.open,
                                        schedule.thursday?.close
                                    ),
                                    friday = Day(schedule.friday?.open, schedule.friday?.close),
                                    saturday = Day(
                                        schedule.saturday?.open,
                                        schedule.saturday?.close
                                    )
                                )
                            }

                        } else {
                            val json = Util.toJson(data.schedule)
                            val schedule = Util.fromJson<Schedule>(json)
                            detail.schedule = Schedule(

                                sunday = Day(schedule.sunday?.open, schedule.sunday?.close),
                                monday = Day(schedule.monday?.open, schedule.monday?.close),
                                tuesday = Day(schedule.tuesday?.open, schedule.tuesday?.close),
                                wednesday = Day(
                                    schedule.wednesday?.open,
                                    schedule.wednesday?.close
                                ),
                                thursday = Day(schedule.thursday?.open, schedule.thursday?.close),
                                friday = Day(schedule.friday?.open, schedule.friday?.close),
                                saturday = Day(schedule.saturday?.open, schedule.saturday?.close)
                            )
                        }
                        loadFotos()
                        loadReviews()
                        detail.totalReviews = 12
                        locationDetail.value = detail

                        locationsLoadError.value = false
                        loading.value = false
                    } catch (e: Throwable) {
                        locationsLoadError.value = true
                        loading.value = false
                        Log.d("error ", "" + e.printStackTrace())
                    }
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

    fun load(id: Int) {
        loadLocationDetail(id)
    }

    private fun loadFotos() {
        var lista = ArrayList<String>()
        for (x in 0 until 15)
            lista.add(fotoAleatoria())

        fotosList.value = lista
    }

    private fun fotoAleatoria(): String {
        var num = Random.nextInt(1, 6)
        var num2 = Random.nextInt(100, 300)
        when (num) {
            1 -> {
                return "https://picsum.photos/${num2}/300.jpg";
            }
            2 -> {
                return "https://picsum.photos/${num2}/400.jpg";
            }
            3 -> {
                return "https://picsum.photos/${num2}/500.jpg";
            }
            4 -> {
                return "https://picsum.photos/${num2}/600.jpg";
            }
            5 -> {
                return "https://picsum.photos/${num2}/700.jpg";
            }
            6 -> {
                return "https://picsum.photos/${num2}/800.jpg";
            }

        }
        return "https://picsum.photos/${num2}/800.jpg"
    }

    private fun loadReviews() {
        var lista = ArrayList<LocationReview>()

        lista.add(LocationReview(fotoAleatoria(),
                5.0,
                "Fantástico!!",
                "Tortas deliciosas. Os waffles também estavam muito bons. Equipe muito atenciosa. :)",
                "Tomás Montenegro, Belo Horizonte - MG"
            ))

        lista.add(LocationReview(fotoAleatoria(),
                4.0,
                "Café da manhã delicioso",
                "Nós fomos para o brunch e estava realmente delicioso. Pães, ovos, café, sucos naturais. Não é muito barato mas vale a pena.",
                "Glória Ruiz, São João Del Rey - MG"
            ))

        lista.add(
            LocationReview(fotoAleatoria(),
                4.0,
                "Ótima comida",
                "Comidas frescas e de boa qualidade. Pães e quitandas saindo do forno toda hora. Cafés especiais e ambiente agradável.",
                "Shirley Jones, Mountain View - CA"
            ))
        reviewList.value = lista;
    }
}

