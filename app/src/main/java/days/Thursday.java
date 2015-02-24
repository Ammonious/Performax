package days;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.nffs.performax.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.quentindommerc.superlistview.SuperListview;

import java.util.ArrayList;
import java.util.List;

import adapters.ScheduleAdapter;
import parse.ScheduleItems;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class Thursday extends Fragment {


    SuperListview mListView;
    ScheduleAdapter mAdapter;
    int fragVal;

    public static Thursday init(int val) {
        Thursday truitonFrag = new Thursday();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonFrag.setArguments(args);
        return truitonFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedules, container, false);


        Parse.initialize(getActivity(), "U692hcbFzi1BKoUrVgSq31AFSB9cFBUVb1rm45Qp", "NiI0ElUMLrL5LFwY53lhAxturzzBYGAhp3NJnZUj");
        ParseObject.registerSubclass(ScheduleItems.class);
        ParseAnalytics.trackAppOpened(getActivity().getIntent());

        mListView = (SuperListview) rootView.findViewById(R.id.scheduleListView);


        mAdapter = new ScheduleAdapter(getActivity(), new ArrayList<ScheduleItems>());




        mListView.setAdapter(mAdapter);

        updateData();
        mAdapter.addAll();

        return rootView;
    }

    public void updateData(){
        String day = "Thursday";
        ParseQuery<ScheduleItems> query = ParseQuery.getQuery(ScheduleItems.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.orderByAscending("order");
        query.whereEqualTo("Day",day);
        query.findInBackground(new FindCallback<ScheduleItems>() {

            @Override
            public void done(List<ScheduleItems> schedules, ParseException error) {
                if(schedules != null){
                    mAdapter.clear();
                    mAdapter.addAll(schedules);
                }
            }
        });
    }



}
