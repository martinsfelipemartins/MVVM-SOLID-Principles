package com.example.mvvmcleansolid.framework.di

import com.example.mvvmcleansolid.framework.NoteViewModel
import com.example.mvvmcleansolid.presentation.NoteListViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {

    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: NoteListViewModel)

}