package br.com.tosin.samplepaging.repository

import br.com.tosin.samplepaging.model.Person
import br.com.tosin.samplepaging.repository.utils.FactoryPerson

class PersonLocalRepository {

    /**
     * Provide [Person]
     * @param page range [0 - 9]
     */
    fun fetchPersonByPage(page: Int): List<Person> {
        return FactoryPerson.createListPerson(page)
    }
}