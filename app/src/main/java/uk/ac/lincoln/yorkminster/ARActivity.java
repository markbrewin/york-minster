package uk.ac.lincoln.yorkminster;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;

import java.io.IOException;

public class ARActivity extends AppCompatActivity {

    private static final String TAG = ARActivity.class.getSimpleName();

    //Creates the AR environment.
    protected ArchitectView architectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Used to enabled remote debugging of the ArExperience with google chrome https://developers.google.com/web/tools/chrome-devtools/remote-debugging
        WebView.setWebContentsDebuggingEnabled(true);

        //Gets the AR View.
        architectView = new ArchitectView(this);

        //Config for the AR view.
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey("WKu1VfuCZlgTpQ/lOgCI6tTBjOxQHm2nu/GnJ0WZzlcttbYNFEIJ947P6p/DXfmBOgXLGgDv6VW9X+qugzFUSgY8hwNoEFJU9ot2BemF3CYz/HE+bDkcRQQfB27rgH0CdJCnrhWB5x5L/nIWlA6HdSmaTKT4S8hodMgkA8cpesBTYWx0ZWRfX0d9NWwlda/fQfPMStFvzFzWZLJWhb2b+oFZdhQXFyfsuvsmSlLGePKB8txRGfw1eNlylCxY9ImrY7WQmkATDjTEH5qTIjofuIiZmvSKvK2MOLDaKqEoCcWPjj5pp6VBKSb1kFftkmVp8e9onV2Pi/rLD3NrvGXQCN72X83xnkwyi0cP1+8nWeMxlHeb1ZILiWKzBOJoq3Q8cMVgctCkTtPnX0UTdKsxifis292JswjQ7VLnT3m27e+5MbR+licuHAbkm5HF276IlqAMti4BshbDsRzVIqKc1ZF/iAP2wYSbpdnSWsg9s5ajNVjU6v2dgJg64W0Z0aPLvO23NMVvrDq5vQi/buwJzCVEjsV1fuIRgONQo6L5+TUnxq1flyOePxGqRRa7ps+E2J84kEzFeUuFUrfNXDUHQDszL954rKi3N97XPOKfskvncFaOg2/XGikTxHn7nBjIv6inlPKRcfCVKwkh80T/RRM8IYqGh3d9MabeOdauscc=");

        ///Sets the config for AR view.
        architectView.onCreate( config );
        setContentView(architectView);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        architectView.onPostCreate();

        try {
            this.architectView.load( "minster.html" );
        } catch (IOException e) {
            CharSequence arError = "Error loading AR experience.";
            Toast.makeText(this, arError, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Exception while loading arExperience.", e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        architectView.onResume(); // Mandatory ArchitectView lifecycle call
    }

    @Override
    protected void onPause() {
        super.onPause();
        architectView.onPause(); // Mandatory ArchitectView lifecycle call
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        architectView.clearCache(); //Deletes all cached files.
        architectView.onDestroy(); // Mandatory ArchitectView lifecycle call
    }
}
