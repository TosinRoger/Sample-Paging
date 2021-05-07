package br.com.tosin.samplepaging.repository.utils

import br.com.tosin.samplepaging.model.Person
import kotlin.random.Random

object FactoryPerson {

    private const val itemsInPage = 10

    fun createListPerson(page: Int? = 0): List<Person> {
        val list = mutableListOf<Person>()

        val indexFirstItem = itemsInPage * page!!

        fullNameList.forEachIndexed { index, fullName ->
            if (index >= indexFirstItem && index < indexFirstItem + itemsInPage) {
                val person = Person(
                    index,
                    fullName,
                    Random.nextInt(18, 60)
                )
                list.add(person)
            }
        }

        return list
    }

    val fullNameList = listOf(
        "Heaven Miranda",
        "Kobe Lambert",
        "Jamie May",
        "Malachi Atkinson",
        "Malia Barr",
        "Alexzander Rosales",
        "Savion Montes",
        "Shirley Stout",
        "Yaretzi Anthony",
        "Andrew Rich",
        "Nikhil Smith",
        "Brendan Zimmerman",
        "Aryan Baker",
        "Jorden Landry",
        "Talon Branch",
        "Mekhi Koch",
        "Tyrese Wilson",
        "Rory Heath",
        "Shyann Pruitt",
        "Kyra Weeks",
        "Finley Duncan",
        "Ryann White",
        "Jaliyah Mack",
        "Miranda Blevins",
        "Alexia Donovan",
        "Ismael Archer",
        "Harmony Valdez",
        "Felicity Vazquez",
        "Bentley Mccann",
        "Alfredo Miller",
        "Jenna Marshall",
        "Kaitlynn Greerv",
        "Reilly Hampton",
        "Amelie Freeman",
        "Noelle Gillespie",
        "Josiah Whitehead",
        "Reginald Chang",
        "Antony Bentley",
        "Karsyn Leonard",
        "Nathen Mcdowell",
        "Jazlyn English",
        "Hadassah Webbv",
        "Victoria Hall",
        "Donavan Tran",
        "Kadence Grimes",
        "Melanie Coleman",
        "Leonidas Adkins",
        "Steven David",
        "Joe Jacobson",
        "Yosef Marshall",
        "Emery Wells",
        "Zachery Glenn",
        "Jordan Jarvis",
        "Crystal Peters",
        "Jayson Mason",
        "Dean Reilly",
        "Karson Hutchinson",
        "Norah Dougherty",
        "Valentina Erickson",
        "Mia Jacobs",
        "Beatrice Doyle",
        "Barbara Sims",
        "Harry Galloway",
        "London Bautista",
        "Danica Fox",
        "Craig Flores",
        "Araceli Ho",
        "Jared Alvarez",
        "Rayne Lester",
        "Katelyn Solomon",
        "Kayla Ruiz",
        "Emily Foley",
        "Kyleigh Hurst",
        "Kingston Leblanc",
        "Paityn Dennis",
        "Todd Ellison",
        "Bridget Coleman",
        "London Vincent",
        "Valery Frey",
        "Nasir Huerta",
        "Bridger Obrien",
        "Ana Graves",
        "Omar Avila",
        "Judah Marshall",
        "Maximilian Hale",
        "Josephine Hurst",
        "Kane Krueger",
        "Kelvin Gray",
        "Kolton Galloway",
        "Shyla Marks",
        "Aisha Juarez",
        "Raegan Leach",
        "Maya Roberts",
        "Ace Koch",
        "Riley Poole",
        "Amirah Ward",
        "Lilianna Robles",
        "Marcos Barber",
        "Orlando Rivas",
        "Vanessa Schaefer",
    )
}