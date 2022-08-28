package com.example.mvvmcleansolid.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mvvmcleansolid.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {

    lateinit var binding: FragmentNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBack()
    }

    private fun goBack() {
        binding.floatingActionButtonCheck.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }
}