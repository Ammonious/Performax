package com.nffs.performax;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import utils.BaseActivity;
import views.RevealBackgroundView;


public class CalorieResults extends BaseActivity implements RevealBackgroundView.OnStateChangeListener {

    RevealBackgroundView vRevealBackground;
    LinearLayout rootView;
    RelativeLayout vUpperPanel;
    TextView lose_calorie,man_calorie,gain_calorie;
    ImageView imageView3,imageView4,imageView5,nitro,stamina,revive,hyper;
    Context mContext;
    private static final Interpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";
    public static final String ARG_CALCULATION_RESULTS = "calcResults";
    private boolean pendingIntro;
    private int currentState;

    public static void startFromLocation(int[] startingLocation, Activity startingActivity, double Results) {
        Intent intent = new Intent(startingActivity, CalorieResults.class);
        intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation);
        intent.putExtra(ARG_CALCULATION_RESULTS, Results);
        startingActivity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        nitro = (ImageView) findViewById(R.id.nitro);
        stamina = (ImageView) findViewById(R.id.stamina);
        revive = (ImageView) findViewById(R.id.revive);
        hyper = (ImageView) findViewById(R.id.hyper);
        lose_calorie = (TextView) findViewById(R.id.lose_calories);
        man_calorie = (TextView) findViewById(R.id.man_calories);
        gain_calorie = (TextView) findViewById(R.id.gain_calories);

        double preCalcValue = getIntent().getDoubleExtra(ARG_CALCULATION_RESULTS,0);
        double calorieValue = Math.round(preCalcValue);

        lose_calorie.setText((calorieValue - 500) + " to " + (calorieValue - 300));
        man_calorie.setText(calorieValue + " to " + (calorieValue + 200));
        gain_calorie.setText((calorieValue + 500) + " to " + (calorieValue + 700));

        rootView = (LinearLayout) findViewById(R.id.root_view);
        vRevealBackground = (RevealBackgroundView) findViewById(R.id.vRevealBackground);
        vUpperPanel = (RelativeLayout) findViewById(R.id.vUpperPanel);


        Picasso.with(mContext).load(R.drawable.carb_blockers).into(imageView4);
        Picasso.with(mContext).load(R.drawable.ultra_pro).into(imageView5);
        Picasso.with(mContext).load(R.drawable.hcg).into(imageView3);
        Picasso.with(mContext).load(R.drawable.stamina).into(stamina);
        Picasso.with(mContext).load(R.drawable.revive).into(revive);
        Picasso.with(mContext).load(R.drawable.hyper).into(hyper);
        Picasso.with(mContext).load(R.drawable.nitro_amp).into(nitro);

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
        return R.layout.fragment_calorie_results;
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
