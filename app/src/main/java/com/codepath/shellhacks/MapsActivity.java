package com.codepath.shellhacks;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;

    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;

    private LatLng latLng;

    public String userName = getIntent().getStringExtra("name"); // 'string1' needs to match the key we used when we put the string in the Intent
    public String bloodType = getIntent().getStringExtra("bloodType");
    public String allergies = getIntent().getStringExtra("allergies");
    public String number1 = getIntent().getStringExtra("number1");
    public String number2 = getIntent().getStringExtra("number2");
    public String dangerText = getIntent().getStringExtra("text");
    public String medicalText = getIntent().getStringExtra("textMedical");
    public String typeEmergency = getIntent().getStringExtra("typeEmergency");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                try{
                    latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    mMap.addMarker(new MarkerOptions().position(latLng).title("My Position"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


                    upDateSMS(number1, number2, getCurLat(location), getCurlong(location));


                } catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {


                    if (typeEmergency == "Med" ){
                        sendFirstSMS( number1, number2, medicalText );
                    }else if (typeEmergency == "Emergency"){
                        sendFirstSMS( number1, number2, dangerText );
                    }






                } else {


                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);

                }
            }

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void sendFirstSMS(String number1, String number2, String customMessage ){

       /* String number1 = data.getExtras().getString("number1");
        String number2 = data.getExtras().getString("number2");
        String textMessage = data.getExtras().getString("text");*/

        try {
            SmsManager smsmanager = SmsManager.getDefault();
            smsmanager.sendTextMessage(number1, null, customMessage, null, null);
            smsmanager.sendTextMessage(number2, null, customMessage, null, null);
            Toast.makeText(this, "Message is sent", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            e.printStackTrace();
            //Run error message here!!!
            Toast.makeText(this, "ERROR!!! CAN NOT SEND", Toast.LENGTH_SHORT).show();
        }


    }

    private void upDateSMS(String number1, String number2, String latitude, String longitude) {
        String message = "Latitude = " + latitude + " Longitude = " + longitude;
        try {
            SmsManager smsmanager = SmsManager.getDefault();
            smsmanager.sendTextMessage(number1, null, message, null, null);
            smsmanager.sendTextMessage(number2, null, message, null, null);
            Toast.makeText(this, "Message is sent", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            e.printStackTrace();
            //Run error message here!!!
            Toast.makeText(this, "ERROR!!! CAN NOT SEND", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurLat(Location location) {
        return String.valueOf(location.getAltitude());

    }

    private String getCurlong(Location location) {
        return String.valueOf(location.getLongitude());
    }
}