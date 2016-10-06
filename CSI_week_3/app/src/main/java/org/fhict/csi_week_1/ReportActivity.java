package org.fhict.csi_week_1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ReportActivity extends AppCompatActivity {

    private static final byte LOCATION_PERMISSION = 0;
    private final String GPS_PROVIDER = "GPS_PROVIDER";

    LocationManager LM;
    LocationListener LL;
    Location location;
    Vibrator vibrator;
    boolean SCAON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        LM = (LocationManager) getSystemService(LOCATION_SERVICE);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        SCAON = true;

        CriminalProvider criminalProvider = new CriminalProvider(this);

        Intent intent = getIntent();
        final int chosenCriminalPosition = intent.getIntExtra("chosenCriminalPosition", 0);
        Criminal criminal = criminalProvider.GetCriminal(chosenCriminalPosition);

        Button button = (Button) findViewById(R.id.Back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buttonSCAON = (Button) findViewById(R.id.SCAON);
        buttonSCAON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SCAON = true;
            }
        });

        Button buttonSCAOFF = (Button) findViewById(R.id.SCAOFF);
        buttonSCAOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SCAON = false;
                    LM.removeUpdates(LL);
                }
                catch (SecurityException ex) { System.out.println(ex.getMessage()); }
            }
        });

        ImageView mugshot = (ImageView) findViewById(R.id.suspect);
        mugshot.setBackground(criminal.mugshot);

        new Thread(new Runnable() {
            @Override
            public void run() {
                vibrator.vibrate(new long[] {0, 500, 200, 500, 200, 500}, 3);
            }
        }).start();

        if (SCAON)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
            }
        } else {
                location = LM.getLastKnownLocation(GPS_PROVIDER);
                LM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, LL = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        updateLocationDistance(location);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });
            }
        }
    }

    public void updateLocationDistance(Location newLocation) {
        try
        {
            float meters = location.distanceTo(newLocation);

            if (meters < 100)
            {
                vibrator.vibrate(new long[] {20, 50, 100, 200, 40, 100}, 2);
            }
        }
        catch (SecurityException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void onPause() {
        try {
            LM.removeUpdates(LL);
        }
        catch (SecurityException ex) { System.out.println(ex.getMessage()); }
    }
}
