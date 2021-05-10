package br.com.tosin.samplepaging.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.tosin.samplepaging.model.Person
import br.com.tosin.samplepaging.repository.PersonCallDb

private const val STARTING_PAGE_INDEX = 0

class PersonPagingSource(
    private val service: PersonCallDb,
    private val otherParams: String
) : PagingSource<Int, Person>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        /**
         * Key of the page to be loaded. If this is the first time that load is called, LoadParams.key will be null
         */
        val position = params.key ?: STARTING_PAGE_INDEX
        val response = service.fetchPersonByPage(position)
        val nextKey = if (response.isEmpty()) {
            null
        } else {
            position + 1
        }
        return LoadResult.Page(
            data = response,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}