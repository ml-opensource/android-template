package com.monstarlab.features.main

import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.chuckerteam.chucker.api.Chucker
import com.monstarlab.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val notificationRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setupNStack()
        checkNotificationPermission()
    }

    private fun checkNotificationPermission() {
        // If the app send notifications other than Chucker's, remove the `isOp` check and move
        // the request to the appropriate screen in the app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && Chucker.isOp) {
            notificationRequest.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

}
