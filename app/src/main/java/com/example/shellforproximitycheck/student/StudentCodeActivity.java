package com.example.shellforproximitycheck.student;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.shellforproximitycheck.R;
import com.example.shellforproximitycheck.teacher.CodeGenerator;
import com.example.shellforproximitycheck.teacher.CreateGeofenceActivity;
import com.example.shellforproximitycheck.teacher.GenerateCodeActivity;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

public class StudentCodeActivity extends AppCompatActivity {
    final String incorrectLength = "The code must be 5-digits long!";
    final String incorrectCode = "Incorrect code.";
    final String accessGranted = "You've been checked into class.";
    double lat;
    double lng;

    private final String TAG = "StudentCodeActivity";

    private FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback callback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            //super.onLocationResult(locationResult);
            if (locationResult == null){
                return;
            }
            for (Location location : locationResult.getLocations()){
                Log.d(TAG, "onLocationResult: " + location.toString());
                lat = location.getLatitude();
                lng = location.getLongitude();
                float [] results = new float[3];
                //CreateGeofenceActivity.geofenceLatLng.latitude
                //CreateGeofenceActivity.geofenceLatLng.longitude
                //LAT: 39.9904608
                //LONG:-75.2513041
                //LAT: 39.9952056
                //LONG: -75.375474
                Location.distanceBetween(CreateGeofenceActivity.geofenceLatLng.latitude, CreateGeofenceActivity.geofenceLatLng.longitude,
                        lat, lng, results);
                if (results[0] <= 10){
                    Log.d(TAG, "Within range: " + results[0]);
                    Toast.makeText(getApplicationContext(), "Yes: " + results[0], Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, "Not in range: " + results[0]);
                    Toast.makeText(getApplicationContext(), "No: " + results[0], Toast.LENGTH_LONG).show();
                }
            }
        }

    };

    final int FINE_LOCATION_ACCESS_REQUEST_CODE = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);
        //final TextView enterCodeTV = findViewById(R.id.enterCodeText);
        final EditText password = findViewById(R.id.password);
        //final Button checkinButton = findViewById(R.id.checkin);
        final Button testButton = findViewById(R.id.test_student);
        System.out.println("Code: " + CodeGenerator.code);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        /*
        checkinButton.setOnClickListener(v -> {
            validate(password, password.getText().toString());
        }); */

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = password.getText().toString();
                validate(password, text);
            }
        };
        password.addTextChangedListener(afterTextChangedListener);

        testButton.setOnClickListener(v ->
                changeActivityTest()
        );

        /*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, FINE_LOCATION_ACCESS_REQUEST_CODE);

            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        //getLastLocation();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            checkSettingsAndStartLocationUpdates();
        } else {
            askLocationPermission();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocationUpdates();
    }

    private void checkSettingsAndStartLocationUpdates() {
        LocationSettingsRequest request = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).build();

        SettingsClient client = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> locationSettingsResponseTask = client.checkLocationSettings(request);
        locationSettingsResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                startLocationUpdates();
            }
        });

        locationSettingsResponseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException apiException = (ResolvableApiException) e;
                    try {
                        apiException.startResolutionForResult(StudentCodeActivity.this, 1001);
                    } catch (IntentSender.SendIntentException sendIntentException) {
                        sendIntentException.printStackTrace();
                    }
                }
            }
        });
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, callback, Looper.getMainLooper());
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(callback);
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.d(TAG, "onSuccess: " + location.toString());
                    Log.d(TAG, "onSuccess: " + location.getLatitude());
                    Log.d(TAG, "onSuccess: " + location.getLongitude());

                } else {
                    Log.d(TAG, "onSuccess: Location was null...");
                }


            }
        });
        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
            }
        });
    }

    private void askLocationPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, FINE_LOCATION_ACCESS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FINE_LOCATION_ACCESS_REQUEST_CODE) {
            checkSettingsAndStartLocationUpdates();

        }
    }


    private void changeActivityTest(){
        Intent intent = new Intent(this, GenerateCodeActivity.class);
        startActivity(intent);
    }

    private void changeActivitySuccess(){
        //int StudentID = getIntent().getIntExtra("Student", 0); // Passing along StudentID to CheckInSuccess
        Intent intent = new Intent(this, CheckInSuccessActivity.class);
        //intent.putExtra("Student", StudentID);
        startActivity(intent);
    }


    void validate(TextView tv, String text){
        if (text.length() == 5) {
            if (text.equals(CodeGenerator.code)){
                Toast.makeText(getApplicationContext(), accessGranted, Toast.LENGTH_SHORT).show();
                changeActivitySuccess();
            } else {
                Toast.makeText(getApplicationContext(), incorrectCode, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), incorrectLength, Toast.LENGTH_SHORT).show();
        }
    }
}
