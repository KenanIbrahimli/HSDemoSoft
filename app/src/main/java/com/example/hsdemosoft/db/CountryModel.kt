package com.example.hsdemosoft.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CountryList")
data class CountryDBModel (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var respose: String
)