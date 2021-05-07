package br.com.tosin.samplepaging.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.tosin.samplepaging.model.Person
import br.com.tosin.samplepaging.repository.PersonCallDb
import kotlinx.coroutines.flow.Flow

class PersonLocalRepository {

    fun fetchPersonByPage(): Flow<PagingData<Person>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { PersonPagingSource(PersonCallDb()) }
        ).flow
    }
}