package com.oza.googlemapsdemo

import android.graphics.Bitmap
import android.graphics.Canvas
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
import com.oza.googlemapsdemo.databinding.ActivityMapsBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerDragListener, OnMarkerClickListener {

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
        val sydneyMarker = mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").snippet("Some other random text").draggable(true))
        sydneyMarker?.tag = "Restaurant"

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15F))
        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
//            isZoomGesturesEnabled = truer
//            isScrollGesturesEnabled = false
            isMyLocationButtonEnabled = true
        }

        val losAngles = LatLng(34.052235, -118.243683)
        val losAnglesMarker = mMap.addMarker(
            MarkerOptions()
                .position(losAngles)
                .title("Marker in los angles")
                .snippet("Some random text")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_android))
//                .icon(BitmapDescriptorFactory.defaultMarker(114F))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                .flat(true)// Stop the marker from moving on map orientation change
                .zIndex(1F) // Push this marker on top if there is another marker
        )
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
        mMap.setOnMarkerDragListener(this)
        mMap.setOnMarkerClickListener(this)
    }

    override fun onMarkerDrag(p0: Marker) {
        Log.d("MyCall", "Drag")
    }

    override fun onMarkerDragEnd(p0: Marker) {
        Log.d("MyCall", "End")
    }

    override fun onMarkerDragStart(p0: Marker) {
        Log.d("MyCall", "Start")
    }

    private fun fromVectorToBitmap(id: Int, color: Int): BitmapDescriptor {
        val vectorDrawable: Drawable? = ResourcesCompat.getDrawable(resources, id, null)
        if (vectorDrawable == null) {
            return BitmapDescriptorFactory.defaultMarker()
        }
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.setTint(color)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17F), 2000, null)
        marker.showInfoWindow()
        lifecycleScope.launch {
            delay(5000L)
            marker.hideInfoWindow()
        }
        return true
    }

}