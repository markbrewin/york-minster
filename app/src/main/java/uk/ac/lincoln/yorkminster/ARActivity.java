package uk.ac.lincoln.yorkminster;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.io.IOException;

public class ARActivity extends AppCompatActivity {
    //Creates the AR environment.
    protected ArchitectView architectView;

    private FusedLocationProviderClient mFusedLocationClient;
    private static LocationRequest mLocationRequest = new LocationRequest();
    private LocationCallback mLocationCallback;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView.setWebContentsDebuggingEnabled(true);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    mLastLocation = location;

                    float accuracy = mLastLocation.hasAccuracy() ? mLastLocation.getAccuracy() : 1000;

                    if (mLastLocation.hasAltitude()) {
                        architectView.setLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), mLastLocation.getAltitude(), accuracy);
                    } else {
                        architectView.setLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), accuracy);
                    }
                }
            };
        };

        //Config for the AR view.
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey("WKu1VfuCZlgTpQ/lOgCI6tTBjOxQHm2nu/GnJ0WZzlcttbYNFEIJ947P6p/DXfmBOgXLGgDv6VW9X+qugzFUSgY8hwNoEFJU9ot2BemF3CYz/HE+bDkcRQQfB27rgH0CdJCnrhWB5x5L/nIWlA6HdSmaTKT4S8hodMgkA8cpesBTYWx0ZWRfX0d9NWwlda/fQfPMStFvzFzWZLJWhb2b+oFZdhQXFyfsuvsmSlLGePKB8txRGfw1eNlylCxY9ImrY7WQmkATDjTEH5qTIjofuIiZmvSKvK2MOLDaKqEoCcWPjj5pp6VBKSb1kFftkmVp8e9onV2Pi/rLD3NrvGXQCN72X83xnkwyi0cP1+8nWeMxlHeb1ZILiWKzBOJoq3Q8cMVgctCkTtPnX0UTdKsxifis292JswjQ7VLnT3m27e+5MbR+licuHAbkm5HF276IlqAMti4BshbDsRzVIqKc1ZF/iAP2wYSbpdnSWsg9s5ajNVjU6v2dgJg64W0Z0aPLvO23NMVvrDq5vQi/buwJzCVEjsV1fuIRgONQo6L5+TUnxq1flyOePxGqRRa7ps+E2J84kEzFeUuFUrfNXDUHQDszL954rKi3N97XPOKfskvncFaOg2/XGikTxHn7nBjIv6inlPKRcfCVKwkh80T/RRM8IYqGh3d9MabeOdauscc=");

        //Gets the AR View.
        architectView = new ArchitectView(this);
        ///Sets the config for AR view.
        architectView.onCreate(config);

        setContentView(architectView);
    }

    private void startLocationUpdates() {
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                if(ContextCompat.checkSelfPermission(ARActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mFusedLocationClient.requestLocationUpdates(ARActivity.mLocationRequest,
                            mLocationCallback,
                            null /* Looper */);
                }
            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(ARActivity.this,
                                0x1);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });
    }

    private void stopLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        architectView.onPostCreate();

        try {
            this.architectView.load( "index.html" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        architectView.onResume(); // Mandatory ArchitectView lifecycle call
        startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        architectView.onPause(); // Mandatory ArchitectView lifecycle call
        stopLocationUpdates();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        architectView.clearCache(); //Deletes all cached files.
        architectView.onDestroy(); // Mandatory ArchitectView lifecycle call
    }
}