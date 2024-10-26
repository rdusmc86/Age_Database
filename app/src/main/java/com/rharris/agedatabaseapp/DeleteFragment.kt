package com.rharris.agedatabaseapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class DeleteFragment : Fragment(R.layout.content_delete) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(
            R.layout.content_delete,
            container,
            false)

        // Database and UI code goes here in next chapter
        val dm = DataManager(requireActivity())

        val btnDelete: Button = view.findViewById(R.id.btnDelete)
        val editDelete: EditText = view.findViewById(R.id.editDelete)

        btnDelete.setOnClickListener(
            {
                dm.delete(editDelete.text.toString())
            }
        )

        return view
    }
}