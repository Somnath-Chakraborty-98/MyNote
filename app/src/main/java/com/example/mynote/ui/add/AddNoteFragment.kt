package com.example.mynote.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mynote.R
import com.example.mynote.model.User
import com.example.mynote.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_add_note.view.*


class AddNoteFragment : Fragment() {

    lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.save_note.setOnClickListener {
            insertDataToDatabase()
        }
        return view

    }
    private fun insertDataToDatabase(){
        val title = editTextTitle.text.toString()
        val note = editTextNote.text.toString()

        if(inputCheck(title, note)){
            val user = User(0,title, note)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Succesfully added!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.actionSaveNote)
        }else{
            Toast.makeText(requireContext(),"Please fill out all Fields!",Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(title: String,note: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))
    }

}