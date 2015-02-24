package com.nffs.performax;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.makeramen.RoundedImageView;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import parse.ScheduleItems;
import utils.BaseActivity;


public class EventDescription extends BaseActivity {

    public static final String PROFILE_VIEW = "EventDescription:PROFILE";
    public static final String EXTRA_NAME = "EventDescription:Username";
    public static final String EXTRA_EVENT = "EventDescription:Event";
    public static final String EXTRA_DESCRIPTION = "EventDescription:Description";

    RoundedImageView Profile;
    TextView InstructorName,EventName,EventDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ParseObject.registerSubclass(ScheduleItems.class);


        Typeface tf3 = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Regular.ttf");

        Profile = (RoundedImageView) findViewById(R.id.instructorPic);
        ViewCompat.setTransitionName(Profile, PROFILE_VIEW);
        Picasso.with(this).load(getIntent().getStringExtra(PROFILE_VIEW)).into(Profile);

        InstructorName = (TextView) findViewById(R.id.instructor_name);
        InstructorName.setTypeface(tf3);
        InstructorName.setText(getIntent().getExtras().getString(EXTRA_NAME));

        EventName = (TextView) findViewById(R.id.eventName);

        EventName.setTypeface(tf);
        EventName.setText(getIntent().getExtras().getString(EXTRA_EVENT));

        EventDesc = (TextView) findViewById(R.id.eventDesc);
        EventDesc.setTypeface(tf);
        EventDesc.setText(getIntent().getExtras().getString(EXTRA_DESCRIPTION));



    }


    protected int getLayoutResource() {

        return R.layout.activity_event;
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

    public static void launch(BaseActivity activity, View transitionView, String url, String Username, String Event, String Description) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, PROFILE_VIEW);
        Intent intent = new Intent(activity, EventDescription.class);
        intent.putExtra(PROFILE_VIEW, url);
        intent.putExtra(EXTRA_NAME, Username);
        intent.putExtra(EXTRA_EVENT, Event);
        intent.putExtra(EXTRA_DESCRIPTION, Description);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

}
