package br.com.homartapp.ui.locationdetail

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.homartapp.R
import br.com.homartapp.ui.LocationDetailActivity
import br.com.homartapp.ui.LocationDetailActivityArgs
import kotlinx.android.synthetic.main.location_detail_fragment.*
import kotlinx.android.synthetic.main.content_scrolling.*


class LocationDetailFragment : Fragment() {
    companion object {
        fun newInstance() = LocationDetailFragment()
    }

    private lateinit var viewModel: LocationDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders
            .of(this)
            .get(LocationDetailViewModel::class.java)
        //var id = LocationDetailActivityArgs.fromBundle(savedInstanceState!!).id

        arguments?.let {
            val safeArgs = LocationDetailActivityArgs.fromBundle(it)
            val id = safeArgs.id
            viewModel.load(id)
        }
        val root = inflater.inflate(R.layout.location_detail_fragment, container, false)
        val toolbar: Toolbar = root.findViewById(R.id.toolbar)

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
            detail?.let {
                locationDetail.visibility = View.VISIBLE
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
    }


}
