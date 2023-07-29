package com.example.firebaseapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaseapp.databinding.ActivityStorageBinding
import com.google.firebase.storage.FirebaseStorage

class StorageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStorageBinding
    var imageUri: Uri? = null
    private lateinit var storage:FirebaseStorage
    val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        storage = FirebaseStorage.getInstance()

        binding.selectImageBtn.setOnClickListener {
            val intent = Intent()
            intent.type = "image/"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent,101)
        }
        binding.uploadImageBtn.setOnClickListener {
            imageUri?.apply {
                storage
                    .getReference("images/dog")
                    .putFile(this)
                    .addOnSuccessListener {
                        Log.d(TAG,"successfully added: ${it.storage}")
                    }
                    .addOnFailureListener{
                        Log.d(TAG,"error $it")
                    }
            }


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK && data != null){
            imageUri = data.data
            binding.imageView.setImageURI(imageUri)

        }
    }
}