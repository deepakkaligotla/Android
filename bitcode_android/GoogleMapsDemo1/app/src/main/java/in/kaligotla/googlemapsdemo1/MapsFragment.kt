package `in`.kaligotla.googlemapsdemo1

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import `in`.kaligotla.googlemapsdemo1.databinding.InfoWindowBinding
import androidx.core.graphics.scale

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

    @RequiresApi(Build.VERSION_CODES.O)
    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        createMarkers()
        initMapSettings()
        addMarkers()
        addShapes()
        setMarkerListeners()
        setInfoWindow()
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
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
        var icon = BitmapDescriptorFactory.fromBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background)
            .scale(100, 100, false))
        googleMap.addMarker(
            MarkerOptions()
                .position(coordinates["Bitcode"]!!)
                .title("Bitcode Technologies")
                .snippet("Android & iOS")
                .draggable(true)
                .icon(icon)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

    fun setMarkerListeners() {
        googleMap.setOnMarkerClickListener {
            Toast.makeText(requireContext(), "${it.position}", Toast.LENGTH_SHORT).show()
            false
        }
        //Anonymous class
        googleMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDrag(p0: Marker) {
                Toast.makeText(requireContext(), "${p0.position}", Toast.LENGTH_SHORT).show()
            }

            override fun onMarkerDragEnd(p0: Marker) {
                Toast.makeText(requireContext(), "${p0.position}", Toast.LENGTH_SHORT).show()
            }

            override fun onMarkerDragStart(p0: Marker) {
                Toast.makeText(requireContext(), "${p0.position}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun setInfoWindow() {
        googleMap.setInfoWindowAdapter(SetMyInfoWindowView())
        googleMap.setOnInfoWindowClickListener {
            Toast.makeText(requireContext(), "InfoWindow clicked", Toast.LENGTH_SHORT).show()
        }
    }

    inner class SetMyInfoWindowView: GoogleMap.InfoWindowAdapter {
        override fun getInfoWindow(marker: Marker): View? {
            // Use getInfoContents instead
            return null
        }

        override fun getInfoContents(marker: Marker): View {
            val binding = InfoWindowBinding.inflate(LayoutInflater.from(requireContext()))
            binding.infoWindowTitle.text = marker.title
            if (!marker.snippet.isNullOrEmpty()) {
                binding.infoWindowDescription.text = marker.snippet
                binding.infoWindowDescription.visibility = View.VISIBLE
            } else {
                binding.infoWindowDescription.visibility = View.GONE
            }
            return binding.root
        }
    }
}