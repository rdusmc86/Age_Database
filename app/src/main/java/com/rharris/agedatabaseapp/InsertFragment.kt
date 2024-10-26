package com.rharris.agedatabaseapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class InsertFragment : Fragment(R.layout.content_insert) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate layout for fragment
        val view = inflater.inflate(R.layout.content_insert, container, false)

        // initialize DataManager
        val dm = DataManager(requireActivity())

        // find views
        val btnInsert: Button = view.findViewById(R.id.btnInsert)
        val editName: EditText = view.findViewById(R.id.editName)
        val editAge: EditText = view.findViewById(R.id.editAge)

        // set onClickListener for button
        btnInsert.setOnClickListener {
            dm.insert(editName.text.toString(), editAge.text.toString())
        }

        // return inflated view
        return view
    }
}