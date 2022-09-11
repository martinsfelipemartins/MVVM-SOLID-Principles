package com.example.mvvmcleansolid.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcleansolid.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment(), ListAction {
    lateinit var viewModel: NoteListViewModel
    private val notesListAdapter = NotesListAdapter(arrayListOf(), this
    )

    lateinit var binding: FragmentNoteListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(layoutInflater, container, false)
         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }

        viewModel = ViewModelProviders.of(this)[NoteListViewModel::class.java]

        binding.floatingActionButtonAdd.setOnClickListener {
            goToNoteDetails()
        }
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.noteList.observe(viewLifecycleOwner) { notes ->
            binding.progressBar.visibility = View.GONE
            notesListAdapter.updateNotes(notes.sortedByDescending {
                it.updateTime
            })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNoteList()
    }

    private fun goToNoteDetails(noteID: Long = 0){
        val action =  NoteListFragmentDirections.actionNoteListFragmentToNoteFragment(noteID)
        Navigation.findNavController(binding.floatingActionButtonAdd).navigate(action)
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }
}