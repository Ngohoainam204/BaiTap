package com.example.callblocker

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BlockedNumbersAdapter
    private lateinit var edtPhoneNumber: EditText
    private lateinit var btnAddNumber: Button
    private val sharedPreferences by lazy { getSharedPreferences("BlockedNumbers", Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtPhoneNumber = findViewById(R.id.edtPhoneNumber)
        btnAddNumber = findViewById(R.id.btnAddNumber)
        recyclerView = findViewById(R.id.recyclerView)

        // Lấy danh sách số bị chặn
        val blockedNumbers = getBlockedNumbers().toMutableList()
        adapter = BlockedNumbersAdapter(blockedNumbers) { phoneNumber ->
            removeBlockedNumber(phoneNumber)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnAddNumber.setOnClickListener {
            val phoneNumber = edtPhoneNumber.text.toString().trim()
            if (phoneNumber.isNotEmpty()) {
                addBlockedNumber(phoneNumber)
                edtPhoneNumber.text.clear()
            } else {
                Toast.makeText(this, "Vui lòng nhập số điện thoại!", Toast.LENGTH_SHORT).show()
            }
        }

        requestPermissions()
    }

    private fun getBlockedNumbers(): List<String> {
        return sharedPreferences.getStringSet("blocked_list", setOf())?.toList() ?: emptyList()
    }

    private fun addBlockedNumber(phoneNumber: String) {
        val blockedList = getBlockedNumbers().toMutableSet()
        blockedList.add(phoneNumber)
        sharedPreferences.edit().putStringSet("blocked_list", blockedList).apply()
        adapter.updateList(blockedList.toList())
        Toast.makeText(this, "Đã thêm số $phoneNumber vào danh sách chặn", Toast.LENGTH_SHORT).show()
    }

    private fun removeBlockedNumber(phoneNumber: String) {
        val blockedList = getBlockedNumbers().toMutableSet()
        blockedList.remove(phoneNumber)
        sharedPreferences.edit().putStringSet("blocked_list", blockedList).apply()
        adapter.updateList(blockedList.toList())
        Toast.makeText(this, "Đã xóa số $phoneNumber khỏi danh sách chặn", Toast.LENGTH_SHORT).show()
    }

    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ANSWER_PHONE_CALLS
        )
        requestPermissions.launch(permissions)
    }

    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                if (!it.value) {
                    Toast.makeText(this, "Quyền $it.key bị từ chối!", Toast.LENGTH_SHORT).show()
                }
            }
        }
}
