package com.example.locationsharingapp_dipti_ict_amad_l4_04_14.viewdipti14

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.locationsharingapp_dipti_ict_amad_l4_04_14.MainActivity
import com.example.locationsharingapp_dipti_ict_amad_l4_04_14.databinding.ActivityLoginDipti14Binding
import com.example.locationsharingapp_dipti_ict_amad_l4_04_14.viewmodeldipti14.AuthenticationViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginActivityDipti14 : AppCompatActivity() {
    private  lateinit var  binding: ActivityLoginDipti14Binding
    private  lateinit var  authenticationViewModel: AuthenticationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=  ActivityLoginDipti14Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        authenticationViewModel = ViewModelProvider(this).get(AuthenticationViewModel::class.java)


        binding.loginTxt.setOnClickListener{
            val  email = binding.emailEt.text.toString()
            val  password = binding.passwordEt.text.toString()

            if(email.isEmpty()||password.isEmpty()) {
                Toast.makeText(this, "please fill al,l fields", Toast.LENGTH_SHORT).show()
            }
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher((email)).matches ()){
                Toast.makeText(this,"please enter valid email", Toast.LENGTH_SHORT).show()
            }
            else if(password.length<6){
                Toast.makeText(this,"password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            }
            else{
                authenticationViewModel.login(email,password, {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                },{
                    Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
                })
            }

        }
        binding.registerBtn.setOnClickListener{
            startActivity(Intent(this, RegisterActivityDipti14::class.java))
            finish()
        }

    }
    override fun onStart() {
        super.onStart()
        if(Firebase.auth.currentUser!=null){
            startActivity(Intent(this@LoginActivityDipti14, MainActivity::class.java))
            finish()
        }
    }

}