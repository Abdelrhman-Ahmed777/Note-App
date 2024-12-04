package com.example.note.viewModel

import android.app.Application
import android.content.ClipData.Item
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.ItemData

class NoteViewModel(app:Application , private val repository: Repository):AndroidViewModel(app) {



    fun addNote(item:ItemData) = viewModelScope.launch(Dispatchers.IO){
        repository.insertNote(item)
    }

    fun deleteNote(item: ItemData) = viewModelScope.launch (Dispatchers.IO){
        repository.deleteNote(item)
    }
    fun updateNote(item: ItemData) = viewModelScope.launch (Dispatchers.IO){
        repository.updateNote(item)
    }

}