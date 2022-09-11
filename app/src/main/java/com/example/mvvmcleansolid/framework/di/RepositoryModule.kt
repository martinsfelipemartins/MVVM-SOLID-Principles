package com.example.mvvmcleansolid.framework.di

import android.app.Application
import com.example.core.repository.NoteRepository
import com.example.mvvmcleansolid.framework.RoomNoteDatabase
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesRepository(app: Application) = NoteRepository(RoomNoteDatabase(app))
}