package com.example.mayur_location_permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    //debug
    private static final String TAG="locationDebug";
    //debug

    //setting location permission
    private static final int PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 0;
    //setting location permission

    //single request for getting location
    private FusedLocationProviderClient fusedLocationClient;
    public static Location mlocation;
    //single request for getting location

    //onCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationPermission();
    }
    //onCreate Method

    //setting location permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                getLocation();
            } else {
                // Permission request was denied.

                locationPermissionExplainAlert();
            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }

    private void locationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is already available, start camera preview
            getLocation();
        } else {
            // Permission is missing and must be requested.
            requestLocationPermission();
        }
    }

    private void requestLocationPermission() {
        //code for asking location permission

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with cda button to request the missing permission.
            locationPermissionExplainAlert();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void locationPermissionExplainAlert() {
        //code for alert
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("LOCATION PERMISSION REQUIRED");
        dialog.setMessage("In Order to show shops nearest to your location, I need permission to access your location");
        dialog.setPositiveButton("Give Permission", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //code for allowing permission
                Toast.makeText(MainActivity.this, "Open dialog to ask permission", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        //code for alert
    }
    //setting location permission

    //single request for getting location
    private void getLocation() {
        Log.d(TAG, "getLocation: 1");
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Log.d(TAG, "getLocation: 2");
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d(TAG, "getLocation: 3");
            Toast.makeText(this, "Don't have permission to access " +
                    "coarse and fine location", Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Check location is turned ON in device" +
                    "device settings",Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG, "getLocation: 4");
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.d(TAG, "onSuccess: ");
                        if(location==null){
                            //show user following messages
                            //make sure Cell Network is ON
                            //make sure Airplane mode is OFF
                            //make sure Internet is ON
                            Log.d(TAG, "onSuccess: Location null");
                        }
                        if (location != null) {
                            //logic to handle location object
                            Log.d(TAG, "onSuccess: Location not null");
                            mlocation=location;
                            Toast.makeText(MainActivity.this, ""+
                                    mlocation.getLatitude()+", "+
                                    mlocation.getLongitude(), Toast.LENGTH_SHORT).show();
                            sortedShops();
                        }
                    }
                });
        fusedLocationClient.getLastLocation()
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: "+e.toString());
                    }
                });
        fusedLocationClient.getLastLocation()
                .addOnCanceledListener(this, new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Log.d(TAG, "onCanceled: ");
                    }
                });
        fusedLocationClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }
    //single request for getting location

    private void sortedShops() {
        //code to show sorted shops according to locations
        Toast.makeText(this, "Sorted Shops", Toast.LENGTH_SHORT).show();
    }
}