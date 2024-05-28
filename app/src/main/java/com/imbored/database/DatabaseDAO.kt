package com.imbored.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry: ActivityEntry)

    @Delete
    fun delete(entry: ActivityEntry)

    @Query("Select * from activityTable")
    fun getActivityCompleted() : LiveData<List<ActivityEntry>>
}