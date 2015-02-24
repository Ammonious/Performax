package com.nffs.performax;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import net.qiujuer.genius.widget.GeniusEditText;

import org.eazegraph.lib.charts.ValueLineChart;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class BMI extends Fragment {



    FloatingActionButton calculate;
    GeniusEditText weight, inches, feet;
    public static int weightNum,heightNum,bmiNum;
    TextView bmiCondition;
    Context mContext;
    int Results;

    int fragVal;

    public static BMI init(int val) {
        BMI truitonFrag = new BMI();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonFrag.setArguments(args);
        return truitonFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_bmi, container, false);



        weight = (GeniusEditText) rootView.findViewById(R.id.enter_weight);
        inches = (GeniusEditText) rootView.findViewById(R.id.enter_inches);
        feet = (GeniusEditText) rootView.findViewById(R.id.enter_feet);








        calculate = (FloatingActionButton) rootView.findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(weight.getText().toString() == null || inches.getText().toString() == null || feet.getText().toString() == null) {

                    Toast.makeText(getActivity(), "Missing Field", Toast.LENGTH_LONG).show();

                } else {

                    int inchValue = Integer.parseInt(inches.getText().toString());
                    int feetValue = Integer.parseInt(feet.getText().toString());
                    int weightValue = Integer.parseInt(weight.getText().toString());

                    int finalHeight = (feetValue * 12) + inchValue;


                Results = (weightValue * 703) / (finalHeight * finalHeight);


                    int[] startingLocation = new int[2];
                    calculate.getLocationOnScreen(startingLocation);
                    startingLocation[0] += calculate.getWidth() / 2;
                    BmiResults.startBMIFromLocation(startingLocation, getActivity(), Results);
                    getActivity().overridePendingTransition(0, 0);


            }
        }
        });


        return rootView;
    }




}


