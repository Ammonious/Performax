package fitness;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.nffs.performax.CalorieResults;
import com.nffs.performax.R;
import com.squareup.picasso.Picasso;

import net.qiujuer.genius.widget.GeniusEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalorieCalc extends Fragment {

    public static final String ARG_CALCULATION_RESULTS = "calcResults";
    GeniusEditText enter_age,enter_feet,enter_inches,enter_weight;
    RadioGroup gender,activity_level;
    RadioButton male,female,inactive,somewhat_active,active,very_active;
    int fragVal;
    double maleBMR,femaleBMR;
    double Results;
    FloatingActionButton submit;
    Context mContext;

    public static CalorieCalc init(int val) {
        CalorieCalc truitonFrag = new CalorieCalc();
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
        final View rootView = inflater.inflate(R.layout.fragment_calorie, container, false);

        enter_age = (GeniusEditText) rootView.findViewById(R.id.enter_age);
        enter_feet = (GeniusEditText) rootView.findViewById(R.id.enter_feet);
        enter_inches = (GeniusEditText) rootView.findViewById(R.id.enter_inches);
        enter_weight = (GeniusEditText) rootView.findViewById(R.id.enter_weight);
        gender = (RadioGroup) rootView.findViewById(R.id.gender);
        activity_level = (RadioGroup) rootView.findViewById(R.id.activity_level);
        male = (RadioButton) rootView.findViewById(R.id.male);
        female = (RadioButton) rootView.findViewById(R.id.female);
        inactive = (RadioButton) rootView.findViewById(R.id.inactive);
        somewhat_active = (RadioButton) rootView.findViewById(R.id.somewhat_active);
        active = (RadioButton) rootView.findViewById(R.id.active);
        very_active = (RadioButton) rootView.findViewById(R.id.very_active);



        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.male) {

                        System.out.print("male has been selected");

                } else  {

                        System.out.print("female has been selected");


                }

            }

        });

        activity_level.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.inactive) {

                    System.out.print("Inactive");

                } else if(checkedId == R.id.somewhat_active)  {

                    System.out.print("Somehwat Active");


                } else if(checkedId == R.id.active)  {

                    System.out.print("Active");


                } else if(checkedId == R.id.very_active)  {

                    System.out.print("Very Active");


                }

            }

        });


        submit = (FloatingActionButton) rootView.findViewById(R.id.navigate);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int genderId = gender.getCheckedRadioButtonId();
                int activityId = activity_level.getCheckedRadioButtonId();

                if (genderId == -1 || activityId == -1 || enter_age.getText().toString() == null || enter_inches.getText().toString() == null || enter_feet.getText().toString() == null || enter_weight.getText().toString() == null) {

                    Toast.makeText(getActivity(), "Missing Field", Toast.LENGTH_LONG).show();

                } else {
                    if (genderId == male.getId()) {


                        int age = Integer.parseInt(enter_age.getText().toString());
                        int feet = Integer.parseInt(enter_feet.getText().toString());
                        int inches = Integer.parseInt(enter_inches.getText().toString());
                        int weight = Integer.parseInt(enter_weight.getText().toString());
                        int feetInches = (feet * 12) + inches;

                        double BMR = 66 + (6.23 * weight) + (12.7 * feetInches) - (6.8 * age);

                        maleBMR = BMR;

                        if (activityId == inactive.getId()) {

                            Results = maleBMR * 1.2;


                        } else if (activityId == somewhat_active.getId()) {

                            Results = maleBMR * 1.375;

                        } else if (activityId == active.getId()) {

                            Results = maleBMR * 1.55;

                        } else if (activityId == very_active.getId()) {


                            Results = maleBMR * 1.725;

                        }

                    } else {


                        int age = Integer.parseInt(enter_age.getText().toString());
                        int feet = Integer.parseInt(enter_feet.getText().toString());
                        int inches = Integer.parseInt(enter_inches.getText().toString());
                        int weight = Integer.parseInt(enter_weight.getText().toString());
                        int feetInches = (feet * 12) + inches;


                        double BMR = 655 + (4.35 * weight) + (4.7 * feetInches) - (4.7 * age);

                        femaleBMR = BMR;

                        if (activityId == inactive.getId()) {

                            Results = femaleBMR * 1.2;

                        } else if (activityId == somewhat_active.getId()) {

                            Results = femaleBMR * 1.375;

                        } else if (activityId == active.getId()) {

                            Results = femaleBMR * 1.55;

                        } else if (activityId == very_active.getId()) {

                            Results = femaleBMR * 1.725;

                        }
                    }


                    int[] startingLocation = new int[2];
                    submit.getLocationOnScreen(startingLocation);
                    startingLocation[0] += submit.getWidth() / 2;
                    CalorieResults.startFromLocation(startingLocation, getActivity(), Results);
                    getActivity().overridePendingTransition(0, 0);


                }
            }
        });


            return rootView;
    }

    }
