package br.com.tosin.samplepaging.ui.main

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import br.com.tosin.samplepaging.data.PersonLocalRepository
import br.com.tosin.samplepaging.model.Person
import kotlinx.coroutines.flow.Flow

class MainViewModel: ViewModel() {

    private val repository: PersonLocalRepository = PersonLocalRepository()

    fun loadListPerson(): Flow<PagingData<Person>> {
        return repository.fetchPersonByPage()
    }
}