package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.UUID;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public final static String EXTRA_LOCATION = "trinityconnect.android.bignerdranch.com.trinityconnect.LOCATION";

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, MapsActivity.class);
        return intent;
    }

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


    private void setUpMap() {
        LatLng mLatLng = new LatLng(41.747368, -72.690357);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 17));

        //Sports Fields
        Marker millerField = mMap.addMarker(new MarkerOptions().position(new LatLng(41.749661, -72.689377)).title("Miller Field"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.749651, -72.688036)).title("Sheppard Field"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.748114, -72.689452)).title("Baseball Field"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.749252, -72.690447)).title("Lacrosse Field"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.746841, -72.688731)).title("Ferris"));

        //North part of campus
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.748309, -72.691884)).title("Main Quad"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.749221, -72.691801)).title("Chapel"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.751301, -72.691597)).title("Vernon Social"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.751354, -72.690701)).title("North"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.750694, -72.690744)).title("Hamlin"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.750384, -72.691233)).title("The Bistro"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.750919, -72.689447)).title("Psi Upsilon"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.751338, -72.692193)).title("AD"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.751751, -72.692790)).title("The Hall"));

        //Middle part of campus
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.745920, -72.692439)).title("Elton"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.746041, -72.691953)).title("Jones"));

        Marker mather = mMap.addMarker(new MarkerOptions().position(new LatLng(41.746652, -72.692580)).title("Mather"));
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){

                @Override
                public void onInfoWindowClick(Marker arg0){
                    Intent intent = new Intent(MapsActivity.this, EventListActivity.class);

                    String loc = arg0.getTitle();
                    intent.putExtra(EXTRA_LOCATION, loc);
                    Log.i("MapsActivity", loc);
                    startActivity(intent);


                }
        });

        Marker raetherLibrary = mMap.addMarker(new MarkerOptions().position(new LatLng(41.747277, -72.690873)).title("Raether Library"));

        mMap.addMarker(new MarkerOptions().position(new LatLng(41.745946, -72.691441)).title("McCook"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.746600, -72.690635)).title("Austin Arts"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.747334, -72.691777)).title("Cinestudio"));

        //South part of campus
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.743288, -72.691475)).title("Trinity Commons"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.744059, -72.691511)).title("Summit East"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.744004, -72.691898)).title("Summit South"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.744384, -72.691993)).title("Summit North"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.745065, -72.691094)).title("LSC Quad"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.744430, -72.691063)).title("MECC"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.745117, -72.690513)).title("LSC"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.744741, -72.691968)).title("Funston"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.745055, -72.691945)).title("Smith"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.745355, -72.692274)).title("Wheaton"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.745406, -72.691939)).title("Jackson"));

    }
}
