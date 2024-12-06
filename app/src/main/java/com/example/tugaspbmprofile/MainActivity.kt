package com.example.tugaspbmprofile

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var ivProfilePicture: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvNpm: TextView
    private lateinit var btnEdit: Button

    // Data Profile
    private var name: String = "ALNO MUJABAR"
    private var email: String = "mujabaralno@gmail.com"
    private var npm: String = "24073122003"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivProfilePicture = findViewById(R.id.ivProfilePicture)
        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        tvNpm = findViewById(R.id.tvNpm)
        btnEdit = findViewById(R.id.btnEdit)

        ivProfilePicture.setOnClickListener {
            Toast.makeText(this, "Feature to change image coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Set initial data
        updateProfileView()

        // Handle edit button click
        btnEdit.setOnClickListener {
            showEditDialog()
        }
    }

    private fun updateProfileView() {
        tvName.text = name
        tvEmail.text = email
        tvNpm.text = npm
    }

    private fun showEditDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_profile, null)
        val inputName = dialogView.findViewById<TextInputEditText>(R.id.etName)
        val inputEmail = dialogView.findViewById<TextInputEditText>(R.id.etEmail)
        val inputNpm = dialogView.findViewById<TextInputEditText>(R.id.etNpm)

        inputName.setText(name)
        inputEmail.setText(email)
        inputNpm.setText(npm)

        AlertDialog.Builder(this)
            .setTitle("Edit Profile")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val newName = inputName.text.toString()
                val newEmail = inputEmail.text.toString()
                val newNpm = inputNpm.text.toString()

                if (newName.isNotBlank() && newEmail.isNotBlank() && newNpm.isNotBlank()) {
                    name = newName
                    email = newEmail
                    npm = newNpm
                    updateProfileView()
                    Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
