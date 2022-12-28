package com.samsung.sam_day9_service.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class DownloadService : Service() {


    companion object{

        val INTENT_DOWNLOAD_NEW_VERSION="INTENT_DOWNLOAD_MAIN_NEW_VERSION"

        fun stop(){

        }

    }
    override fun onCreate() {
        super.onCreate()



    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread{
            download()
        }.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }


    fun download(){

        for (i in 0..100){
            println(i)
            Thread.sleep(1000)

            val intent=Intent(DownloadService.INTENT_DOWNLOAD_NEW_VERSION)
            intent.putExtra("progress",i)

            sendBroadcast(intent)
        }
    }



}