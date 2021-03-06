package com.uksusoff.rock63.data.entities

import com.j256.ormlite.field.DatabaseField
import java.util.*

class NewsItem {
    @DatabaseField(id = true)
    var id = 0
    @DatabaseField
    lateinit var title: String
    @DatabaseField
    lateinit var body: String
    @DatabaseField
    var date: Date? = null
    @DatabaseField
    var smallThumbUrl: String? = null
    @DatabaseField
    var mediumThumbUrl: String? = null
    @DatabaseField
    var isNew = false
    @DatabaseField
    var url: String? = null

}