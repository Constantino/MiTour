package criptofor.mitour;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.io.InputStream;

public class Main extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        /*
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
        */
        fragmentManager.beginTransaction()
                .replace(R.id.container, FirstFragment.newInstance())
                .commit();


    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Main) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public static class FirstFragment extends Fragment {

        private static FirstFragment newInstance() {
            FirstFragment fragment = new FirstFragment();
            return fragment;
        }

        public FirstFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_maps, container, false);

            return rootView;
        }

        /*
        @Override
        public
        */

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Main) activity).onSectionAttached(1);
        }


        private GoogleMap mMap; // Might be null if Google Play services APK is not available.

        /*
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setUpMapIfNeeded();
        }

        @Override
        public void onResume() {
            super.onResume();
            setUpMapIfNeeded();
        }
        */

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

        /*
        private void setUpMapIfNeeded() {
            // Do a null check to confirm that we have not already instantiated the map.
            if (mMap == null) {
                // Try to obtain the map from the SupportMapFragment.
                mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                        .getMap();
                // Check if we were successful in obtaining the map.
                if (mMap != null) {
                    try {
                        setUpMap();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        */

        /**
         * This is where we can add markers or lines, add listeners or move the camera. In this case, we
         * just add a marker near Africa.
         * <p/>
         * This should only be called once and when we are sure that {@link #mMap} is not null.
         */
        private void setUpMap() throws IOException {

            LatLng lMacroPlaza = new LatLng(25.667788, -100.310228);
            LatLng lTeatroCiudad = new LatLng(25.668932, -100.309084);
            LatLng lMuseoHistoria =  new LatLng(25.671587, -100.306799);
            LatLng lMuseoNoreste = new LatLng(25.670789, -100.306708);
            LatLng lMuseoMetro = new LatLng(25.666232, -100.311260);
            LatLng lMuseoMarco = new LatLng(25.664736, -100.309780);
            LatLng lMuseoPalacio = new LatLng(25.672482, -100.309550);

            //Obtener la matriz de tiempos
            //https://maps.googleapis.com/maps/api/distancematrix/json?origins=fundidora+monterrey&destinations=cola+de+caballo+monterrey&mode=car&language=en-EN
            //<exp>



            //</exp>


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

        private void readStream(InputStream in) {
            return;
        }


    }

}
