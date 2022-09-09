package com.example.mvvmcleansolid.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.Note
import com.example.mvvmcleansolid.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotesListAdapter(var notes: ArrayList<Note>) :
    RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>){
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(view: ItemNoteBinding) : RecyclerView.ViewHolder(view.root) {
        private val noteTitle = view.title
        private val noteContent = view.content
        private val noteDate = view.date
        fun bind(note: Note) {

            noteTitle.text = note.title.toString()
            noteContent.text = note.content
            val simpleDateFormat = SimpleDateFormat("dd MMM, HH:MM")
            val resultData = Date(note.updateTime)
            noteDate.text = "Last updated: ${simpleDateFormat.format(resultData)}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemNoteBinding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(itemNoteBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

}