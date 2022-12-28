package com.samsung.sam_day9_service.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.Date

class MyService : Service() {


    override fun onCreate() {
        super.onCreate()

      Thread{
          while (true) {
              println(Date())
              Thread.sleep(1000)
          }
      }.start()
    }


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}