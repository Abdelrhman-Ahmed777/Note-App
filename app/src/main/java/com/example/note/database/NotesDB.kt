package com.example.note.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import model.ItemData

@Database(
    entities = [ItemData::class ] ,
    version = 1 ,
    exportSchema = false
)
abstract class NotesDB : RoomDatabase() {

    abstract fun noteDao(): NotesDao

    companion object {
        @Volatile
        var Instance: NotesDB? = null
        fun getDataBase(context: Context): NotesDB {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext ,
                    NotesDB::class.java ,
                    "notes_db"
                ).build()
                Instance = instance
                instance
            }
        }
    }
}

