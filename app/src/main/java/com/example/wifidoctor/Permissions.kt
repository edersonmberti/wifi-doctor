package com.example.wifidoctor

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Permissions {

    var isFineLocationPermissionEnabled = false
    var isCoarseLocationPermissionEnabled = false
    var isAccessWifiStatePermissionEnabled = false
    var isChangeWifiStatePermissionEnabled = false

    fun requestFineLocationPermission(activity: Activity) {
        ActivityCompat.requestPermissions(activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
    }

    fun checkFineLocationPermission(context: Context) {
        val permission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        val isPermissionEnabled = permission == PackageManager.PERMISSION_GRANTED

        isFineLocationPermissionEnabled = isPermissionEnabled
        Log.i("Fine Location: ", isPermissionEnabled.toString())
    }

    fun checkCoarseLocationPermission(context: Context) {
        val permission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        val isPermissionEnabled = permission == PackageManager.PERMISSION_GRANTED

        isCoarseLocationPermissionEnabled = isPermissionEnabled
        Log.i("Coarse Location: ", isPermissionEnabled.toString())
    }

    fun checkAccessWifiStatePermission(context: Context) {
        val permission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        val isPermissionEnabled = permission == PackageManager.PERMISSION_GRANTED

        isAccessWifiStatePermissionEnabled = isPermissionEnabled
        Log.i("Access Wifi State: ", isPermissionEnabled.toString())
    }

    fun checkChangeWifiStatePermission(context: Context) {
        val permission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        val isPermissionEnabled = permission == PackageManager.PERMISSION_GRANTED

        isChangeWifiStatePermissionEnabled = isPermissionEnabled
        Log.i("Change Wifi State: ", isPermissionEnabled.toString())
    }
}