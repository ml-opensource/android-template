package com.monstarlab.features.main

import android.os.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.*
import com.chuckerteam.chucker.api.Chucker
import com.monstarlab.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModels<MainActivityViewModel>()
    private val notificationRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setOnExitAnimationListener { viewModel.stateFlow.value.showSplash }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        checkNotificationPermission()
        observeState()
    }

    private fun checkNotificationPermission() {
        // If the app send notifications other than Chucker's, remove the `isOp` check and move
        // the request to the appropriate screen in the app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && Chucker.isOp) {
            notificationRequest.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun observeState() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.stateFlow.collect {
                if (it.nstackData != null) {
                    handleNstackData(it.nstackData)
                    viewModel.onNstackDataConsumed()
                }
            }
        }
    }

}
