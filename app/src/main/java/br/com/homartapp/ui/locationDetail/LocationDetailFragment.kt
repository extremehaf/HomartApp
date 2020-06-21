package br.com.homartapp.ui.locationdetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.homartapp.R

class LocationDetailFragment : Fragment() {

    companion object {
        fun newInstance() = LocationDetailFragment()
    }

    private lateinit var viewModel: LocationDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.location_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LocationDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}