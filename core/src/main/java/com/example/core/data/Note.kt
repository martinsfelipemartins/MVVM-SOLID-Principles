package com.example.core.data

data class Note(
    var id: Int = 0,
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long
)
