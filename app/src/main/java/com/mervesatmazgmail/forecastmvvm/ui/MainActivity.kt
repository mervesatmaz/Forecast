package com.mervesatmazgmail.forecastmvvm.ui

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.mervesatmazgmail.forecastmvvm.R
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Permission
import java.util.jar.Manifest
private const val REQUEST_CODE= 1
class MainActivity : AppCompatActivity() {


    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
        checkPermission()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)


    }

    override fun onStart() {
        super.onStart()
        Log.d("Activity", "OnStart")


    }

    private fun checkPermission() {
        if(ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.INTERNET
        ) != PackageManager.PERMISSION_GRANTED
                ){
            Log.e("izin", "internet izni yok")
        }
    }

    private fun checkPermission2() {
        (ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_NETWORK_STATE
        ) != PackageManager.PERMISSION_GRANTED
                )
    }
       // if (ContextCompat.checkSelfPermission(
          //      this,
            //    android.Manifest.permission.ACCESS_NETWORK_STATE
          //  ) != PackageManager.PERMISSION_GRANTED
       // )// {
           // Toast.makeText(getApplicationContext(), "İzin Verilmedi yine", Toast.LENGTH_LONG).show()
    // }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE ) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "İnternet ayarılarına bak", Toast.LENGTH_LONG).show()
            else{
                Toast.makeText(this, "İNTERNET AYARLARINA BAK", Toast.LENGTH_LONG).show()
        }
    }

    }

}
