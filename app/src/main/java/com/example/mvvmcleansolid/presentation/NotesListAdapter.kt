package com.example.mvvmcleansolid.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.Note
import com.example.mvvmcleansolid.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesListAdapter(var notes: ArrayList<Note>, val action: ListAction) :
    RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(view: ItemNoteBinding) : RecyclerView.ViewHolder(view.root) {
        private val noteTitle = view.title
        private val noteContent = view.content
        private val noteDate = view.date
        private val layout = view.root
        private val noteCount = view.wordCount
        fun bind(note: Note) {

            noteTitle.text = note.title
            noteContent.text = note.content
            val resultData = Date(note.updateTime)
            val simpleDateFormat = SimpleDateFormat("dd MMM, HH:MM")
            noteDate.text = "Last updated: ${simpleDateFormat.format(resultData)}"
            noteCount.text = "Words:${note.countWord}"

            layout.setOnClickListener {
                action.onClick(note.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemNoteBinding =
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(itemNoteBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

}