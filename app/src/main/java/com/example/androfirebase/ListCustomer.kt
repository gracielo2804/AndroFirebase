package com.example.androfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androfirebase.databinding.ActivityListCustomer2Binding
import com.example.androfirebase.databinding.ActivityListSupplierBinding

class ListCustomer : AppCompatActivity() {

    lateinit var binding:ActivityListCustomer2Binding

    private lateinit var adapters:ListSupCusAdapter
    private var listDataCus = arrayListOf<SupCus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_customer2)

        binding= ActivityListCustomer2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddCus.setOnClickListener{
            val i = Intent(this,AddCustomer::class.java)
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