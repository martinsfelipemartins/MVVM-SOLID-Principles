package com.example.mvvmcleansolid.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mvvmcleansolid.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {

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
        binding.floatingActionButtonAdd.setOnClickListener {
            goToNoteDetails()
        }
    }

    private fun goToNoteDetails(noteID: Long = 0){
        val action =  NoteListFragmentDirections.actionNoteListFragmentToNoteFragment(noteID)
        Navigation.findNavController(binding.floatingActionButtonAdd).navigate(action)
    }
}