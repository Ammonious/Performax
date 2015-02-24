package com.nffs.performax;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import mehdi.sakout.fancybuttons.FancyButton;
import utils.BaseActivity;
import utils.TinyDB;


public class BarcodeActivity extends BaseActivity {



    TextView barcode,instruction1,instruction2,widgetText;
    EditText enterNum;
    FancyButton generate;
    ImageView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/fre3of9x.ttf");

        final TinyDB tinydb = new TinyDB(this);
        instruction1 = (TextView) findViewById(R.id.instructions);
        instruction1.setVisibility(View.VISIBLE);
        instruction2 = (TextView) findViewById(R.id.instructions2);
        instruction2.setVisibility(View.VISIBLE);
        widgetText = (TextView) findViewById(R.id.widget_text);
        enterNum = (EditText) findViewById(R.id.enter_barcode);
        barcodeView = (ImageView) findViewById(R.id.barcode_view);
        barcodeView.setVisibility(View.VISIBLE);
        barcode = (TextView) findViewById(R.id.barcode);
        barcode.setTypeface(tf);
        barcode.setText(tinydb.getString("barcode"));
        String barcodeURI = barcode.getText().toString();

        if(barcodeURI != "" ){

            barcodeView.setImageResource(R.drawable.preview);
            widgetText.setText("Widget also available");
            instruction1.setVisibility(View.INVISIBLE);
            instruction2.setVisibility(View.INVISIBLE);
        }


        generate = (FancyButton) findViewById(R.id.generate_barcode);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinydb.putString("barcode", "*"+enterNum.getText().toString()+"*");

                barcode.setText(tinydb.getString("barcode"));
                barcodeView.setImageResource(R.drawable.preview);
                widgetText.setText("Widget also available");
                instruction1.setVisibility(View.INVISIBLE);
                instruction2.setVisibility(View.INVISIBLE);
            }
        });



    }





    @Override
    protected int getLayoutResource() {
        return R.layout.activity_barcode;
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

            case R.id.settings:
                // Intent nextScreen4 = new Intent(getApplicationContext(), SettingsActivity.class);
                // startActivity(nextScreen4);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }




}