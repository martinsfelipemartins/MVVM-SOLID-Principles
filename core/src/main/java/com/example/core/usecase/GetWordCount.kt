package com.example.core.usecase

import com.example.core.data.Note

class GetWordCount {
    operator fun invoke(note: Note) =
        getCount(note.title) + getCount(note.content)
}

private fun getCount(string: String) =
    string.split(" ", "\n").count {
        it.contains(Regex(".*[a-zA-Z].*"))
    }
