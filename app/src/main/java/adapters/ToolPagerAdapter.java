package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nffs.performax.BMI;

import days.Friday;
import days.Monday;
import days.Saturday;
import days.Thursday;
import days.Tuesday;
import days.Wednesday;
import fitness.CalorieCalc;
import fitness.WeightTracker;

/**
 * Created by ammonrees on 12/4/14.
 */
public class ToolPagerAdapter extends FragmentPagerAdapter {

    static final int ITEMS = 3;

    private final String[] TITLES = {"Weight Tracker", "BMI Tracker", "Calorie Calc"};


    public ToolPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show image
                return WeightTracker.init(position);
            case 1: // Fragment # 1 - This will show image
                return BMI.init(position);
            case 3: // Fragment # 1 - This will show image
                return CalorieCalc.init(position);
            default:// Fragment # 2-9 - Will show list
                return CalorieCalc.init(position);
        }
    }
}
