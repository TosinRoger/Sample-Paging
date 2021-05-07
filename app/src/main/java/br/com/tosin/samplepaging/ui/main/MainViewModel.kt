package br.com.tosin.samplepaging.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.tosin.samplepaging.data.PersonLocalRepository
import br.com.tosin.samplepaging.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private val dao: PersonLocalRepository = PersonLocalRepository()
    private var currentPersonResult: Flow<PagingData<Person>>? = null

    fun loadPage(): Flow<PagingData<Person>> {
        val newResult: Flow<PagingData<Person>> = dao
            .fetchPersonByPage()
            .cachedIn(viewModelScope)
        currentPersonResult = newResult

        return newResult
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            viewModelScope.launch {
                dao.fetchPersonByPage()
            }
        }
    }
}