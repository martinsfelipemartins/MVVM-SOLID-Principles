package com.example.mvvmcleansolid.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.core.data.Note
import com.example.mvvmcleansolid.databinding.FragmentNoteBinding
import com.example.mvvmcleansolid.framework.NoteViewModel

class NoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private var currentNote =
        Note(title = "", content = "", creationTime = 0L, updateTime = 0L)

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
        viewModel = ViewModelProviders.of(this)[NoteViewModel::class.java]
        addNote()
        observerSetup()
    }

    private fun addNote() {
        binding.floatingActionButtonCheck.setOnClickListener {
            if (binding.editTextContent.text.toString() != "" || binding.editTextTitle.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote = Note(
                    title = binding.editTextTitle.toString(),
                    content = binding.editTextContent.toString(),
                    creationTime = time,
                    updateTime = time
                )
                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }
    }

    private fun observerSetup() {
        viewModel.saved.observe(viewLifecycleOwner) {
            if (it) {
                hideKeyboard()
                Toast.makeText(context, "Nota adicionada", Toast.LENGTH_LONG).show()
                Navigation.findNavController(binding.editTextContent).popBackStack()
            } else {
                Toast.makeText(context, "Algo deu errado. Tente Novamente.", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editTextTitle.windowToken, 0)
    }


}