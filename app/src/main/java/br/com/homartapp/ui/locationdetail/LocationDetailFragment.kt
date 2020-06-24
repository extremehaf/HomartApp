package br.com.homartapp.ui.locationdetail

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.homartapp.R
import br.com.homartapp.data.model.LocationDetail
import br.com.homartapp.data.model.LocationReview
import br.com.homartapp.ui.LocationDetailActivity
import br.com.homartapp.ui.LocationDetailActivityArgs
import br.com.homartapp.util.isSequence
import br.com.homartapp.util.loadImage
import br.com.homartapp.util.parseDayOfWeek
import br.com.homartapp.util.parseDayOfWeekInt
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.location_detail_fragment.*
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashMap

class LocationDetailFragment : Fragment() {
    companion object {
        fun newInstance() = LocationDetailFragment()
    }

    private lateinit var viewModel: LocationDetailViewModel
    private val fotosAdapter = FotosRecyclerViewAdapter(arrayListOf<String>())
    private val locationReviewAdapter = ReviewRecyclerViewAdapter(arrayListOf<LocationReview>())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders
            .of(this)
            .get(LocationDetailViewModel::class.java)

        arguments?.let {
            val safeArgs = LocationDetailActivityArgs.fromBundle(it)
            val id = safeArgs.id
            viewModel.load(id)
        }
        val root = inflater.inflate(R.layout.location_detail_fragment, container, false)
        val toolbar: Toolbar = root.findViewById(R.id.toolbar)

        val fotosList: RecyclerView = root.findViewById(R.id.list_fotos)
        val layout = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        fotosList.apply {
            layoutManager = layout
            adapter = fotosAdapter
        }
        val reviewsList: RecyclerView = root.findViewById(R.id.list_reviews)
        val layoutReview = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        reviewsList.apply {
            layoutManager = layoutReview
            adapter = locationReviewAdapter
        }
        (activity as LocationDetailActivity).setSupportActionBar(toolbar)
        (activity as LocationDetailActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as LocationDetailActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as LocationDetailActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        observeViewModel()
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_location_detail, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun observeViewModel() {

        viewModel.locationDetail.observe(viewLifecycleOwner, Observer { detail ->
            detail?.let { it ->
                locationDetail.visibility = View.VISIBLE
                title_page.text = it.name
                imageDetail.loadImage(it.image)
                appCompatRatingBar.rating = it.review.toFloat()
                textRatingValue.text = String.format("%.2f", it.review)
                message.text = it.about
                textPhone.text = PhoneNumberUtils.formatNumber(it.phone, "BRA");
                textAderes.text = it.adress
                textTime.text = getScheduleString(it)

                textAllReviews.text = getString(R.string.text_all_reviews)
                    .replace("XX",it.totalReviews.toString())
            }
        })
        viewModel.locationsLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    locationDetail.visibility = View.GONE
                }
            }
        })
        viewModel.fotosList.observe(viewLifecycleOwner, Observer { fotos ->
            fotos?.let {
                fotosAdapter.updateFotos(it)
            }
        })

        viewModel.reviewList.observe(viewLifecycleOwner, Observer { reviews ->
            reviews?.let {
                locationReviewAdapter.updateReview(it)
            }
        })
    }

    private fun getScheduleString(detail: LocationDetail): String {
        val textSchedule = StringBuilder()
        val list = ArrayList<Map<String, String>>()
        var context = HashMap<String, String>()

        list.add(hashMapOf(Pair("monday","${detail.schedule.monday?.open} - ${detail.schedule.monday?.close}")))
        list.add(hashMapOf(Pair("tuesday","${detail.schedule.tuesday?.open} - ${detail.schedule.tuesday?.close}")))
        list.add(hashMapOf(Pair("wednesday","${detail.schedule.wednesday?.open} - ${detail.schedule.wednesday?.close}")))
        list.add(hashMapOf(Pair("thursday","${detail.schedule.thursday?.open} - ${detail.schedule.thursday?.close}")))
        list.add(hashMapOf(Pair("friday","${detail.schedule.friday?.open} - ${detail.schedule.friday?.close}")))
        list.add(hashMapOf(Pair("saturday","${detail.schedule.saturday?.open} - ${detail.schedule.saturday?.close}")))
        list.add(hashMapOf(Pair("sunday","${detail.schedule.sunday?.open} - ${detail.schedule.sunday?.close}")))

        val diasAgrupados = list.asSequence()
            .flatMap {
                it.asSequence()
            }.groupBy({ it.value }, { it.key })


        val mapDiasAgurapados = diasAgrupados.toMutableMap()
        val novoDia = HashMap<String, List<String>>()
        mapDiasAgurapados.forEach { dia ->
            var sequence = ""
            val lista = dia.value.toMutableList()
            lista.forEach {
                sequence += it.parseDayOfWeekInt(Locale.ENGLISH)
                if (!sequence.isSequence()) {
                    novoDia[dia.key] = listOf(it)
                    lista.remove(it)
                }
            }

            val day1 = lista.first().parseDayOfWeek(Locale.ENGLISH)
            val day2 = lista.last().parseDayOfWeek(Locale.ENGLISH)
            val condicionalDia =
                if (lista.size > 2)
                    getString(R.string.daysConditional)
                else
                    getString(R.string.dayConditional)

            val horario = dia.key.replace("-", getString(R.string.timeConditional))

            if (day1 == day2) {
                textSchedule.append(
                    "$day1: ${
                    if (dia.key.contains("null"))
                        getString(R.string.closed)
                    else horario
                    } \n"
                );
            } else
                textSchedule.append(
                    "$day1 $condicionalDia $day2: ${
                    if (dia.key.contains("null"))
                        getString(R.string.closed)
                    else horario
                    }\n"
                );
        }
        novoDia.forEach {
            val day1 = it.value.first().parseDayOfWeek(Locale.ENGLISH)
            val day2 = it.value.last().parseDayOfWeek(Locale.ENGLISH)
            val horario = it.key.replace("-", getString(R.string.timeConditional))
            val condicionalDia =
                if (it.value.size > 2) getString(R.string.daysConditional) else getString(R.string.dayConditional)
            if (day1 == day2) {
                textSchedule.append(
                    "$day1: ${
                    if (it.key.contains("null"))
                        getString(R.string.closed)
                    else horario
                    } \n"
                );
            } else
                textSchedule.append(
                    "$day1 $condicionalDia $day2: ${
                    if (it.key.contains("null"))
                        getString(R.string.closed)
                    else horario
                    }\n"
                );
        }

        return textSchedule
            .toString()
            .substring(0, textSchedule.length - 2)
    }
}
