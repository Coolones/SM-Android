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

    LocationManager LM = (LocationManager) getSystemService(LOCATION_SERVICE);
    Location location;
    Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

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

        ImageView mugshot = (ImageView) findViewById(R.id.suspect);
        mugshot.setBackground(criminal.mugshot);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
            }
        } else {
            location = LM.getLastKnownLocation(GPS_PROVIDER);
            LM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 25, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    updateLocationDistance();
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

    public void updateLocationDistance() {
        try
        {
            Location newLocation = LM.getLastKnownLocation(GPS_PROVIDER);
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
}
