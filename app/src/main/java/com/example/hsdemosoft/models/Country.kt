package com.example.hsdemosoft.models

import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class Country(
    val capital: String,
    val currency: String,
    val emoji: String,
    val languages: ArrayList<Language>,
    val name: String,
    val native: String
)