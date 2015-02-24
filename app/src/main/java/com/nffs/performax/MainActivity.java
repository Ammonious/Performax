package com.nffs.performax;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import drawer.CustomDrawerAdapter;
import drawer.DrawerItem;
import utils.BaseActivity;
import utils.FragmentHost;
import utils.ToolFragmentHost;


public class MainActivity extends BaseActivity  {


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;
    List<DrawerItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setActionBarIcon(R.drawable.ic_ab_drawer);
        // Initializing
       dataList = new ArrayList<DrawerItem>();
       mTitle = mDrawerTitle = getTitle();
       mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       mDrawerList = (ListView) findViewById(R.id.left_drawer);

       mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);





      /*

         SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        // PreferenceManager.setDefaultValues(getBaseContext(), R.xml.pref_general, false);
        boolean notifications = getPrefs.getBoolean("prefNotification", true);

        if (notifications == true){

            PushService.subscribe(getBaseContext(), "Subscribers", MainActivity.class);
        }
        else{

            PushService.unsubscribe(getBaseContext(), "Subscribers");
        }*/

        // Add Drawer Item to dataList
        dataList.add(new DrawerItem("Schedules & Classes", R.drawable.ic_calander));
        dataList.add(new DrawerItem("Fitness Tools", R.drawable.ic_calc));
        dataList.add(new DrawerItem("Member Portal", R.drawable.ic_national));
        dataList.add(new DrawerItem("About", R.drawable.ic_about));





        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());






        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            SelectItem(0);
        }

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }






    public void SelectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new FragmentHost();

                break;
            case 1:
                fragment = new ToolFragmentHost();

                break;
            case 2:
                fragment = new MemberPortal();

                break;
            case 3:
                fragment = new About();

                break;
           default:
                break;
        }


        FragmentManager frgManager = getSupportFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment)

                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(dataList.get(position).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.barcode_activity:
                 Intent nextScreen4 = new Intent(getApplicationContext(), BarcodeActivity.class);
                 startActivity(nextScreen4);
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




    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position);

        }
    }











}
