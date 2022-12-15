package com.example.androfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnToCus.setOnClickListener {
            val i = Intent(this,ListCustomer::class.java)
            startActivity(i)
            finish()
        }
        binding.btnToSup.setOnClickListener {
            val i = Intent(this,ListSupplier::class.java)
            startActivity(i)
            finish()
        }
    }
}