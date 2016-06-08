package putitout.zipjetclone.ui.activity;


import android.content.Intent;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.util.ZLog;
import putitout.zipjetclone.ui.util.ZPrefs;

/**
 * Created by SA on 5/19/2016.
 */
@SuppressWarnings("ALL")
public class MapActivity extends BaseActivity implements View.OnClickListener  {

    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap map;
    private ImageView confirmAddressImageView;
     String address ="" ;
    // latitude and longitude

    // latitude and longitude
//    double latitude = 31.5105;
//    double longitude = 74.3413;

    double latitude ;
    double longitude ;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        confirmAddressImageView = (ImageView) findViewById(R.id.confirmAddressImageView);
        confirmAddressImageView.setOnClickListener(this);

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);
                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                markerOptions.title( getAddressFromLatLng( latLng ) );

                ZLog.info("latitude" +  ":" + latLng.latitude + "longitude" + ":" + latLng.longitude);

                latitude = latLng.latitude;

                longitude = latLng.longitude;

                // Clears the previously touched position
                map.clear();

                // Animating to the touched position
                map.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                map.addMarker(markerOptions);

//                MarkerOptions options = new MarkerOptions().position( latLng );
//                options.title( getAddressFromLatLng( latLng ) );
//
//                options.icon( BitmapDescriptorFactory.defaultMarker() );
//                map.addMarker( options );

            }
        });

//        Geocoder geocoder = new Geocoder(this);
//        List<Address> addresses;
//        addresses = geocoder.getFromLocationName(<String address>, 1);
//        if(addresses.size() > 0) {
//            double latitude= addresses.get(0).getLatitude();
//            double longitude= addresses.get(0).getLongitude();
//        }

//        if (map!=null){
//            Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
//                    .title("Hamburg"));
//            Marker kiel = map.addMarker(new MarkerOptions()
//                    .position(KIEL)
//                    .title("Kiel")
//                    .snippet("Kiel is cool")
//                    .icon(BitmapDescriptorFactory
//                            .fromResource(R.drawable.ic_launcher)));
//        }


        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setRotateGesturesEnabled(true);
        GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange (final Location location) {
                LatLng loc = new LatLng (location.getLatitude(), location.getLongitude());
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));

            }
        };
        map.setOnMyLocationChangeListener(myLocationChangeListener);

//        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("its my current location");
//
//        // ROSE color icon
//        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
//
//// GREEN color icon
//        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//
//// adding marker
//        map.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(17.385044, 78.486671)).zoom(12).build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        Location myLocation = map.getMyLocation();

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

//        if(location!=null){
//            onLocationChanged(location);
//        }
//        locationManager.requestLocationUpdates(provider, 20000, 0, this);


        ZLog.info("Location" + myLocation);
        // create marker

//        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        window.setStatusBarColor(this.getResources().getColor(R.color.greenBarColor));
    }

    @Override
    public void initWidget() {

    }


    public void onMapClick(LatLng latLng) {

        MarkerOptions options = new MarkerOptions().position( latLng );
        options.title( getAddressFromLatLng( latLng ) );

        options.icon( BitmapDescriptorFactory.defaultMarker() );
        map.addMarker( options );
    }

    private String getAddressFromLatLng( LatLng latLng ) {
        Geocoder geocoder = new Geocoder( this );

         address = "";
        try {
            address = geocoder
                    .getFromLocation( latLng.latitude, latLng.longitude, 1 )
                    .get(0).getAddressLine(0);
            ZPrefs.saveString(getActivity(),ZPrefs.KEY_TOKEN,address);
        } catch (IOException e ) {
        }

        return address;
    }


    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);

//        onActivityResult(intent,requestCode,options);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.confirmAddressImageView:
                ZLog.info("address"+address);
                Intent intent = new Intent(this,HomeActivity.class);
                Bundle b = new Bundle();
                b.putDouble("lat",latitude);
                b.putDouble("long",longitude);
                intent.putExtras(b);
                startActivityForResult(intent,10);

                Double lat =  latitude;
                Double log =  longitude;

                ZPrefs.saveString(getActivity(),ZPrefs.KEY_ADDRESS,address);
                ZPrefs.saveLong(getActivity(), ZPrefs.KEY_LATITUDE, Double.doubleToLongBits(lat));
                ZPrefs.saveLong(getActivity(), ZPrefs.KEY_LONGITUDE, Double.doubleToLongBits(log));


                this.finish();
                break;
        }
    }
}
