package br.com.homartapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.homartapp.R
import br.com.homartapp.ui.locationdetail.LocationDetailFragment

class LocationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_detail_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LocationDetailFragment.newInstance())
                .commitNow()
        }
    }
}
