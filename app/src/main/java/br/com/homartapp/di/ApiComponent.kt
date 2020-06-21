package br.com.homartapp.di
import br.com.homartapp.data.model.LocationService
import br.com.homartapp.ui.home.HomeViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: LocationService)
    fun inject(viewModel: HomeViewModel)
}