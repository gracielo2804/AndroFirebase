package com.example.androfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androfirebase.databinding.ActivityListSupplierBinding
import com.example.androfirebase.databinding.ActivityMainBinding

class ListSupplier : AppCompatActivity() {

    lateinit var binding: ActivityListSupplierBinding

    private lateinit var adapters:ListSupCusAdapter
    private var listDataCus = arrayListOf<SupCus>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityListSupplierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddSup.setOnClickListener{
            val i = Intent(this,AddSupplier::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onBackPressed() {
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }
}