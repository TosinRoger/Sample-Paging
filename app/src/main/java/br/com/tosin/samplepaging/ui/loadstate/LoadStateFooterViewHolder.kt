package br.com.tosin.samplepaging.ui.loadstate

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.tosin.samplepaging.databinding.ItemLoadStateFooterBinding

class LoadStateFooterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val binding = ItemLoadStateFooterBinding.bind(itemView)

    val progress = binding.progressBarItemLoadStateFooter
}