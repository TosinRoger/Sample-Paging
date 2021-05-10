package br.com.tosin.samplepaging.ui.loadstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import br.com.tosin.samplepaging.R

class LoadStateFooterAdapter: LoadStateAdapter<LoadStateFooterViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateFooterViewHolder, loadState: LoadState) {
        when(loadState) {
            LoadState.Loading -> {
                holder.progress.isVisible = true
            }
            else -> {
                holder.progress.isVisible = false
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateFooterViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.item_load_state_footer, parent, false)
        return LoadStateFooterViewHolder(view)
    }
}