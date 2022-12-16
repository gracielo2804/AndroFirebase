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
import com.example.androfirebase.databinding.ActivityListCustomer2Binding
import com.example.androfirebase.databinding.ActivityListSupplierBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ListCustomer : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var binding:ActivityListCustomer2Binding
    lateinit var db: DatabaseReference
    lateinit var spinner: Spinner

    var isiSpinner="Semua"

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

        adapters= ListSupCusAdapter()
        binding.rvListCus.apply {
            adapter=adapters
            layoutManager=LinearLayoutManager(this@ListCustomer)
        }
        refreshData()

       spinner= binding.spJenisCus
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
        listDataCus.clear()
        db=FirebaseDatabase.getInstance(resources.getString(R.string.firebaseurl)).getReference()
        db.child("customer").get().addOnSuccessListener {
            for(items in it.children){
                Log.i("isi",items.value.toString())
                Log.i("key",items.key.toString())
                Log.i("isichild",items.child("nama").toString())
                listDataCus.add(SupCus(
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
        Log.e("cek","check")
        val listDataKirim = arrayListOf<SupCus>()
        var isibinding=binding.spJenisCus.selectedItem.toString()
        for (i in listDataCus){
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
