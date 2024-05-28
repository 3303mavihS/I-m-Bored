package com.imbored.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activityTable")
data class ActivityEntry(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val activity:String
)
