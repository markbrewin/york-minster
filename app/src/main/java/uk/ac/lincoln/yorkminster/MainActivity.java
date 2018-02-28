package uk.ac.lincoln.yorkminster;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.common.permission.PermissionManager;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private final PermissionManager permissionManager = ArchitectView.getPermissionManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean openAR(View view)
    {
        final String[] permissions = permissionUtil.getPermissionsForArFeatures();

        permissionManager.checkPermissions(MainActivity.this, permissions, PermissionManager.WIKITUDE_PERMISSION_REQUEST, new PermissionManager.PermissionManagerCallback() {
            @Override
            public void permissionsGranted(int requestCode) {
                final Intent intent = new Intent(MainActivity.this, ARActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void permissionsDenied(@NonNull String[] deniedPermissions) {
                Toast.makeText(MainActivity.this, "getString(R.string.permissions_denied)" + Arrays.toString(deniedPermissions), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showPermissionRationale(final int requestCode, @NonNull String[] strings) {
                final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("R.string.permission_rationale_title");
                alertBuilder.setMessage("getString(R.string.permission_rationale_text)" + Arrays.toString(permissions));
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        permissionManager.positiveRationaleResult(requestCode, permissions);
                    }
                });

                AlertDialog alert = alertBuilder.create();
                alert.show();
            }
        });
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        ArchitectView.getPermissionManager().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

