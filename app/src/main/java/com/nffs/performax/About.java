package com.nffs.performax;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import mehdi.sakout.fancybuttons.FancyButton;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class About extends Fragment {

    FloatingActionButton navigate;
    FancyButton facebook;
    String gymAddress = "Performax Gym, East 1400 South, Clearfield, UT";
    TextView header1,header2,header3,body1,body2,body3,hours,amenities,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_about, container, false);


        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");
        //  Typeface tf2 = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoSlab-Bold.ttf");
        Typeface tf3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/RobotoSlab-Regular.ttf");


        header1 = (TextView) rootView.findViewById(R.id.date_header);
        header1.setTypeface(tf3);
        header2 = (TextView) rootView.findViewById(R.id.date_header2);
        header2.setTypeface(tf3);
        header3 = (TextView) rootView.findViewById(R.id.date_header3);
        header3.setTypeface(tf3);
        body1 = (TextView) rootView.findViewById(R.id.date_body);
        body1.setTypeface(tf);
        body2 = (TextView) rootView.findViewById(R.id.date_body2);
        body2.setTypeface(tf);
        body3 = (TextView) rootView.findViewById(R.id.date_body3);
        body3.setTypeface(tf);
        hours = (TextView) rootView.findViewById(R.id.hours);
        hours.setTypeface(tf3);
        amenities = (TextView) rootView.findViewById(R.id.amenities);
        amenities.setTypeface(tf3);
        txt1 = (TextView) rootView.findViewById(R.id.text1);
        txt1.setTypeface(tf);
        txt2 = (TextView) rootView.findViewById(R.id.text2);
        txt2.setTypeface(tf);
        txt3 = (TextView) rootView.findViewById(R.id.text3);
        txt3.setTypeface(tf);
        txt4 = (TextView) rootView.findViewById(R.id.text4);
        txt4.setTypeface(tf);
        txt5 = (TextView) rootView.findViewById(R.id.text5);
        txt5.setTypeface(tf);


        navigate = (FloatingActionButton) rootView.findViewById(R.id.navigate);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String map = "http://maps.google.co.in/maps?q=" + gymAddress;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                getActivity().startActivity(intent);


            }
        });

        facebook = (FancyButton) rootView.findViewById(R.id.btn_facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fb = "https://www.facebook.com/performaxgyms/timeline";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fb));
                getActivity().startActivity(intent);


            }
        });



        return rootView;
    }


}
