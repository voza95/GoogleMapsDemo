package com.oza.googlemapsdemo

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker

class CustomInfoAdapter(context: Context): InfoWindowAdapter {

    private val contextView = (context as Activity).layoutInflater.inflate(R.layout.custom_info_window, null)

    override fun getInfoContents(marker: Marker): View? {
        renderViews(marker, contextView)
        return contextView
    }

    override fun getInfoWindow(marker: Marker): View? {
        renderViews(marker, contextView)
        return contextView
    }

    private fun renderViews(marker: Marker?, contentView: View) {
        val title = marker?.title
        val description = marker?.snippet

        val titleTextView = contentView.findViewById<TextView>(R.id.title_textView)
        titleTextView.text = title
        val descriptionTextView = contentView.findViewById<TextView>(R.id.description_textView)
        descriptionTextView.text = description
    }
}