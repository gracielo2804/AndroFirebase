package com.example.androfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androfirebase.databinding.ActivityAddCustomerBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class AddCustomer : AppCompatActivity() {

    lateinit var binding:ActivityAddCustomerBinding
    lateinit var db:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAddCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpanAddCust.setOnClickListener{
            var cekinput=true
            if(binding.etNamaCustomer.text.isNullOrEmpty()||
                binding.etEmailCustomer.text.isNullOrEmpty()||
                binding.etNomorCustomer.text.isNullOrEmpty()||
                binding.etCatatanCustomer.text.isNullOrEmpty()){
                cekinput=false
            }
            if(cekinput){
                val newData=SupCus(
                    binding.etNamaCustomer.text.toString(),
                    binding.spJenisAddCus.selectedItem.toString(),
                    binding.etNomorCustomer.text.toString(),
                    binding.etEmailCustomer.text.toString(),
                    binding.etCatatanCustomer.text.toString()
                )
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                db= FirebaseDatabase.getInstance(resources.getString(R.string.firebaseurl)).getReference()
                db.child("customer").child(timeStamp).child("nama").setValue(binding.etNamaCustomer.text.toString())
                db.child("customer").child(timeStamp).child("jenis").setValue(binding.spJenisAddCus.selectedItem.toString())
                db.child("customer").child(timeStamp).child("nomor").setValue(binding.etNomorCustomer.text.toString())
                db.child("customer").child(timeStamp).child("email").setValue(binding.etEmailCustomer.text.toString())
                db.child("customer").child(timeStamp).child("catatan").setValue(binding.etCatatanCustomer.text.toString())

                Toast.makeText(this, "Berhasil Add Customer", Toast.LENGTH_SHORT).show()
                binding.etNamaCustomer.text?.clear()
                binding.etNomorCustomer.text?.clear()
                binding.etEmailCustomer.text?.clear()
                binding.etCatatanCustomer.text?.clear()
                binding.spJenisAddCus.setSelection(0)
            }
            else{
                Toast.makeText(this, "Harap Isi Semua Inputan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        val i = Intent(this,ListCustomer::class.java)
        startActivity(i)
        finish()
    }
}