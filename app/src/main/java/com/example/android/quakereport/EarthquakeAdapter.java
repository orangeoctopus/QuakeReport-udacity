package com.example.android.quakereport;

/**
 * Created by Pogee on 2/08/2016.
 */

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;
import android.graphics.drawable.GradientDrawable;

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

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextview.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

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

    private int getMagnitudeColor(double magnitude){
        int magnitudeColor;
        int magRound = (int) magnitude; // or (int) Math.floor(magnitude);

        switch (magRound){
            case 0:
            case 1: magnitudeColor = R.color.magnitude1;
                break;
            case 2: magnitudeColor =  R.color.magnitude2;
                break;
            case 3: magnitudeColor =  R.color.magnitude3;
                break;
            case 4: magnitudeColor = R.color.magnitude4;
                break;
            case 5: magnitudeColor =  R.color.magnitude5;
                break;
            case 6: magnitudeColor =  R.color.magnitude6;
                break;
            case 7: magnitudeColor =  R.color.magnitude7;
                break;
            case 8: magnitudeColor =  R.color.magnitude8;
                break;
            case 9: magnitudeColor =  R.color.magnitude9;
                break;
            default: magnitudeColor =  R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColor);
    }
}

