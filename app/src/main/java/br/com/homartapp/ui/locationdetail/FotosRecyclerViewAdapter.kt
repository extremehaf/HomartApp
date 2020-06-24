package br.com.homartapp.ui.locationdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.homartapp.databinding.ItemListaBinding


class FotosRecyclerViewAdapter(var fotos: ArrayList<String>) :
    RecyclerView.Adapter<FotosViewHolder>() {

    fun updateFotos(newFotos: List<String>) {
        fotos.clear()
        fotos.addAll(newFotos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = FotosViewHolder(
        ItemListaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = fotos.size
    override fun onBindViewHolder(holder: FotosViewHolder, position: Int) {
        holder.bind(fotos[position])
    }
}

class FotosViewHolder(val binding: ItemListaBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(foto: String) {
        binding.imageUrl = foto
        binding.executePendingBindings()
    }


}