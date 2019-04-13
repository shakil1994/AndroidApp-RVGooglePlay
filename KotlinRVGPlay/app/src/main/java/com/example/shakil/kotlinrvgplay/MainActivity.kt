package com.example.shakil.kotlinrvgplay

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.shakil.kotlinrvgplay.Adapter.MyGroupAdapter
import com.google.firebase.database.DatabaseReference
import com.example.shakil.kotlinrvgplay.Interface.IFirebaseloadListener
import com.example.shakil.kotlinrvgplay.Model.ItemGroup
import dmax.dialog.SpotsDialog
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.DatabaseError
import com.example.shakil.kotlinrvgplay.Model.ItemData
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity(), IFirebaseloadListener {

    lateinit var dialog: AlertDialog
    lateinit var iFirebaseloadListener: IFirebaseloadListener

    lateinit var myData: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init
        myData = FirebaseDatabase.getInstance().getReference("MyData")
        dialog = SpotsDialog.Builder().setContext(this).build()
        iFirebaseloadListener = this

        my_recycler_view.setHasFixedSize(true)
        my_recycler_view.layoutManager = LinearLayoutManager(this)

        //Load data from firebase
        getFirebaseData()
    }

    private fun getFirebaseData() {
        dialog.show()
        myData.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                iFirebaseloadListener.onFirebaseLoadFailed(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                val itemGroups = ArrayList<ItemGroup>()
                for (myDataSnapShot in p0.children) {
                    val itemGroup = ItemGroup()
                    itemGroup.headerTitle = myDataSnapShot.child("headerTitle").getValue(true)!!.toString()
                    val genericTypeIndicator = object : GenericTypeIndicator<ArrayList<ItemData>>() {}
                    itemGroup.listItem = myDataSnapShot.child("listItem").getValue(genericTypeIndicator)
                    itemGroups.add(itemGroup)
                }
                iFirebaseloadListener.onFirebaseLoadSuccess(itemGroups)
            }
        })
    }

    override fun onFirebaseLoadSuccess(itemGroupList: List<ItemGroup>) {
        val adapter = MyGroupAdapter(this@MainActivity, itemGroupList)
        my_recycler_view.adapter = adapter
        dialog.dismiss()
    }

    override fun onFirebaseLoadFailed(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }
}
