package com.example.contactapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.contactapp.utlis.DatabaseHelper

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var etName: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var userList: MutableList<String>
    private var selectedName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        etName = findViewById(R.id.et_name)
        btnAdd = findViewById(R.id.btn_add)
        btnUpdate = findViewById(R.id.btn_update)
        btnDelete = findViewById(R.id.btn_delete)
        listView = findViewById(R.id.list_view)

        loadUsers()

        listView.setOnItemClickListener { _, _, position, _ ->
            selectedName = userList[position]
            etName.setText(selectedName)
        }

        btnAdd.setOnClickListener {
            val name = etName.text.toString().trim()
            if (name.isNotEmpty()) {
                dbHelper.insertUser(name)
                loadUsers()
                etName.text.clear()
            }
        }

        btnUpdate.setOnClickListener {
            val newName = etName.text.toString().trim()
            if (selectedName != null && newName.isNotEmpty()) {
                dbHelper.updateUser(selectedName!!, newName)
                loadUsers()
                etName.text.clear()
                selectedName = null
            }
        }

        btnDelete.setOnClickListener {
            if (selectedName != null) {
                dbHelper.deleteUser(selectedName!!)
                loadUsers()
                etName.text.clear()
                selectedName = null
            }
        }
    }

    private fun loadUsers() {
        userList = dbHelper.getAllUsers().toMutableList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList)
        listView.adapter = adapter
    }
}
