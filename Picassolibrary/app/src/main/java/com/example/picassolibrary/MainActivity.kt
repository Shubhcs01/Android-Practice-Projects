package com.example.dexterLib

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dexterLib.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//            Dexter.withContext(this)
//                .withPermission(Manifest.permission.CAMERA)
//                .withListener(object : PermissionListener {
//                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
//                      Toast.makeText(this@MainActivity, "Permission Granted", Toast.LENGTH_SHORT).show()
//                    }
//
//                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
//                        Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
//                    }
//
//                    override fun onPermissionRationaleShouldBeShown(
//                        permission: PermissionRequest?,
//                        token: PermissionToken?
//                    ) {
//
//                    }
//                }).check()

            Dexter.withContext(this)
                .withPermissions(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.RECORD_AUDIO
                ).withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        Toast.makeText(this@MainActivity, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: List<PermissionRequest>,
                        token: PermissionToken
                    ) {
                        Toast.makeText(this@MainActivity, "Pta nhi", Toast.LENGTH_SHORT).show()
                    }
                }).check()

    }
}