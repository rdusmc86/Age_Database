package com.rharris.agedatabaseapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultsFragment : Fragment(R.layout.content_results) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.content_results,
            container,
            false)

        // Database and UI code goes here in next chapter
        // Create an instance of our DataManager
        val dm = DataManager(requireActivity())

        // Get a reference to the TextView to show the results in
        val textResults: TextView = view.findViewById(R.id.textResults)

        // Create and initialize a Cursor with all the results in
        val c = dm.selectAll()

        // A String to hold all the text
        var list = ""

        // Loop through the results in the Cursor
        while (c.moveToNext()) {
            // Add the results to the String
            // with a little formatting
            list +=
                c.getString(1) + " - " + c.getString(2) + "\n"
        }

        // Display the String in the TextView
        textResults.text = list

        return view
    }

}