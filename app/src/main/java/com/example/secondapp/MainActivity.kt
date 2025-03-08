package com.example.secondapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    // Define categories
    private val categories = listOf("Select Category", "Flowers", "Pets", "Buildings")

    // Define image resources for each category directly
    private val flowerImages = listOf(
        R.drawable.flower1, R.drawable.flower2, R.drawable.flower3,
        R.drawable.flower4, R.drawable.flower5, R.drawable.flower6
    )

    private val petImages = listOf(
        R.drawable.pet1, R.drawable.pet2, R.drawable.pet3,
        R.drawable.pet4, R.drawable.pet5, R.drawable.pet6
    )

    private val buildingImages = listOf(
        R.drawable.building1, R.drawable.building2, R.drawable.building3,
        R.drawable.building4, R.drawable.building5, R.drawable.building6
    )

    private lateinit var gridView: GridView
    private lateinit var categorySpinner: Spinner
    private lateinit var emptyStateText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        gridView = findViewById(R.id.gridView)
        categorySpinner = findViewById(R.id.categorySpinner)
        emptyStateText = findViewById(R.id.emptyStateText)

        // Set up the category spinner
        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categories
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = spinnerAdapter

        // Handle spinner item selection
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> { // "Select Category" - show empty grid
                        emptyStateText.visibility = View.VISIBLE
                        gridView.adapter = ImageAdapter(this@MainActivity, emptyList())
                    }
                    1 -> { // "Flowers"
                        emptyStateText.visibility = View.GONE
                        gridView.adapter = ImageAdapter(this@MainActivity, flowerImages)
                    }
                    2 -> { // "Pets"
                        emptyStateText.visibility = View.GONE
                        gridView.adapter = ImageAdapter(this@MainActivity, petImages)
                    }
                    3 -> { // "Buildings"
                        emptyStateText.visibility = View.GONE
                        gridView.adapter = ImageAdapter(this@MainActivity, buildingImages)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Initialize with empty grid
        emptyStateText.visibility = View.VISIBLE
        gridView.adapter = ImageAdapter(this, emptyList())
    }
}