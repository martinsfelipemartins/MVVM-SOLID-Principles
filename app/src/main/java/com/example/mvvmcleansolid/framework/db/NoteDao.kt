package com.example.mvvmcleansolid.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = REPLACE)
    suspend fun addNoteEntity(noteEntity: NoteEntity)

    @Query(value = "SELECT * FROM noteEntity WHERE id = :id")
    suspend fun getNoteEntity(id: Long): NoteEntity?

    @Query("SELECT * FROM noteEntity")
    suspend fun getAllNoteEntity(): List<NoteEntity>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NoteEntity)
}