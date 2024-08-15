package com.oza.googlemapsdemo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.MenuProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdate

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.oza.googlemapsdemo.databinding.ActivityMapsBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val losAngeles = LatLng(34.052235, -118.243683)
    private val newYork = LatLng(40.730610, -73.935242)
    private val france = LatLng(48.736842497057594, 0.1415298459960326)
    private val colombia = LatLng(5.8600760428333825, -75.092846909068)

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
        mMap.addMarker(MarkerOptions().position(losAngeles).title("Los Angles").snippet("Some random text"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 1F))
        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
//            isZoomGesturesEnabled = truer
//            isScrollGesturesEnabled = false
            isMyLocationButtonEnabled = true
        }

        lifecycleScope.launch {
            addPloyLine()
        }
    }

    private suspend fun addPloyLine() {
        val polyLine = mMap.addPolyline(
            PolylineOptions().apply {
                add(losAngeles, newYork, france)
                width(5F)
                color(Color.BLUE)
                geodesic(true)
            }
        )
        delay(8000L)
        val newList = listOf<LatLng>(
            losAngeles, france, colombia
        )
        polyLine.points = newList
    }

}