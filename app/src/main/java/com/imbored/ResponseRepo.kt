package com.imbored

import com.imbored.apicalls.ResponseRetrofitService
import com.imbored.database.ActivityEntry
import com.imbored.database.DatabaseDAO

class ResponseRepo(
    private val responseRetrofitService: ResponseRetrofitService,
    private val dao: DatabaseDAO
) {
    //data getting from RetrofitService
    suspend fun getActivity() = responseRetrofitService.getTheActivity()

    //data getting from ProjectActivityDatabase Access Object (DAO)
    fun insert(entry: ActivityEntry){
        dao.insert(entry)
    }

    fun delete(entry: ActivityEntry){
        dao.delete(entry)
    }

    fun getActivityData() = dao.getActivityCompleted()

}