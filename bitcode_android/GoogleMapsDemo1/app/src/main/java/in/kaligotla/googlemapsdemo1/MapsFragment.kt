package `in`.kaligotla.googlemapsdemo1

import android.Manifest
import android.graphics.Color
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresPermission

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions

class MapsFragment : Fragment(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private var coordinates: MutableMap<String, LatLng> = mutableMapOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        createMarkers()
        initMapSettings()
        addMarkers()
        addShapes()
    }

    fun initMapSettings() {
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.uiSettings.isMapToolbarEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isTiltGesturesEnabled = true
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.isBuildingsEnabled = true
        googleMap.isMyLocationEnabled = true
        googleMap.isIndoorEnabled = true
    }

    fun createMarkers() {
        coordinates["Bitcode"] = LatLng(18.509112, 73.832558)
    }

    fun addMarkers() {
        googleMap.addMarker(MarkerOptions().position(coordinates["Bitcode"]!!).title("Bitcode Technologies"))
    }

    fun addShapes() {
        googleMap.addCircle(
            CircleOptions().center(LatLng(12.9716, 77.5946))
                .radius(100000.0)
                .strokeColor(Color.MAGENTA)
                .strokeWidth(2F)
                .fillColor(Color.argb(0.5f, 0.5f, 0.5f, 0.5f))
                .visible(true)
                .zIndex(30.0F)
        )

        googleMap.addPolygon(
            PolygonOptions()
                .add(coordinates["Bitcode"])
                .add(LatLng(12.9716, 77.5946))
                .add(LatLng(17.6869, 83.2185))
                .add(LatLng(21.1458, 79.0882))
                .strokeWidth(2F)
                .strokeColor(Color.RED)
                .fillColor(Color.argb(0.5f, 0.5f, 0.5f, 0.5f))
                .zIndex(10F)
        )

        googleMap.addPolyline(
            PolylineOptions()
                .add(LatLng(28.7041, 77.1024))
                .add(LatLng(26.4499, 80.3319))
                .add(LatLng(22.7196, 75.8500))
                .add(LatLng(24.5926, 72.7156))
                .add(LatLng(28.7041, 77.1024))
                .width(2F)
                .color(Color.RED)
        )
    }
}