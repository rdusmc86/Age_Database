package com.rharris.agedatabaseapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class SearchFragment : Fragment(R.layout.content_search) {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.content_search,
            container,
            false)

        val btnSearch: Button = view.findViewById(R.id.btnSearch)
        val editSearch: EditText = view.findViewById(R.id.editSearch)
        val textResult: TextView = view.findViewById(R.id.textResult)

// This is our DataManager instance
        val dm = DataManager(requireActivity())

        btnSearch.setOnClickListener(
            {
                val c = dm.searchName(editSearch.text.toString())

                // Make sure a result was found
                // before using the Cursor
                if (c.count > 0) {
                    c.moveToNext()
                    textResult.text =
                        "Result = ${c.getString(1)} - ${c.getString(2)}"
                }
            }
        )

        return view
    }
}