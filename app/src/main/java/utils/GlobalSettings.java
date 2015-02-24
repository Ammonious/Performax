package utils;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;


/**
 * Created by ammonrees on 6/21/14.
 */
public class GlobalSettings extends Application {


    // Debugging switch
    public static final boolean APPDEBUG = false;
    public static String CUSTOM_INTENT = "com.nffs.CUSTOM_INTENT";
    // Debugging tag for the application
    public static final String APPTAG = "Performax";
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        // do the ACRA init here

        //
        Parse.initialize(getApplicationContext(), "U692hcbFzi1BKoUrVgSq31AFSB9cFBUVb1rm45Qp", "NiI0ElUMLrL5LFwY53lhAxturzzBYGAhp3NJnZUj");


    }

}

