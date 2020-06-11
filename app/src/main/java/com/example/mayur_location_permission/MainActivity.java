package com.example.mayur_location_permission;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationPermission();
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
    }

    private void locationPermissionExplainAlert() {
        //code for paying alert
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Select Payment Mode");
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onDestroy();
            }
        });
        dialog.setPositiveButton("Give Permission", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //code for allowing permission
            }
        });

        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
        //code for paying alert
    }

    private void sortedShops() {
        //code to show sorted shops according to locations
    }
}