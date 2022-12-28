package com.samsung.sam_day9_service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.permissionx.guolindev.PermissionX
import com.samsung.sam_day9_service.services.DownloadService
import com.samsung.sam_day9_service.services.MyService

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button=findViewById<Button>(R.id.button)
        val button2=findViewById<Button>(R.id.button2)
         textView=findViewById(R.id.textView)


        button.setOnClickListener {

            startService(Intent(this,DownloadService::class.java))
        }


        button2.setOnClickListener {

//            PermissionX.init(this)
//                .permissions(Manifest.permission.POST_NOTIFICATIONS)
//                .request { allGranted, grantedList, deniedList ->
//                    // handling the logic
//                    if (allGranted){
//
//                    }
//
//                }


            createNotificationChannel()
            var builder = NotificationCompat.Builder(this, "1001")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setProgress(100,0,true)





            Thread{

                for (i in 0..100 ){


                    Thread.sleep(200)
                    with(NotificationManagerCompat.from(this)) {
                        // notificationId is a unique int for each notification that you must define

                        builder.setProgress(100,i,false)
                        notify(2222, builder.build())
                    }
                }

            }.start()
        }
    }




    inner class DownloadProgressReceiver: BroadcastReceiver(){

        override fun onReceive(p0: Context?, i: Intent?) {

          var progress= i?.getIntExtra("progress",-1)

            textView.setText("$progress%")

        }


    }

    private val reciver=DownloadProgressReceiver()

    override fun onStart() {
        super.onStart()

        registerReceiver(reciver, IntentFilter(DownloadService.INTENT_DOWNLOAD_NEW_VERSION))
    }


    override fun onStop() {
        super.onStop()

        unregisterReceiver(reciver)
    }



    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          //  val name = getString("download_Channel_name")
           // val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1001", "download_Channel_name", importance).apply {
                description = "Channel Descriotion"
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}