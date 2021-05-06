package br.com.tosin.samplepaging.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.tosin.samplepaging.databinding.ItemPersonBinding

class PersonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPersonBinding.bind(itemView)

    val index = binding.textViewIndex
    val fullName = binding.textViewFullName
    val age = binding.textViewAge
}