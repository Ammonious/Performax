package utils;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.nffs.performax.R;



/**
 * Created by ammonrees on 11/28/14.
 */
public class UpdateWidgetService extends Service {
    private static final String LOG = "com.nffs.performax";



    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(LOG, "Called");
        // Create some random data
        TinyDB tinyDB = new TinyDB(getApplicationContext());



        Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/fre3of9x.ttf");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());

        int[] allWidgetIds = intent
                .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        ComponentName thisWidget = new ComponentName(getApplicationContext(),
                MyWidgetProvider.class);
        int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
        Log.w(LOG, "From Intent" + String.valueOf(allWidgetIds.length));
        Log.w(LOG, "Direct" + String.valueOf(allWidgetIds2.length));

        for (int widgetId : allWidgetIds) {

            // Create some random data
            Resources res = getResources();
            String Barcode = (String) tinyDB.getString("barcode");


            RemoteViews remoteViews = new RemoteViews(this
                    .getApplicationContext().getPackageName(),
                    R.layout.widget_layout);

            // Set the text
            remoteViews.setImageViewBitmap(R.id.barcode_widget, buildUpdate(Barcode));




            // Register an onClickListener
            Intent clickIntent = new Intent(this.getApplicationContext(),
                    MyWidgetProvider.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                    allWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        stopSelf();

        super.onStart(intent, startId);
    }

    public Bitmap buildUpdate(String barcode)
    {
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.keytag_background).copy(Bitmap.Config.ARGB_8888, true);
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.widget_logo2).copy(Bitmap.Config.ARGB_8888, true);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/fre3of9x.ttf");
        paint.setAntiAlias(true);
        paint.setSubpixelText(true);
        paint.setTypeface(tf);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(400);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawBitmap(logo,450, 400, paint);
        canvas.drawText(barcode, bitmap.getWidth()/2, 950, paint);
        return bitmap;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }
}