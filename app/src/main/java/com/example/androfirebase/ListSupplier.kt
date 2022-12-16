package com.example.androfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androfirebase.databinding.ActivityListSupplierBinding
import com.example.androfirebase.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ListSupplier : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var binding: ActivityListSupplierBinding
    lateinit var db: DatabaseReference
    lateinit var spinner: Spinner

    var isiSpinner="Semua"

    private lateinit var adapters:ListSupCusAdapter
    private var listDataSup = arrayListOf<SupCus>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityListSupplierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddSup.setOnClickListener{
            val i = Intent(this,AddSupplier::class.java)
            startActivity(i)
            finish()
        }
        adapters= ListSupCusAdapter()
        binding.rvListSup.apply {
            adapter=adapters
            layoutManager= LinearLayoutManager(this@ListSupplier)
        }

        refreshData()

        spinner= binding.spJenisSup
        ArrayAdapter.createFromResource(
            this,
            R.array.list_dropdown_all,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener=this

    }

    override fun onBackPressed() {
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }

    fun refreshData(){
        db= FirebaseDatabase.getInstance(resources.getString(R.string.firebaseurl)).getReference()
        db.child("supplier").get().addOnSuccessListener {
            for(items in it.children){
                Log.i("isi",items.value.toString())
                Log.i("key",items.key.toString())
                Log.i("isichild",items.child("nama").toString())
                listDataSup.add(SupCus(
                    items.child("nama").value.toString(),
                    items.child("jenis").value.toString(),
                    items.child("nomor").value.toString(),
                    items.child("email").value.toString(),
                    items.child("catatan").value.toString(),
                ))
            }
            olahData()
        }
    }
    fun olahData(){
        val listDataKirim = arrayListOf<SupCus>()
        var isibinding=binding.spJenisSup.selectedItem.toString()
        for (i in listDataSup){
            if(isibinding=="Semua"){
                listDataKirim.add(i)
            }
            else if(isibinding=="Utama"){
                if(i.jenisBarang=="Utama") listDataKirim.add(i)
            }
            else if(isibinding=="Pelengkap"){
                if(i.jenisBarang=="Pelengkap") listDataKirim.add(i)
            }
            else if(isibinding=="Lainnya"){
                if(i.jenisBarang=="Lainnya") listDataKirim.add(i)
            }
        }
        adapters.refreshData(listDataKirim)
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        isiSpinner=spinner.selectedItem.toString()
        olahData()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}