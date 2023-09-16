package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkInfo.State.*
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.workmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        val request = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraints)
            .setInputData(workDataOf("image" to "imageUrl"))
            .build()

        binding.oneTimeWorkBTn.setOnClickListener {
            WorkManager.getInstance(this)
                .enqueueUniqueWork("my_worker",ExistingWorkPolicy.REPLACE,request)
            WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(request.id)
                .observeForever {workInfo ->
                    Log.d(TAG,"onCreate: ${workInfo.progress.getInt("progress",0)}")
                    if (workInfo.state == SUCCEEDED){
                        val result = workInfo.outputData.getString("output")
                        Toast.makeText(this,result,Toast.LENGTH_SHORT)
                            .show()
                    }else if (workInfo.state == FAILED){
                        Toast.makeText(this,"Work finished failed", Toast.LENGTH_SHORT)
                            .show()

                        //davomiy takrorlanuchi workManager darsi korilmadi
                    }
                }

        }
    }
}