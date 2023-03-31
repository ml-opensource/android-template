package com.monstarlab.features.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import dk.nodes.nstack.kotlin.NStack
import dk.nodes.nstack.kotlin.models.AppOpenData
import dk.nodes.nstack.kotlin.models.AppUpdate
import dk.nodes.nstack.kotlin.models.AppUpdateState
import dk.nodes.nstack.kotlin.models.Message
import dk.nodes.nstack.kotlin.models.RateReminder
import dk.nodes.nstack.kotlin.models.state
import dk.nodes.nstack.kotlin.models.update

fun MainActivity.handleNstackData(data: AppOpenData?, onDismissed: () -> Unit) {
    val updateData = data?.update
    if (updateData != null) {
        when (updateData.state) {
            AppUpdateState.NONE -> Unit // Nothing to do
            AppUpdateState.UPDATE -> showUpdateDialog(updateData, onDismissed)
            AppUpdateState.FORCE -> showForceDialog(updateData)
            AppUpdateState.CHANGELOG -> showChangelogDialog(updateData, onDismissed)
        }
    }
    data?.message?.let { showMessageDialog(it, onDismissed) }
    data?.rateReminder?.let { showRateReminderDialog(it, onDismissed) }
}

fun MainActivity.showRateReminderDialog(rateReminder: RateReminder, onDismissed: () -> Unit) {
    AlertDialog.Builder(this)
        .setMessage(rateReminder.body)
        .setTitle(rateReminder.title)
        .setCancelable(false)
        .setPositiveButton(rateReminder.yesButton) { dialog, _ ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(rateReminder.link)))
            dialog.dismiss()
        }
        .setNegativeButton(rateReminder.noButton) { dialog, _ ->
            dialog.dismiss()
        }
        .setNeutralButton(rateReminder.laterButton) { dialog, _ ->
            dialog.dismiss()
        }
        .setOnDismissListener { onDismissed() }
        .show()
}

fun MainActivity.showMessageDialog(message: Message, onDismissed: () -> Unit) {
    AlertDialog.Builder(this)
        .setMessage(message.message)
        .setCancelable(false)
        .setPositiveButton("Translation.defaultSection.ok") { dialog, _ ->
            NStack.messageSeen(message)
            dialog.dismiss()
        }
        .setOnDismissListener { onDismissed() }
        .show()
}

fun MainActivity.showUpdateDialog(appUpdate: AppUpdate, onDismissed: () -> Unit) {
    AlertDialog.Builder(this)
        .setTitle(appUpdate.update?.translate?.title ?: return)
        .setMessage(appUpdate.update?.translate?.message ?: return)
        .setPositiveButton(appUpdate.update?.translate?.positiveButton) { dialog, _ ->
            dialog.dismiss()
        }
        .setOnDismissListener { onDismissed() }
        .show()
}

fun MainActivity.showChangelogDialog(appUpdate: AppUpdate, onDismissed: () -> Unit) {
    AlertDialog.Builder(this)
        .setTitle(appUpdate.update?.translate?.title ?: "")
        .setMessage(appUpdate.update?.translate?.message ?: "")
        .setNegativeButton(appUpdate.update?.translate?.negativeButton ?: "") { dialog, _ ->
            dialog.dismiss()
        }
        .setOnDismissListener { onDismissed() }
        .show()
}

fun MainActivity.startPlayStore() {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$packageName")
            )
        )
    } catch (anfe: android.content.ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            )
        )
    }
}

fun MainActivity.showForceDialog(appUpdate: AppUpdate) {
    val dialog = AlertDialog.Builder(this)
        .setTitle(appUpdate.update?.translate?.title ?: return)
        .setMessage(appUpdate.update?.translate?.message ?: return)
        .setCancelable(false)
        .setPositiveButton(appUpdate.update?.translate?.positiveButton, null)
        .create()

    dialog.setOnShowListener {
        val b = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        b.setOnClickListener {
            startPlayStore()
        }
    }

    dialog.show()
}
