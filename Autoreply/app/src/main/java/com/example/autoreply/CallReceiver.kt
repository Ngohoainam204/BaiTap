package com.example.autoreply

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast

class CallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

            // Kiểm tra quyền trước khi lấy số gọi đến
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                try {
                    val clazz = Class.forName(telephonyManager.javaClass.name)
                    val method = clazz.getDeclaredMethod("getLine1Number")
                    val phoneNumber = method.invoke(telephonyManager) as String?

                    if (phoneNumber != null) {
                        Log.d("CallReceiver", "Cuộc gọi đến từ: $phoneNumber")
                        Toast.makeText(context, "Cuộc gọi đến từ: $phoneNumber", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("CallReceiver", "Không thể lấy số gọi đến", e)
                }
            } else if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                // Nếu có số gọi đến, gửi tin nhắn trả lời tự động
                sendAutoReplySms(context)
            }
        }
    }

    private fun sendAutoReplySms(context: Context) {
        try {
            val smsManager = android.telephony.SmsManager.getDefault()
            val message = "Xin lỗi, tôi đang bận. Tôi sẽ gọi lại sau."
            val phoneNumber = "0123456789" // Bạn có thể thay bằng số thử nghiệm

            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(context, "Đã gửi tin nhắn đến $phoneNumber", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Gửi tin nhắn thất bại!", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}
