package criptofor.mitour;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        LatLng lMacroPlaza = new LatLng(25.667788, -100.310228);
        LatLng lTeatroCiudad = new LatLng(25.668932, -100.309084);
        LatLng lMuseoHistoria =  new LatLng(25.671587, -100.306799);
        LatLng lMuseoNoreste = new LatLng(25.670789, -100.306708);
        LatLng lMuseoMetro = new LatLng(25.666232, -100.311260);
        LatLng lMuseoMarco = new LatLng(25.664736, -100.309780);
        LatLng lMuseoPalacio = new LatLng(25.672482, -100.309550);

        mMap.addMarker(new MarkerOptions().position(lMacroPlaza).
                title("Macro Plaza").
                snippet("#1, 5/5, llegada: 9:00am, partida: 10:00am"));
        mMap.addMarker(new MarkerOptions().position(lTeatroCiudad).title("Teatro de la ciudad")
                .snippet("#2, 4/5, llegada: 10:10am, partida: 11:30am"));
        mMap.addMarker(new MarkerOptions().position(lMuseoHistoria).title("Museo de historia")
                .snippet("#3, 4/5, llegada: 11:40am, partida: 01:00pm"));
        mMap.addMarker(new MarkerOptions().position(lMuseoNoreste).title("Museo del noreste")
                .snippet("#4, 5/5, llegada: 01:10pm, partida: 03:00pm"));
        mMap.addMarker(new MarkerOptions().position(lMuseoMetro).title("Museo Metropolitano de Monterrey")
                .snippet("#5, 3/5, llegada: 3:15pm, partida: 04:15am"));
        mMap.addMarker(new MarkerOptions().position(lMuseoMarco).title("Museo Marco")
                .snippet("#6, 5/5, llegada: 04:25pm, partida: 05:25pm"));
        mMap.addMarker(new MarkerOptions().position(lMuseoPalacio).title("Museo del palacio")
                .snippet("#7, 3/5, llegada: 5:40pm, partida: 06:40pm"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lMacroPlaza, 15.8f));

        // Instantiates a new Polygon object and adds points to define a rectangle
        /*
        PolygonOptions rectOptions = new PolygonOptions()
                .add(
                        lMacroPlaza,
                        lTeatroCiudad,
                        lMuseoHistoria,
                        lMuseoNoreste,
                        lMuseoMetro,
                        lMuseoMarco,
                        lMuseoPalacio
                )
                .strokeColor(Color.BLUE);
        */

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(
                        lMacroPlaza,
                        lTeatroCiudad,
                        lMuseoHistoria,
                        lMuseoNoreste,
                        lMuseoMetro,
                        lMuseoMarco,
                        lMuseoPalacio
                )
                .width(5)
                .color(Color.RED)
                .geodesic(true)
                );

        Circle circle = mMap.addCircle(new CircleOptions()
                .center(lMacroPlaza)
                .radius(70)
                .fillColor(Color.argb(50,0,0,200)))
                ;

        // Get back the mutable Polygon
        //Polygon polygon = mMap.addPolygon(rectOptions);

        mMap.setMyLocationEnabled(true);
    }
}
