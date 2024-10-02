package com.example.bai1

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserInfoAdapter
    private val userList = mutableListOf<UserInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fullNameEditText = findViewById<EditText>(R.id.fullNameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        val termsCheckBox = findViewById<CheckBox>(R.id.termsCheckBox)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val recyclerView = findViewById<RecyclerView>(R.id.userInfoRecyclerView)

        // RecyclerView setup
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserInfoAdapter(userList)
        recyclerView.adapter = adapter

        saveButton.setOnClickListener {
            if (termsCheckBox.isChecked) {
                val fullName = fullNameEditText.text.toString()
                val email = emailEditText.text.toString()
                val phone = phoneEditText.text.toString()

                // Get selected gender
                val gender = when (genderRadioGroup.checkedRadioButtonId) {
                    R.id.maleRadioButton -> "Nam"
                    R.id.femaleRadioButton -> "Nữ"
                    R.id.otherRadioButton -> "Khác"
                    else -> "Không xác định"
                }

                // Add user info to the list
                val userInfo = UserInfo(fullName, email, phone, gender)
                userList.add(userInfo)

                // Notify adapter of the change
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Vui lòng đồng ý với điều khoản sử dụng", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
