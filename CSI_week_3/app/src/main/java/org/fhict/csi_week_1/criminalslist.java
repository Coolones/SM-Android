package org.fhict.csi_week_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class criminalslist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminalslist);

        CriminalProvider criminalProvider = new CriminalProvider(this);
        CriminalListAdapter criminalListAdapter = new CriminalListAdapter(this, criminalProvider.GetCriminals());

        ListView listView = (ListView) findViewById(R.id.listViewCriminals);

        listView.setAdapter(criminalListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.putExtra("chosenCriminalPosition", position);
                startActivity(intent);
            }
        });
    }
}
