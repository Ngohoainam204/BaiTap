package com.example.callblocker

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log

@Suppress("DEPRECATION")
class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

            if (state == TelephonyManager.EXTRA_STATE_RINGING && incomingNumber != null) {
                Log.d("CallReceiver", "Cuộc gọi đến từ: $incomingNumber")

                if (isBlockedNumber(context, incomingNumber)) {
                    Log.d("CallReceiver", "Số bị chặn! Đang từ chối cuộc gọi...")
                    rejectCall(context)
                }
            }
        }
    }

    private fun isBlockedNumber(context: Context?, phoneNumber: String): Boolean {
        val blockedNumbers = listOf("+84901234567", "+84123456789") // Danh sách số bị chặn
        return blockedNumbers.contains(phoneNumber)
    }

    @SuppressLint("MissingPermission")
    private fun rejectCall(context: Context?) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            val telecomManager = context?.getSystemService(Context.TELECOM_SERVICE) as android.telecom.TelecomManager
            telecomManager.endCall()
        }
    }
}
