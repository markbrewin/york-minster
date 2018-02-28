package uk.ac.lincoln.yorkminster;

import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;

import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.IOException;

public class ARActivity extends AppCompatActivity implements LocationListener{
    //Creates the AR environment.
    protected ArchitectView architectView;

    private LocationProvider locationProvider;

    private final LocationProvider.ErrorCallback errorCallback = new LocationProvider.ErrorCallback() {
        @Override
        public void noProvidersEnabled() {
            Toast.makeText(ARActivity.this, "No location providers!", Toast.LENGTH_LONG).show();
        }
    };

    private final ArchitectView.SensorAccuracyChangeListener sensorAccuracyChangeListener = new ArchitectView.SensorAccuracyChangeListener() {
        @Override
        public void onCompassAccuracyChanged(int accuracy) {
            if (accuracy < SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM) {
                Toast.makeText(ARActivity.this, "Compass accuracy low!", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView.setWebContentsDebuggingEnabled(true);

        //Config for the AR view.
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey("WKu1VfuCZlgTpQ/lOgCI6tTBjOxQHm2nu/GnJ0WZzlcttbYNFEIJ947P6p/DXfmBOgXLGgDv6VW9X+qugzFUSgY8hwNoEFJU9ot2BemF3CYz/HE+bDkcRQQfB27rgH0CdJCnrhWB5x5L/nIWlA6HdSmaTKT4S8hodMgkA8cpesBTYWx0ZWRfX0d9NWwlda/fQfPMStFvzFzWZLJWhb2b+oFZdhQXFyfsuvsmSlLGePKB8txRGfw1eNlylCxY9ImrY7WQmkATDjTEH5qTIjofuIiZmvSKvK2MOLDaKqEoCcWPjj5pp6VBKSb1kFftkmVp8e9onV2Pi/rLD3NrvGXQCN72X83xnkwyi0cP1+8nWeMxlHeb1ZILiWKzBOJoq3Q8cMVgctCkTtPnX0UTdKsxifis292JswjQ7VLnT3m27e+5MbR+licuHAbkm5HF276IlqAMti4BshbDsRzVIqKc1ZF/iAP2wYSbpdnSWsg9s5ajNVjU6v2dgJg64W0Z0aPLvO23NMVvrDq5vQi/buwJzCVEjsV1fuIRgONQo6L5+TUnxq1flyOePxGqRRa7ps+E2J84kEzFeUuFUrfNXDUHQDszL954rKi3N97XPOKfskvncFaOg2/XGikTxHn7nBjIv6inlPKRcfCVKwkh80T/RRM8IYqGh3d9MabeOdauscc=");

        //Gets the AR View.
        architectView = new ArchitectView(this);
        locationProvider = new LocationProvider(this, this, errorCallback);

        ///Sets the config for AR view.
        architectView.onCreate(config);

        setContentView(architectView);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        architectView.onPostCreate();

        try {
            this.architectView.load( "minster.html" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        architectView.onResume(); // Mandatory ArchitectView lifecycle call
        locationProvider.onResume();
        architectView.registerSensorAccuracyChangeListener(sensorAccuracyChangeListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        architectView.onPause(); // Mandatory ArchitectView lifecycle call
        locationProvider.onPause();
        architectView.unregisterSensorAccuracyChangeListener(sensorAccuracyChangeListener);
    }

    @Override
    public void onLocationChanged(Location location) {
        float accuracy = location.hasAccuracy() ? location.getAccuracy() : 1000;
        if (location.hasAltitude()) {
            architectView.setLocation(location.getLatitude(), location.getLongitude(), location.getAltitude(), accuracy);
        } else {
            architectView.setLocation(location.getLatitude(), location.getLongitude(), accuracy);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        architectView.clearCache(); //Deletes all cached files.
        architectView.onDestroy(); // Mandatory ArchitectView lifecycle call
    }
}