package org.fhict.csi_week_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CriminalProvider criminalProvider = new CriminalProvider(this);

        Intent intent = getIntent();
        final int chosenCriminalPosition = intent.getIntExtra("chosenCriminalPosition", 0);
        Criminal criminal = criminalProvider.GetCriminal(chosenCriminalPosition);

        Button button = (Button) findViewById(R.id.Report);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentR = new Intent(getApplicationContext(), ReportActivity.class);
                intentR.putExtra("chosenCriminalPosition", chosenCriminalPosition);
                startActivity(intentR);
            }
        });

        CrimeListAdapter crimeListAdapter = new CrimeListAdapter(this, criminal.crimes);

        ListView listView = (ListView) findViewById(R.id.listViewCrimes);

        listView.setAdapter(crimeListAdapter);

        ImageView mugshot = (ImageView) findViewById(R.id.Mugshot);
        TextView nameBox = (TextView) findViewById(R.id.name);
        TextView genderBox = (TextView) findViewById(R.id.gender);
        TextView ageBox = (TextView) findViewById(R.id.age);
        TextView bountyBox = (TextView) findViewById(R.id.bounty);
        TextView detailsBox = (TextView) findViewById(R.id.details);



        mugshot.setBackground(criminal.mugshot);
        nameBox.setText(criminal.name);
        genderBox.setText(criminal.gender);
        ageBox.setText(Integer.toString(criminal.age));
        bountyBox.setText(Integer.toString(criminal.getBountyInDollars()));
        detailsBox.setText(criminal.description);
    }
}
