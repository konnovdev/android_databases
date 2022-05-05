package dev.konnov.feature.realm.data.testik

import io.realm.RealmObject

// TODO delete, it's just a test class to see realm working
class Person : RealmObject {
    var name: String = "Foo"
    var dog: Dog? = null
}