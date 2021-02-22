package com.example.wifidoctor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var wifiManager: WifiManager
    private lateinit var permissions: Permissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        permissions = Permissions()

        permissions.checkAccessWifiStatePermission(applicationContext)
        permissions.checkChangeWifiStatePermission(applicationContext)
        permissions.checkCoarseLocationPermission(applicationContext)
        permissions.checkFineLocationPermission(applicationContext)

        if (!permissions.isFineLocationPermissionEnabled) {
            permissions.requestFineLocationPermission(this@MainActivity)
        }

        buttonScan.setOnClickListener { scanWifi() }

        if (!wifiManager.isWifiEnabled) {
            Toast.makeText(
                    this,
                    "WiFi is disabled ... we need to enabled it!",
                    Toast.LENGTH_LONG)
                    .show()

            wifiManager.setWifiEnabled(true)
        }
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(wifiScanReceiver)
    }

    private fun scanWifi() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        applicationContext.registerReceiver(wifiScanReceiver, intentFilter)

        if (wifiManager.startScan()) {
            Toast.makeText(this, "Scanning WiFi ...", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Error to scan WiFi", Toast.LENGTH_LONG).show()
        }
    }

    private val wifiScanReceiver: BroadcastReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)

            if (success) {
                scanSuccess()
            } else {
                scanFailure()
            }
        }
    }

    private fun scanSuccess() {
        val results = wifiManager.scanResults

        Log.i("Results success", results.toString())
    }

    private fun scanFailure() {
        val results = wifiManager.scanResults

        Log.i("Results error", results.toString())
    }
}