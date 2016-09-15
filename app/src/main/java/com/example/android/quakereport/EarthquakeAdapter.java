package com.example.android.quakereport;

/**
 * Created by Pogee on 2/08/2016.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //get teh item/object located in this position in teh list
        Earthquake currentEarthquake = getItem(position);

        TextView magTextview = (TextView) listItemView.findViewById(R.id.quake_magnitude);
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magString = formatter.format(currentEarthquake.getMagnitude());

        magTextview.setText(String.valueOf(magString));

        String locString = currentEarthquake.getLocation();
        TextView locTextview = (TextView) listItemView.findViewById(R.id.quake_location);
        TextView locOffsetTextview = (TextView) listItemView.findViewById(R.id.quake_locOffset);

        int locIndex = locString.indexOf(" of ");

        if(locIndex == -1) {
            locOffsetTextview.setText("Near the");
            locTextview.setText(locString);
        }
        else {
            locOffsetTextview.setText(locString.substring(0,locIndex+3));
            locTextview.setText(locString.substring(locIndex +4,locString.length()));
        }


        TextView dateTextview = (TextView) listItemView.findViewById(R.id.quake_date);
        TextView timeTextview = (TextView) listItemView.findViewById(R.id.quake_time);

        Date date = new Date(currentEarthquake.getTimeInMilliseconds());

        String formattedDate = formatDate(date);
        dateTextview.setText(formattedDate);
        String formattedTime = formatTime(date);
        timeTextview.setText(formattedTime);


        return listItemView;
    }

    private String formatDate(Date date) {
        // Format date
        SimpleDateFormat formatter
                = new SimpleDateFormat("MMM d, yyyy ");
        String dateString = formatter.format(date);
        return dateString;
    }

    private String formatTime(Date date) {
        // Format time
        SimpleDateFormat formatter
                = new SimpleDateFormat("h:mm a");
        String timeString = formatter.format(date);
        return timeString;
    }
}

