package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import days.Friday;
import days.Monday;
import days.Saturday;
import days.Thursday;
import days.Tuesday;
import days.Wednesday;

/**
 * Created by ammonrees on 11/10/14.
 */
public class MyPagerAdapter  extends FragmentPagerAdapter {

    static final int ITEMS = 6;

    private final String[] TITLES = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};


    public MyPagerAdapter(FragmentManager fragmentManager) {
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
                return Monday.init(position);
            case 1: // Fragment # 1 - This will show image
                return Tuesday.init(position);
            case 2: // Fragment # 1 - This will show image
                return Wednesday.init(position);
            case 3: // Fragment # 1 - This will show image
                return Thursday.init(position);
            case 4: // Fragment # 1 - This will show image
                return Friday.init(position);
            case 5: // Fragment # 1 - This will show image
                return Saturday.init(position);
            default:// Fragment # 2-9 - Will show list
                return Monday.init(position);
        }
    }
}




