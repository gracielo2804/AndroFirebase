package com.example.androfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androfirebase.databinding.ActivityAddSupplierBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class AddSupplier : AppCompatActivity() {

    lateinit var binding:ActivityAddSupplierBinding
    private lateinit var db:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAddSupplierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpanAddSup.setOnClickListener{
            var cekinput=true
            if(binding.etNamaSupplier.text.isNullOrEmpty()||
                binding.etEmailSupplier.text.isNullOrEmpty()||
                binding.etNomorSupplier.text.isNullOrEmpty()||
                binding.etCatatanSupplier.text.isNullOrEmpty()){
                cekinput=false
            }
            if(cekinput){
                val newData=SupCus(
                    binding.etNamaSupplier.text.toString(),
                    binding.spJenisAddCus.selectedItem.toString(),
                    binding.etNomorSupplier.text.toString(),
                    binding.etEmailSupplier.text.toString(),
                    binding.etCatatanSupplier.text.toString()
                )
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                db= FirebaseDatabase.getInstance(resources.getString(R.string.firebaseurl)).getReference()
                db.child("supplier").child(timeStamp).child("nama").setValue(binding.etNamaSupplier.text.toString())
                db.child("supplier").child(timeStamp).child("jenis").setValue(binding.spJenisAddCus.selectedItem.toString())
                db.child("supplier").child(timeStamp).child("nomor").setValue(binding.etNomorSupplier.text.toString())
                db.child("supplier").child(timeStamp).child("email").setValue(binding.etEmailSupplier.text.toString())
                db.child("supplier").child(timeStamp).child("catatan").setValue(binding.etCatatanSupplier.text.toString())

                Toast.makeText(this, "Berhasil Add Supplier", Toast.LENGTH_SHORT).show()
                binding.etNamaSupplier.text?.clear()
                binding.etNomorSupplier.text?.clear()
                binding.etEmailSupplier.text?.clear()
                binding.etCatatanSupplier.text?.clear()
                binding.spJenisAddCus.setSelection(0)
            }
            else{
                Toast.makeText(this, "Harap Isi Semua Inputan", Toast.LENGTH_SHORT).show()
            }
        }
        
    }

    override fun onBackPressed() {
        val i = Intent(this,ListSupplier::class.java)
        startActivity(i)
        finish()
    }


}