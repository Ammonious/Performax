package utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.astuetz.PagerSlidingTabStrip;
import com.nffs.performax.R;
import adapters.ToolPagerAdapter;

/**
 * Created by ammonrees on 12/4/14.
 */
public class ToolFragmentHost extends Fragment {


    ToolPagerAdapter mAdapter;
    ViewPager mPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.toolfragment_host, container, false);

        mAdapter = new ToolPagerAdapter(getChildFragmentManager());
        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
        tabs.setViewPager(mPager);

        return rootView;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        System.out.println(" Fragment destroyed");

    }
}
