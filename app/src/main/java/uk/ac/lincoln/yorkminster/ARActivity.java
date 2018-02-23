package uk.ac.lincoln.yorkminster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ARActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        this.architectView = (ArchitectView)this.findViewById( R.id.architectView );
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey( * license key */ );
        this.architectView.onCreate( config );

        this.architectView.onPostCreate();
        this.architectView.load( "minsterAR.html" );
    }
}
