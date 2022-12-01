package com.kiraninfotech.kiraninfotech

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kiraninfotech.kiraninfotech.Interface.ApiInterface
import com.kiraninfotech.kiraninfotech.Model.NotificationTokenModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val channelID = "Kiran_Infotech_Channel_ID"
const val channelName = "Kiran_Infotech_Channel_Name"


class MyFirebaseMessagingService  : FirebaseMessagingService(){

    override fun onMessageReceived(message: RemoteMessage) {
        generateNotification(message.notification!!.title.toString(), message.notification!!.body.toString())

    }

    override fun onNewToken(token: String) {

        val tokenData = NotificationTokenModel(token)
        ApiInterface.create().addNotifyToken(tokenData).enqueue(object : Callback<NotificationTokenModel>{
            override fun onResponse(
                call: Call<NotificationTokenModel>,
                response: Response<NotificationTokenModel>
            ) {
                if (response.code() == 200){
                    Toast.makeText(applicationContext, "Device Registered for the APP", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NotificationTokenModel>, t: Throwable) {
                Toast.makeText(applicationContext, "Device Unable to Register for the APP", Toast.LENGTH_SHORT).show()
            }

        })

    }


    @SuppressLint("ServiceCast")
    private fun generateNotification(title: String, message : String){

        val intent = Intent(this, MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT)


        val notification: Notification
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager


        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {

            val myChannel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)

            notificationManager.createNotificationChannel(myChannel)

            notification = NotificationCompat.Builder(applicationContext, channelID)
                .setContentText(message)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.app_logo)
                .setChannelId(channelID)
                .setContentIntent(pendingIntent)
                .build()
        } else{
            notification = NotificationCompat.Builder(applicationContext)
                .setContentText(message)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.app_logo)
                .setChannelId(channelID)
                .setContentIntent(pendingIntent)
                .build()
        }

    }

}