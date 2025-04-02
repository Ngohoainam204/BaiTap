package com.example.sqlite

import DatabaseHelper
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val edtId = findViewById<EditText>(R.id.edtId)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        // Xử lý nút Thêm
        btnAdd.setOnClickListener {
            val id = edtId.text.toString().toIntOrNull()  // Lấy ID từ EditText
            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()

            if (id != null && name.isNotEmpty() && phone.isNotEmpty()) {
                val success = databaseHelper.addContact(id, name, phone)
                if (success) {
                    Toast.makeText(this, "Thêm thành công! ID: $id", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Lỗi: ID đã tồn tại!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ ID, tên và số điện thoại!", Toast.LENGTH_SHORT).show()
            }
        }


        // Xử lý nút Sửa
        btnUpdate.setOnClickListener {
            val id = edtId.text.toString().toIntOrNull()
            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()
            if (id != null && name.isNotEmpty() && phone.isNotEmpty()) {
                databaseHelper.updateContact(id, name, phone)
                Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }

        // Xử lý nút Xóa
        btnDelete.setOnClickListener {
            val id = edtId.text.toString().toIntOrNull()
            if (id != null) {
                databaseHelper.deleteContact(id)

            } else {
                Toast.makeText(this, "Nhập ID hợp lệ", Toast.LENGTH_SHORT).show()
            }
        }

        // Xử lý nút Hiển thị
        btnShow.setOnClickListener {
            val contacts = databaseHelper.getAllContacts()
            txtResult.text = if (contacts.isNotEmpty()) contacts.joinToString("\n") else "Không có dữ liệu"
        }
    }
}
