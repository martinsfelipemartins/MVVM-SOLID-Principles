package com.example.mvvmcleansolid.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.core.data.Note
import com.example.core.repository.NoteRepository
import com.example.core.usecase.AddNote
import com.example.core.usecase.GetAllNotes
import com.example.core.usecase.GetNote
import com.example.core.usecase.RemoveNote
import com.example.mvvmcleansolid.framework.RoomNoteDatabase
import com.example.mvvmcleansolid.framework.UseCases
import com.example.mvvmcleansolid.framework.di.ApplicationModule
import com.example.mvvmcleansolid.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteListViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val noteList = MutableLiveData<List<Note>>()

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(application))
            .build()
            .inject(this)
    }

    @Inject
    lateinit var useCases : UseCases


    fun getNoteList(){
        coroutineScope.launch {
            val list = useCases.getAllNotes()
            list.forEach{
                it.countWord = useCases.getWordCount.invoke(it)
            }
            noteList.postValue(list)
        }
    }

}