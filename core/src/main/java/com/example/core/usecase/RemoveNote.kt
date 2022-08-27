package com.example.core.usecase

import com.example.core.data.Note
import com.example.core.repository.NoteRepository

class RemoveNote(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.removeNote(note)
}