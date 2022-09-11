package com.example.mvvmcleansolid.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.Note

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var title: String,
    var content: String,
    @ColumnInfo(name = "creation_data")
    var creationTime: Long,
    @ColumnInfo(name = "update_time")
    var updateTime: Long
)
{
    companion object{
        fun fromNote(note: Note) = NoteEntity(
            title = note.title,
            content = note.content,
            creationTime = note.creationTime,
            updateTime = note.updateTime,
            id = note.id
        )
    }
    fun toNote() = Note(title = title, id = id, content = content, creationTime = creationTime, updateTime = updateTime)
}
