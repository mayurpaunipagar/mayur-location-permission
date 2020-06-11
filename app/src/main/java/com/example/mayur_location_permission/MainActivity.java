package com.example.mayur_location_permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.

                sortedShops();
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
            sortedShops();
        }else {
            // Permission is missing and must be requested.
            requestLocationPermission();
        }
    }

    private void requestLocationPermission() {
        //code for asking location permission

        if(ActivityCompat.shouldShowRequestPermissionRationale(
                this,Manifest.permission.ACCESS_FINE_LOCATION)){
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with cda button to request the missing permission.
            locationPermissionExplainAlert();
        }
        else{
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void locationPermissionExplainAlert() {
        //code for alert
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
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

        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
        //code for alert
    }

    private void sortedShops() {
        //code to show sorted shops according to locations
        Toast.makeText(this, "Sorted Shops", Toast.LENGTH_SHORT).show();
    }
}