package com.example.piyushchhabra.androidlifecycle

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.os.Build
import android.app.*
import android.app.Notification.*
import android.support.v4.app.NotificationCompat


open class TracerActivity : AppCompatActivity() {

    private var notificationManager: NotificationManager? = null

    companion object {

        const val CHANNEL_ID = "com.example.piyushchhabra.androidlifecycle"
        const val CHANNEL_NAME = "Android Lifecycle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notify("onCreate")
    }

    override fun onPause() {
        super.onPause()
        notify("onPause")
    }

    override fun onResume() {
        super.onResume()
        notify("onResume")
    }

    override fun onStop() {
        super.onStop()
        notify("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        notify("onDestroy")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        notify("onRestoreInstanceState")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        notify("onSaveInstanceState")
    }

    private fun notify(methodName: String) {

        val name = this::class.simpleName
        val strings = name?.split("\\.".toRegex())?.dropLastWhile({ it.isEmpty() })?.toTypedArray()
           var notificationBuilder: Notification? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
            //notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
             //notificationBuilder = Builder(this, CHANNEL_ID)

            notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(methodName + " " + strings!![strings!!.size - 1])
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(name).build()
        }
// else {
//            notificationBuilder
////            notificationBuilder = NotificationCompat.Builder(this)
////            notificationBuilder = Notification.Builder(this)
//        }
//
//        val notification = notificationBuilder
//            .setContentTitle(methodName + " " + strings[strings?.size - 1])
//            .setAutoCancel(true)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setContentText(name).build()



        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder)
    }


    private fun createChannel() {
        val mNotificationManager = getSystemService(NotificationManager::class.java)
        // The id of the channel.
        val id = CHANNEL_ID
        // The user-visible name of the channel.
        val name = "Activity livecycle tracer"
        // The user-visible description of the channel.
        val description = "Allows to trace the activity lifecycle"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(id, name, importance)
        // Configure the notification channel.
        mChannel.setDescription(description)

        mNotificationManager.createNotificationChannel(mChannel)
    }

}