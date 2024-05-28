package com.imbored.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ActivityEntry::class],
    version = 1,
    exportSchema = false
)
abstract class ProjectActivityDatabase : RoomDatabase(){

    abstract fun getDatabaseDao():DatabaseDAO

    companion object{
        private const val DB_NAME = "ActivityDatabase.db"
        private  var instance: ProjectActivityDatabase?=null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,ProjectActivityDatabase::class.java, DB_NAME
        ).build()
    }
}