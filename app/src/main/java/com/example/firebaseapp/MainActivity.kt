package com.example.firebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaseapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth:FirebaseAuth
    private val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.registrationBtn.setOnClickListener {
            auth
                .createUserWithEmailAndPassword("codewithkholikov@gmail.com","oybek1234")
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        Log.d(TAG,"onCreate: Successfully user created")
                    }else{
                        Log.d(TAG,"onCreate: Error while creating user")
                    }
                }
        }
        binding.loginBtn.setOnClickListener {
            auth.signInWithEmailAndPassword("codewithkholikov@gmail.com","oybek1234")
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Log.d(TAG,"onCreate: User Login successfully")
                    }else{
                        Log.d(TAG,"onCreate: User Login error")
                    }
                }
        }
        binding.signOutBtn.setOnClickListener {
            auth.signOut()
        }
    }
}