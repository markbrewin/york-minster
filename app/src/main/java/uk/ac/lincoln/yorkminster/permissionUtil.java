package uk.ac.lincoln.yorkminster;


import android.Manifest;

import com.wikitude.architect.ArchitectStartupConfiguration;

public class permissionUtil {
    private permissionUtil(){}

    public static String[] getPermissionsForArFeatures() {
        return (new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION});
    }
}
