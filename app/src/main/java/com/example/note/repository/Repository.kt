package com.example.note.repository

import com.example.note.database.NotesDB
import model.ItemData

class Repository(private val db: NotesDB) {

    suspend fun insertNote(note: ItemData) = db.noteDao().addNote(note)

    suspend fun updateNote(note: ItemData) = db.noteDao().update(note)

    suspend fun deleteNote(note: ItemData) = db.noteDao().deleteNote(note)

    fun getAllNotes() = db.noteDao().getAllNotes()

    fun getAllNotesOrderedByTitle() = db.noteDao().getAllNotesOrderedByTitle()

    fun getAllNotesOrderedByContent() = db.noteDao().getAllNotesOrderedByContent()

    fun getAllNotesOrderedByDate() = db.noteDao().getAllNotesOrderedByDate()

    fun getAllFav() = db.noteDao().getAllFavoriteNotes()

    fun getAllArch() = db.noteDao().getAllArchivedNotes()

    // main search bar

    fun searchedByTitle(title: String?) = db.noteDao().selectNoteByTitle(title)

    fun searchedByContent(content: String?) = db.noteDao().selectNoteByTitle(content)

    fun searchedByDate(date: String) = db.noteDao().selectNoteByTitle(date)

    // favorites search bar

    fun searchFavNoteByTitle(title: String?) = db.noteDao().selectFavNoteByTitle(title)

    fun searchFavNoteByContent(content: String?) = db.noteDao().selectFavNoteByContent(content)

    fun searchFavNoteByDate(date: String) = db.noteDao().selectFavNoteByDate(date)

    // archive search bar

    fun searchArchNoteByTitle(title: String?) = db.noteDao().selectArchNoteByTitle(title)

    fun searchArchNoteByContent(content: String?) = db.noteDao().selectArchNoteByContent(content)

    fun searchArchNoteByDate(date: String) = db.noteDao().selectArchNoteByDate(date)

}