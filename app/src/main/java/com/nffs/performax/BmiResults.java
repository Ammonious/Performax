package com.nffs.performax;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import mehdi.sakout.fancybuttons.FancyButton;
import utils.BaseActivity;
import views.EmailDialog;
import views.RevealBackgroundView;

/**
 * Created by ammon on 2/17/15.
 *
 *
 *
 */
public class BmiResults extends BaseActivity implements RevealBackgroundView.OnStateChangeListener {

    RevealBackgroundView vRevealBackground;
    LinearLayout rootView;
    FrameLayout vUpperPanel;
    TextView bmi,bmi_condition,bmi_description,bmi_title;
    ImageView biophase;
    Context mContext;
    FancyButton learnHow;
    private static final Interpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";
    public static final String ARG_BMI_RESULTS = "bmiResults";
    private boolean pendingIntro;
    private int currentState;
    int bmiResults;
    public static void startBMIFromLocation(int[] startingLocation, Activity startingActivity, int Results) {
        Intent intent = new Intent(startingActivity, BmiResults.class);
        intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation);
        intent.putExtra(ARG_BMI_RESULTS, Results);
        startingActivity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/RobotoCondensed-Bold.ttf");
        //  Typeface tf2 = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoSlab-Bold.ttf");
        Typeface tf3 = Typeface.createFromAsset(this.getAssets(), "fonts/RobotoSlab-Regular.ttf");
        biophase = (ImageView) findViewById(R.id.biophase);
        bmi = (TextView) findViewById(R.id.bmi);
        bmi_condition = (TextView) findViewById(R.id.bmi_condition);
        bmi_description = (TextView) findViewById(R.id.bmi_description);
        bmi_title = (TextView ) findViewById(R.id.description_title);
        learnHow = (FancyButton) findViewById(R.id.learn_how);

        bmiResults = getIntent().getIntExtra(ARG_BMI_RESULTS,0);

        bmi.setText(String.valueOf(bmiResults));
        bmi.setTypeface(tf);

        bmi_title.setTypeface(tf);
        if (bmiResults <= 18) {

                 bmi_condition.setText("Underweight");
            bmi_description.setText("A lean BMI can indicate that your weight maybe too low. You should consult your physician to determine if you should gain weight, as low body mass can decrease your body's immune system, which could lead to illness such as disappearance of periods (women), bone loss, malnutrition and other conditions. The lower your BMI the greater these risks become.");

        } else if ((bmiResults >= 18) && (bmiResults < 25)) {

                 bmi_condition.setText("Normal");
                 bmi_description.setText("People whose BMI is within 18.5 to 24.9 possess the ideal amount of body weight, associated with living longest, the lowest incidence of serious illness, as well as being perceived as more physically attractive than people with BMI in higher or lower ranges. However, it may be a good idea to check your Waist Circumference and keep it within the recommended limits.");

        } else if ((bmiResults >= 25) && (bmiResults < 30)) {

                bmi_condition.setText("Overweight");
                bmi_description.setText("People falling in this BMI range are considered overweight and would benefit from finding healthy ways to lower their weight, such as diet and exercise. Individuals who fall in this range are at increased risk for a variety of ilnesses. If your BMI is 27-29.99 your risk of health problems becomes higher. In a recent study an increased rate of blood pressure, diabetes and heart disease was recorded at 27.3 for women and 27.8 for men. It may be a good idea to check your Waist Circumference and compare it with the recommended limits.");

        } else if (bmiResults >= 30) {

               bmi_condition.setText("Obese");
               bmi_description.setText("Individuals with a BMI of 30-34.99 are in a physically unhealthy condition, which puts them at risk for serious illnesses such as heart disease, diabetes, high blood pressure, gall bladder disease, and some cancers. This holds especially true if you have a larger than recommended Waist Size. These people would benefit greatly by modifying their lifestyle. Ideally, see your doctor and consider reducing your weight by 5-10 percent. Such a weight reduction will result in considerable health improvements.");
        } else {

            System.out.println("Nothing happens");

            System.out.println("Nothing happens" + bmiResults);


        }


        learnHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentActivity activity = (FragmentActivity)(BmiResults.this);
                FragmentManager fm = activity.getSupportFragmentManager();
                EmailDialog emailDialog = new EmailDialog();
                emailDialog.show(fm, "");



            }
        });

        rootView = (LinearLayout) findViewById(R.id.root_view);
        vRevealBackground = (RevealBackgroundView) findViewById(R.id.vRevealBackground);
        vUpperPanel = (FrameLayout) findViewById(R.id.vUpperPanel);



        Picasso.with(getBaseContext()).load(R.drawable.biophase).into(biophase);


        setupRevealBackground(savedInstanceState);
        vUpperPanel.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                vUpperPanel.getViewTreeObserver().removeOnPreDrawListener(this);
                pendingIntro = true;
                vUpperPanel.setTranslationY(-vUpperPanel.getHeight());

                return true;
            }
        });
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_bmi_results;
    }

    private void setupRevealBackground(Bundle savedInstanceState) {
        vRevealBackground.setFillPaintColor(getResources().getColor(R.color.primary));
        vRevealBackground.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getIntent().getIntArrayExtra(ARG_REVEAL_START_LOCATION);
            vRevealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    vRevealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    vRevealBackground.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            vRevealBackground.setToFinishedFrame();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intentHome = new Intent(this, MainActivity.class);
                intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentHome);
                this.finish();
                return true;


            case R.id.share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.nffs.performax");
                startActivity(Intent.createChooser(shareIntent, "Share app"));
                return true;
            case R.id.settings:
                // Intent nextScreen4 = new Intent(getApplicationContext(), SettingsActivity.class);
                // startActivity(nextScreen4);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {
            rootView.setVisibility(View.VISIBLE);
            if (pendingIntro) {
                startIntroAnimation();
            }
        } else {
            rootView.setVisibility(View.INVISIBLE);
        }
    }

    private void startIntroAnimation() {
        vUpperPanel.animate().translationY(0).setDuration(400).setInterpolator(DECELERATE_INTERPOLATOR);
    }
}