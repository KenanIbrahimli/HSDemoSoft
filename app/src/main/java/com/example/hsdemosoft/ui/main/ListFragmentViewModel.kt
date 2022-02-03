package com.example.hsdemosoft.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hsdemosoft.MainApplication
import com.example.hsdemosoft.data.repository.RepositoryImpl
import com.example.hsdemosoft.db.CountryRepository
import com.example.hsdemosoft.models.Country
import com.example.hsdemosoft.models.CountryModel
import com.example.hsdemosoft.utils.Constants
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

class ListFragmentViewModel : ViewModel() {

    private var countryResponse: MutableLiveData<ArrayList<Country>> = MutableLiveData()
    var _countryResponse: LiveData<ArrayList<Country>> = countryResponse

    private var job = Job()
    private var corutineScope = CoroutineScope(Dispatchers.IO + job)


    @Inject
    lateinit var gSon: Gson

    @Inject
    lateinit var repository: RepositoryImpl

    @Inject
    lateinit var dbRepository: CountryRepository

    @SuppressLint("StaticFieldLeak")
    @Inject
    lateinit var context: Context

    init {
        MainApplication.appComponents.inject(this)
    }


    fun getAllCountryData() {
        val paramObject = JSONObject()
        paramObject.put("query", Constants.QUERY)
        corutineScope.launch {
                //make request to URL
                val result = repository.getData(paramObject.toString())

                if (result.body() != null) {
                    val jsonString = result.body().toString()

                    //add data to DB
                    dbRepository.addDataToDb(jsonString)

                    //convert json to model
                    val model = gSon.fromJson(jsonString, CountryModel::class.java)

                    //add data to livedata
                    countryResponse.postValue(model.data.countries)
                    Log.e("Tag", model.data.countries.toString())
                }
            }
    }


    fun getCachedData(){
        corutineScope.launch {
            //get cached data from DB
            val cachedResponse = dbRepository.getData()

            if(cachedResponse == null){
                countryResponse.postValue(null)
            }else{
                //convert json to model
                val cachedModel = gSon.fromJson(cachedResponse.respose, CountryModel::class.java)

                //add data to livedata
                countryResponse.postValue(cachedModel.data.countries)
            }
        }
    }

}

