package com.example.mynote.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mynote.R
import com.example.mynote.model.User
import com.example.mynote.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel : UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.UpdateTitle.setText(args.currentUser.title)
        view.UpdateNote.setText(args.currentUser.note)

        view.update_note.setOnClickListener {
        updateItem()
        }

        setHasOptionsMenu(true)
        return view
    }
    private fun updateItem(){
        val title = UpdateTitle.text.toString()
        val note = UpdateNote.text.toString()

        if(inputCheck(title, note)){
            val updatedUser = User(args.currentUser.id, title, note)
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }
        else
            Toast.makeText(requireContext(), "Please fill out all fields",Toast.LENGTH_LONG).show()
    }
    private fun inputCheck(title: String,note: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
       val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Removed Successfully",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Delete ${args.currentUser.title}")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.title} ?")
        builder.create().show()
    }
}