package org.fhict.csi_week_1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jasper on 23-9-2016.
 */
@SuppressLint("InflateParams")
public class CrimeListAdapter extends ArrayAdapter<Crime> {

    private Context context;
    private List<Crime> crimes;

    public CrimeListAdapter(Context context, List<Crime> crimes) {
        super(context, R.layout.crimelistitem, crimes);

        this.context = context;
        this.crimes = crimes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Crime requestedCrime = crimes.get(position);

        //TODO: replace this simple view by the layout as defined in criminallistitem.xml"

        View crimeView = convertView;

        if (crimeView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            crimeView = inflater.inflate(R.layout.crimelistitem, null);
        }

        TextView name = (TextView) crimeView.findViewById(R.id.CLName);
        TextView bounty = (TextView) crimeView.findViewById(R.id.CLBounty);
        TextView description = (TextView) crimeView.findViewById(R.id.CLDescription);
        name.setText("Name: " + requestedCrime.name);
        bounty.setText("Bounty: " + requestedCrime.bountyInDollars);
        description.setText(requestedCrime.description);

        return crimeView;
    }
}
