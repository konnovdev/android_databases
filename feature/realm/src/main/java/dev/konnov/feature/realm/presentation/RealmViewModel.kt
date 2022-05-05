package dev.konnov.feature.realm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.feature.realm.data.testik.Dog
import dev.konnov.feature.realm.data.testik.Person
import io.realm.Realm
import io.realm.query
import io.realm.RealmConfiguration
import io.realm.notifications.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealmViewModel @Inject constructor() : ViewModel() {

    fun testDbSpeed() {
        // TODO implement
        testRealmWorks()
    }

    // TODO delete this method, it's just for testing that realm works
    private fun testRealmWorks() {
        val configuration = RealmConfiguration.with(
            schema = setOf(
                Person::class,
                Dog::class
            )
        ) // use the RealmConfiguration.Builder for more options
        val realm = Realm.open(configuration)

        //WRITE
        // plain old kotlin object
        val person = Person().apply {
            name = "Carlo"
            dog = Dog().apply { name = "Fido"; age = 16 }
        }

// persist it in a transaction
        realm.writeBlocking { // this : MutableRealm
            val managedPerson = this.copyToRealm(person)
        }

// asynchroneous updates with Kotlin coroutines
        viewModelScope.launch {
            realm.write {
                val managedPerson = copyToRealm(person)
            }
        }

        // QUERY
        val all = realm.query<Person>().find()

// Persons named 'Carlo'
        val personsByNameQuery = realm.query<Person>( "name = $0", "Carlo")
        val filteredByName = personsByNameQuery.find()

// Person having a dog aged more than 7 with a name starting with 'Fi'
        val filteredByDog =
            realm.query<Person>("dog.age > $0 AND dog.name BEGINSWITH $1", 7, "Fi").find()

// Observing for changes with Coroutine Flows
        viewModelScope.async {
            personsByNameQuery.asFlow().collect { result: ResultsChange<Person> ->
                println("Realm updated: Number of persons is ${result.list.size}")
            }
        }

        //UPDATE
        // Find the first Person without a dog
        realm.query<Person>("dog == NULL LIMIT(1)")
            .first()
            .find()
            ?.also { personWithoutDog ->
                // Add a dog in a transaction
                realm.writeBlocking {
                    findLatest(personWithoutDog)?.dog = Dog().apply { name = "Laika"; age = 3 }
                }
            }

        //DELETE
        // delete all Dogs
        realm.writeBlocking {
            // Selected by a query
            val query = this.query<Dog>()
            delete(query)

            // From a results
            val results = query.find()
            delete(results)

            // From individual objects
            results.forEach { delete(it) }
        }

        // Observing data changes
        // A Realm can be observed globally for changes on its data.
        viewModelScope.launch {
            realm.asFlow()
                .collect { realmChange: RealmChange<Realm> ->
                    when (realmChange) {
                        is InitialRealm<*> -> println("Initial Realm")
                        is UpdatedRealm<*> -> println("Realm updated")
                    }
                }
        }
    }
}