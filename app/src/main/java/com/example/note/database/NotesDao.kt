package com.example.note.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import model.ItemData

@Dao
interface NotesDao {


    @Transaction
    @Upsert // adding note
    suspend fun addNote(item: ItemData)

    @Transaction
    @Delete // deleting a note
    suspend fun deleteNote(item: ItemData)

    @Transaction
    @Update // updating the note
    suspend fun update(item: ItemData)


    @Transaction
    @Query("SELECT * FROM note_table WHERE noteId = :itemId")
    fun getNoteById(itemId: Int): ItemData

    @Transaction
    @Query("SELECT * FROM note_table ") // get all notes by the notes content
     fun getAllNotes(): LiveData<List<ItemData>>

    @Transaction
    @Query("select * from note_table order by titles ") // get all notes by the notes title
     fun getAllNotesOrderedByTitle():  LiveData<List<ItemData>>

    @Transaction
    @Query("select * from note_table order by content DESC") // get all notes by the notes content
     fun getAllNotesOrderedByContent():  LiveData<List<ItemData>>

    @Transaction
    @Query("select * from note_table order by date DESC") // get all notes by the notes date
     fun getAllNotesOrderedByDate():  LiveData<List<ItemData>>

    @Transaction
    @Query("select * from note_table where `favorite items`== 1 ") // get all the favorite notes
     fun getAllFavoriteNotes():  LiveData<List<ItemData>>

    @Transaction
    @Query("select * from note_table where `archived items` == 1 ") // get all the archived notes
     fun getAllArchivedNotes():  LiveData<List<ItemData>>

    @Transaction
    @Query("select * from note_table where `archived items` == 1 and `favorite items`==1 ") // get all the favorite notes in the archive
     fun getAllFavArchivedNotes():  LiveData<List<ItemData>>

    @Transaction
    @Query("select * from note_table where titles = :title") // get one note by the title
     fun selectNoteByTitle(title: String?): ItemData

    @Transaction
    @Query("select * from note_table where content = :content") // get one note by the content
     fun selectNoteByContent(content: String?): ItemData

    @Transaction
    @Query("select * from note_table where date = :date") // get one note by the date
     fun selectNoteByDate(date: String): ItemData

    @Transaction
    @Query("select * from note_table where titles = :title and `favorite items` == 1") // get one note from favorite by the title
     fun selectFavNoteByTitle(title: String?): ItemData

    @Transaction
    @Query("select * from note_table where content = :content and `favorite items` == 1") // get one note from favorite by the content
     fun selectFavNoteByContent(content: String?): ItemData

    @Transaction
    @Query("select * from note_table where date = :date and `favorite items` == 1") // get one note from favorite by the date
     fun selectFavNoteByDate(date: String): ItemData

    @Transaction
    @Query("select * from note_table where titles = :title and `archived items`== 1") // get one note from archive by the title
     fun selectArchNoteByTitle(title: String?): ItemData

    @Transaction
    @Query("select * from note_table where content = :content and `archived items`== 1") // get one note from archive by the content
     fun selectArchNoteByContent(content: String?): ItemData

    @Transaction
    @Query("select * from note_table where date = :date and `archived items`== 1") // get one note from archive by the date
     fun selectArchNoteByDate(date: String): ItemData

    @Transaction
    @Query("select * from note_table where `favorite items`==1 and `archived items`==1") // get the favorite notes from the archive
     fun getFavNoteFromArch(): List<ItemData>

}



