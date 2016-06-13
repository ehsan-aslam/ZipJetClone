package putitout.zipjetclone.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.util.ZLog;
import putitout.zipjetclone.ui.util.ZPrefs;
import putitout.zipjetclone.ui.util.ZUtil;

/**
 * Created by SA on 5/19/2016.
 */
@SuppressWarnings("ALL")
public class MapActivity extends BaseActivity implements View.OnClickListener  {

    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private static final String TAG = MapActivity.class.getSimpleName();
    private GoogleMap map;
    private ImageView confirmAddressImageView;
    String address = "";
    // latitude and longitude

    // latitude and longitude
//    double latitude = 31.5105;
//    double longitude = 74.3413;

    double latitude ;
    double longitude ;


    private boolean gps_enabled=false;
    private boolean network_enabled=false;
    Location location;
    Double MyLat, MyLong;
    String CityName = "";
    String StateName = "";
    String CountryName = "";
    private String AddressLine = "";
    boolean isCurrentAddress = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        confirmAddressImageView = (ImageView) findViewById(R.id.confirmAddressImageView);
        confirmAddressImageView.setOnClickListener(this);
        getMyCurrentLocation();

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                isCurrentAddress=false;

                ZLog.info(TAG+""+isCurrentAddress);

                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);
                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                markerOptions.title( address );

                ZLog.info(TAG+"latitude" +  ":" + latLng.latitude + "longitude" + ":" + latLng.longitude);

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
                getMyCurrentLocation();

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


        ZLog.info(TAG+"Location" + myLocation);
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

    @Override
    protected void onResume() {
        super.onResume();
        turnGPSOn();
    }

    public void turnGPSOn(){
        try {
            String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if(!provider.contains("gps")){ //if gps is disabled
                final Intent poke = new Intent();
                poke.setClassName("com.android.settings","com.android.settings.widget.SettingsAppWidgetProvider");
                poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
                poke.setData(Uri.parse("3"));
                sendBroadcast(poke);
            }
        }
        catch (Exception e) {
        }
    }

    // Method to turn off the GPS
    public void turnGPSOff(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(provider.contains("gps")){ //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }

    // turning off the GPS if its in on state. to avoid the battery drain.

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        turnGPSOff();
    }

    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }

    public void getMyCurrentLocation() {

        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locListener = new MyLocationListener();
        try{gps_enabled=locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);}catch(Exception ex){}

        try{network_enabled=locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);}catch(Exception ex){}
        //don't start listeners if no provider is enabled

        //if(!gps_enabled && !network_enabled)

        //return false;
        if(gps_enabled){
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
        }

        if(gps_enabled){
            location=locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        if(network_enabled && location==null){
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);

        }
        if(network_enabled && location==null)    {
            location=locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        }
        if (location != null) {

            MyLat = location.getLatitude();
            MyLong = location.getLongitude();
        } else {
            Location loc= getLastKnownLocation(this);
            if (loc != null) {
                MyLat = loc.getLatitude();
                MyLong = loc.getLongitude();
            }
        }

        locManager.removeUpdates(locListener); // removes the periodic updates from location listener to avoid battery drainage. If you want to get location at the periodic intervals call this method using pending intent.

        try
        {
// Getting address from found locations.
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());
            addresses = geocoder.getFromLocation(MyLat, MyLong, 1);
            StateName= addresses.get(0).getAdminArea();
            CityName = addresses.get(0).getLocality();
            CountryName = addresses.get(0).getCountryName();
            AddressLine = addresses.get(0).getAddressLine(0);
            // you can get more details other than this . like country code, state code, etc.
            ZLog.info(TAG+" StateName " + StateName);
            ZLog.info(TAG+" CityName " + CityName);
            ZLog.info(TAG+" CountryName " + CountryName);
            ZLog.info(TAG+" AddressLine " + AddressLine);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
//        textView2.setText(""+MyLat);
//        textView3.setText(""+MyLong);
        ZLog.info(TAG+" StateName " + StateName +" CityName " + CityName +" CountryName " + CountryName);
    }

    // Location listener class. to get location.
    public class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            if (location != null) {
            }
        }        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    }
    public static Location getLastKnownLocation(Context context) {
        Location location = null;
        LocationManager locationmanager = (LocationManager)context.getSystemService("location");
        List list = locationmanager.getAllProviders();
        boolean i = false;
        Iterator iterator = list.iterator();
        do {
            //System.out.println("---------------------------------------------------------------------");
            if(!iterator.hasNext())
                break;
            String s = (String)iterator.next();
            //if(i != 0 && !locationmanager.isProviderEnabled(s))
            if(i != false && !locationmanager.isProviderEnabled(s))
                continue;
            // System.out.println("provider ===> "+s);
            Location location1 = locationmanager.getLastKnownLocation(s);
            if(location1 == null)
                continue;
            if(location != null)
            {
                //System.out.println("location ===> "+location);
                //System.out.println("location1 ===> "+location);
                float f = location.getAccuracy();
                float f1 = location1.getAccuracy();
                if(f >= f1)
                {
                    long l = location1.getTime();
                    long l1 = location.getTime();
                    if(l - l1 <= 600000L)
                        continue;
                }
            }
            location = location1;
            // System.out.println("location  out ===> "+location);
            //System.out.println("location1 out===> "+location);
            i = locationmanager.isProviderEnabled(s);
            // System.out.println("---------------------------------------------------------------------");
        } while(true);
        return location;

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
                ZLog.info(TAG+"address"+AddressLine); ZLog.info(TAG+"CityName"+CityName);

                Intent intent = new Intent(this,HomeActivity.class);
                Bundle b = new Bundle();
                b.putDouble(ZUtil.KEY_LATITUDE,MyLat);
                b.putDouble(ZUtil.KEY_LONGITUDE,MyLong);
                intent.putExtras(b);
                startActivityForResult(intent,10);

                Double lat =  latitude;
                Double log =  longitude;

                if(isCurrentAddress){
                    ZPrefs.saveString(getActivity(),ZPrefs.KEY_ADDRESS,AddressLine);
                }
                else{

                    isCurrentAddress= false;
                    ZPrefs.saveString(getActivity(),ZPrefs.KEY_ADDRESS,address);
                }


                ZPrefs.saveLong(getActivity(), ZPrefs.KEY_LATITUDE, Double.doubleToLongBits(lat));
                ZPrefs.saveLong(getActivity(), ZPrefs.KEY_LONGITUDE, Double.doubleToLongBits(log));


                this.finish();
                break;
        }
    }
}
