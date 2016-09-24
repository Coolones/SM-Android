package org.fhict.csi_week_1;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")  // See: https://code.google.com/p/android-developer-preview/issues/detail?id=1203
	public class CriminalListAdapter extends ArrayAdapter<Criminal> {

		private Context context;
		private List<Criminal> criminals;

	public CriminalListAdapter(Context context, List<Criminal> criminals) {
		super(context, R.layout.criminallistitem, criminals);
		
		this.context = context;
		this.criminals = criminals;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Criminal requestedCriminal = criminals.get(position);

		//TODO: replace this simple view by the layout as defined in criminallistitem.xml"

		View criminalView = convertView;

		if (criminalView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			criminalView = inflater.inflate(R.layout.criminallistitem, null);
		}

		ImageView mugshot = (ImageView) criminalView.findViewById(R.id.Mugshot);
		TextView name = (TextView) criminalView.findViewById(R.id.Name);
		TextView tBounty = (TextView) criminalView.findViewById(R.id.TBounty);
		mugshot.setBackground(requestedCriminal.mugshot);
		name.setText("Name: " + requestedCriminal.name);
		tBounty.setText("Total Bounty: " + requestedCriminal.getBountyInDollars());

		return criminalView;
	}
}
