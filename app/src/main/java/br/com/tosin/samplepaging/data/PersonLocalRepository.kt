package br.com.tosin.samplepaging.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.tosin.samplepaging.model.Person
import br.com.tosin.samplepaging.repository.PersonCallDb
import kotlinx.coroutines.flow.Flow

class PersonLocalRepository {

    companion object {
        const val PAGE_SIZE = 10
    }

    fun fetchPersonByPage(): Flow<PagingData<Person>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                PersonPagingSource(
                    PersonCallDb(),
                    "Aqui vao outros parametros caso necessarios, string query, filter, anyway "
                )
            }
        ).flow
    }
}