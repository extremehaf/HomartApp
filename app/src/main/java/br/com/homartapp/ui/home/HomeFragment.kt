package br.com.homartapp.ui.home

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.homartapp.R
import br.com.homartapp.data.model.Location
import br.com.homartapp.ui.LocationDetailActivity
import br.com.homartapp.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val locationAdapter = LocationsRecyclerViewAdapter(arrayListOf<Location>())

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProviders
            .of(this)
            .get(HomeViewModel::class.java)
        homeViewModel.refresh()
        setHasOptionsMenu(true);
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val swipeRefreshLayout: SwipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            homeViewModel.refresh()
        }

        val locationsList: RecyclerView = root.findViewById(R.id.locationsList)
        val grid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        grid.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        locationsList.apply {
            layoutManager = grid
            adapter = locationAdapter
        }

        observeViewModel()
        return root
    }

    private fun observeViewModel() {
        homeViewModel.locations.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                locationsList.visibility = View.VISIBLE
                locationAdapter.updateLocations(it)
            }
        })
        homeViewModel.locationsLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })
        homeViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    locationsList.visibility = View.GONE
                }
            }
        })
    }

}
