package br.com.tosin.samplepaging.repository

import br.com.tosin.samplepaging.model.Person

interface PersonService {
    fun fetchPersonByPage(page: Int): List<Person>
}