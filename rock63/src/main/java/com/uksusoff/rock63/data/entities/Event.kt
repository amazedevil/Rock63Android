package com.uksusoff.rock63.data.entities

import com.j256.ormlite.field.DatabaseField
import java.util.*

class Event {
    @DatabaseField(id = true)
    var id = 0
    @DatabaseField
    lateinit var title: String
    @DatabaseField
    lateinit var body: String
    @DatabaseField
    var start: Date? = null
    @DatabaseField
    var end: Date? = null
    @DatabaseField
    var mediumThumbUrl: String? = null
    @DatabaseField
    var url: String? = null
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    var place: Place? = null
    @DatabaseField
    var isNotify = false
}