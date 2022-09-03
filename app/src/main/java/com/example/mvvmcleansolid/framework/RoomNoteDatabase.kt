package com.example.mvvmcleansolid.framework

import android.content.Context
import com.example.core.data.Note
import com.example.core.repository.NoteDataSource
import com.example.mvvmcleansolid.framework.db.DatabaseService
import com.example.mvvmcleansolid.framework.db.NoteEntity

class RoomNoteDatabase(context: Context) : NoteDataSource {
    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntity().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}