package com.example.firebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaseapp.databinding.ActivityFireStoreBinding
import com.example.firebaseapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var messageRef:CollectionReference
    val TAG = "TAG"

    private lateinit var binding: ActivityFireStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = FirebaseFirestore.getInstance()
        messageRef =firestore.collection("message")
        messageRef.get().addOnSuccessListener {messageList->
            messageList.forEach{
                Log.d(TAG,"messages: ${it.id}=>${it.data}")
            }
        }
        binding.addmessages.setOnClickListener {
            val messageData = mapOf(
                "message" to "Va aleykum Assalom",
                "created" to (System.currentTimeMillis())
            )
            messageRef.add(messageData)
                .addOnSuccessListener{
                    Log.d(TAG,"message inserted ${it.id}")
                }.addOnCanceledListener {
                    Log.d(TAG,"message error")
                }
        }
    }
}