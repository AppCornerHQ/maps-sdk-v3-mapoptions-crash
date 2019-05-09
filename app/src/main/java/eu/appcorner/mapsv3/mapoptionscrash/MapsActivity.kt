package eu.appcorner.mapsv3.mapoptionscrash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.maps.*
import com.google.android.libraries.maps.model.CameraPosition
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)

        val mapFragment = if (savedInstanceState == null) {
            val options = GoogleMapOptions().camera(CameraPosition.fromLatLngZoom(LatLng(48.8583736, 2.2922926), 10.0f))

            SupportMapFragment.newInstance(options).also {
                supportFragmentManager.beginTransaction().replace(R.id.map_container, it).commit()
            }
        } else
            supportFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        // Add a marker in Paris and move the camera
        val paris = LatLng(48.8583736, 2.2922926)
        googleMap.addMarker(MarkerOptions().position(paris).title("Marker in Paris"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paris, 15.0f))
    }
}