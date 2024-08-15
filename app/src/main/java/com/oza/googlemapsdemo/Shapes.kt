package com.oza.googlemapsdemo

import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.delay

class Shapes {

    private val losAngeles = LatLng(34.052235, -118.243683)
    private val newYork = LatLng(40.730610, -73.935242)
    private val france = LatLng(48.736842497057594, 0.1415298459960326)
    private val colombia = LatLng(5.8600760428333825, -75.092846909068)

    private val hollywood = LatLng(34.0206084,-119.0709899)
    private val random = LatLng(33.536646834396045, -117.47268099668223)
    private val sizFlag = LatLng(34.4185655,-118.5770338)
    private val mountainHighResort = LatLng(34.0837191,-117.7736451)

    private val p0 = LatLng(34.120684916146736, -118.35351798656026)
    private val p1 = LatLng(34.0021609833433, -118.11756043462083)
    private val p2 = LatLng(33.860422948235886, -118.22112551240973)
    private val p3 = LatLng(33.897652012054614, -118.37807465329251)

    private suspend fun addPloyLine(mMap: GoogleMap) {
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

    fun addPolygon(map: GoogleMap) {
        val polygon = map.addPolygon(
            PolygonOptions().apply {
                add(hollywood, random, mountainHighResort, sizFlag)
                fillColor(-0x2101)
                addHole(listOf(p0, p1, p2, p3))
            }
        )
    }

    fun addCircle(map: GoogleMap) {
        val circle = map.addCircle(
            CircleOptions().apply {
                center(losAngeles)
                radius(5000.0)
                fillColor(R.color.purple)
                strokeColor(R.color.sky_blue)
            }
        )
    }

}