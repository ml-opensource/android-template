package com.monstarlab.features.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.chuckerteam.chucker.api.Chucker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()
    private val notificationRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { viewModel.stateFlow.value.showSplash }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        checkNotificationPermission()
        observeState()
        setContent {
            MainNavHost()
        }
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
                handleNstackData(it.nstackData, viewModel::onNstackDataConsumed)
            }
        }
    }

}
