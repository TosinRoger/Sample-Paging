package br.com.tosin.samplepaging.repository

import br.com.tosin.samplepaging.model.Person
import br.com.tosin.samplepaging.repository.utils.FactoryPerson

class PersonCallDb: PersonService {
    override fun fetchPersonByPage(page: Int): List<Person> {
        return FactoryPerson.createListPerson(page)
    }
}