package com.imbored

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imbored.apicalls.YourActivity
import com.imbored.database.ActivityEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BridgeModel(
    private val repo: ResponseRepo
) : ViewModel() {

    /**
     * Data coming from API
     */
    val activityLiveDataFromAPI = MutableLiveData<YourActivity>()

    fun getActivityFromAPI(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getActivity()
            if(response.isSuccessful){
                activityLiveDataFromAPI.postValue(response.body())
            }
        }
    }

    /**
     * function to interact with database
     */
    fun getActivityDataFromDatabase() = repo.getActivityData()

    fun insert(entry: ActivityEntry){
        viewModelScope.launch(Dispatchers.IO){
            repo.insert(entry)
        }
    }

    fun delete(entry: ActivityEntry){
        viewModelScope.launch(Dispatchers.IO){
            repo.delete(entry)
        }
    }
}