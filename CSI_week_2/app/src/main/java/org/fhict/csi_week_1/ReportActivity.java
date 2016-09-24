package org.fhict.csi_week_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ReportActivity extends AppCompatActivity {

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
    }
}
