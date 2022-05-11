package dev.konnov.feature.realm.data.model

import io.realm.RealmObject

class NewsReportDto : RealmObject {
    var title: String = ""
    var description: String = ""
}