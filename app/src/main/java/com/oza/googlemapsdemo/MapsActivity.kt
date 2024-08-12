package com.oza.googlemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.oza.googlemapsdemo.databinding.ActivityMapsBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        val sydneyMarker = mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        sydneyMarker?.tag = "Restaurant"

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15F))
        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
//            isZoomGesturesEnabled = truer
//            isScrollGesturesEnabled = false
            isMyLocationButtonEnabled = true
        }

        /*val losAngles: CameraPosition = CameraPosition.Builder()
            .target(LatLng(34.052235, -118.243683))
            .zoom(17F)
            .bearing(100F)
            .tilt(45F)
            .build()
        lifecycleScope.launch {
            delay(4000L)
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(15F), 2000, null)
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(losAngles), 2000, object: GoogleMap.CancelableCallback {
                override fun onCancel() {
                    Toast.makeText(this@MapsActivity, "Cancelled", Toast.LENGTH_SHORT).show()
                }

                override fun onFinish() {
                    Toast.makeText(this@MapsActivity, "Finished", Toast.LENGTH_SHORT).show()
                }

            })
        }*/


    }

}