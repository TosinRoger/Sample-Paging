package br.com.tosin.samplepaging.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import br.com.tosin.samplepaging.R
import br.com.tosin.samplepaging.databinding.MainFragmentBinding
import br.com.tosin.samplepaging.ui.main.adapter.PersonAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

/**
 * https://developer.android.com/codelabs/android-paging
 */
class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mAdapter: PersonAdapter
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        job?.cancel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configObserver()
        configView()

        loadNextPage()
    }

    private fun configView() {
        mAdapter = PersonAdapter()

        _binding?.recyclerViewPerson?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        val layoutManager = binding.recyclerViewPerson.layoutManager as LinearLayoutManager
        _binding?.recyclerViewPerson?.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })

        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.loadPage().collectLatest {
                mAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            mAdapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading  }
                .collect { _binding?.recyclerViewPerson?.scrollToPosition(0) }
        }
    }

    private fun configObserver() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel
    }

    private fun loadNextPage() {

    }
}