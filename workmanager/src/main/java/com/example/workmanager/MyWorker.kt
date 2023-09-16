package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay
import kotlin.random.Random

class MyWorker(context: Context,workerParameters: WorkerParameters):CoroutineWorker(context,workerParameters) {
    override suspend fun doWork():Result {
        val randomNumber = Random.nextInt(100)
        inputData.getString("image")
        Log.d("TAG","doWork: ${inputData.getString("image")}")
        Log.d("TAG","doWork: ${Thread.currentThread().name}")
        Log.d("TAG","doWork: $randomNumber")
        repeat(100){
            setProgress(workDataOf("progress" to it+1))
            delay(50)
        }
        return if(randomNumber%2==0)
        Result.success(workDataOf("output" to "Photo download successfully"))
        else Result.failure()
    }

}