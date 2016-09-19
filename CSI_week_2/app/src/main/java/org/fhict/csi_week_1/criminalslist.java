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

        ListView listView = (ListView) findViewById(R.id.lisViewCriminals);
        final String[] criminals = getResources().getStringArray(R.array.names);

        listView.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, criminals
                )
            );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = criminals[position];

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
