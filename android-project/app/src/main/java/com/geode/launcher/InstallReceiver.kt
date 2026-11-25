package com.geode.launcher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class InstallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val status = intent.getIntExtra("status", -1)

        when (status) {
            -1 -> {
                // Pending user action
            }
            1 -> { // STATUS_SUCCESS
                Toast.makeText(context, "Installation complete", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val msg = intent.getStringExtra("message")
                Toast.makeText(context, "Installation failed: $msg", Toast.LENGTH_SHORT).show()
            }
        }
    }
}