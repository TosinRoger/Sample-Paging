package br.com.tosin.samplepaging.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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

        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.loadListPerson().collectLatest {
                mAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            mAdapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { _binding?.recyclerViewPerson?.scrollToPosition(0) }
        }
    }

    private fun configView() {
        mAdapter = PersonAdapter()

        _binding?.recyclerViewPerson?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    private fun configObserver() {
        viewModel = ViewModelProvider(this)
            .get(MainViewModel::class.java)
    }
}