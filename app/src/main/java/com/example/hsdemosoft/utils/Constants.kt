package com.example.hsdemosoft.utils

object Constants {
    const val BASE_URL = "https://countries.trevorblades.com/"
    const val QUERY = "query {countries{name\n native\n capital\n emoji\n currency\n languages {\n code\n name\n }\n }\n \n}"
    const val DB_VERSION:Int = 1
}