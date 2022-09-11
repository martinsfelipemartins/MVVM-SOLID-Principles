package com.example.mvvmcleansolid.presentation

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.core.data.Note
import com.example.mvvmcleansolid.R
import com.example.mvvmcleansolid.databinding.FragmentNoteBinding
import com.example.mvvmcleansolid.framework.NoteViewModel

class NoteFragment : Fragment(), MenuProvider {

    private var idNote = 0L
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

    private fun alertSetup() {
        if (context != null && idNote != 0L) {
            AlertDialog.Builder(context).setTitle("Delete Note")
                .setMessage("Are you sure you want delete this note?")
                .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                    viewModel.deleteNote(currentNote)
                }
                .setNegativeButton("No") { _: DialogInterface, _: Int -> }
                .create()
                .show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)


        viewModel = ViewModelProviders.of(this)[NoteViewModel::class.java]
        addNote()
        observerSetup()
        arguments?.let {
            idNote = NoteFragmentArgs.fromBundle(it).noteId
        }

        if (idNote != 0L) {
            viewModel.getNote(idNote)
        }
    }

    private fun addNote() {
        binding.floatingActionButtonCheck.setOnClickListener {
            if (binding.editTextContent.text.toString() != "" || binding.editTextTitle.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote = Note(
                    title = binding.editTextTitle.text.toString(),
                    content = binding.editTextContent.text.toString(),
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
        viewModel.currentNote.observe(viewLifecycleOwner) { note ->
            note?.let {
                currentNote = it
                binding.editTextTitle.setText(it.title, TextView.BufferType.EDITABLE)
                binding.editTextContent.setText(it.content, TextView.BufferType.EDITABLE)
            }
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editTextTitle.windowToken, 0)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.note_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.noteDelete -> alertSetup()
        }
        return true
    }
}