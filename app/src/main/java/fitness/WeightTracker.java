package fitness;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.nffs.performax.R;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import mehdi.sakout.fancybuttons.FancyButton;
import utils.TinyDB;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class WeightTracker extends Fragment {


    int fragVal;
    FancyButton Submit;
    EditText weight;
    List<ValueLineChart> mValues = new ArrayList<ValueLineChart>();

    public static WeightTracker init(int val) {
        WeightTracker truitonFrag = new WeightTracker();
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
        final View rootView = inflater.inflate(R.layout.fragment_weight, container, false);


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));

        final String mCurrentdate = dateFormat.format(cal.getTime());


        TinyDB tinyDB = new TinyDB(getActivity());
     //   tinyDB.putList("" , valsComp1);


        ValueLineChart mCubicValueLineChart = (ValueLineChart) rootView.findViewById(R.id.weight_chart);

        final ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);
      //  series.s
       // series.setSeries(mValues);
       //  series.
       //    series.setSeries();



        series.addPoint(new ValueLinePoint("Jan", 2.4f));
        series.addPoint(new ValueLinePoint("Feb", 3.4f));
        series.addPoint(new ValueLinePoint("Mar", .4f));
        series.addPoint(new ValueLinePoint("Apr", 1.2f));
        series.addPoint(new ValueLinePoint("Mai", 2.6f));
        series.addPoint(new ValueLinePoint("Jun", 1.0f));
        series.addPoint(new ValueLinePoint("Jul", 3.5f));
        series.addPoint(new ValueLinePoint("Aug", 2.4f));
        series.addPoint(new ValueLinePoint("Sep", 2.4f));
        series.addPoint(new ValueLinePoint("Oct", 3.4f));
        series.addPoint(new ValueLinePoint("Nov", .4f));
        series.addPoint(new ValueLinePoint("Dec", 1.3f));

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();


        weight = (EditText) rootView.findViewById(R.id.enter_weight);

        Submit = (FancyButton) rootView.findViewById(R.id.submit_weight);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                series.addPoint(new ValueLinePoint(mCurrentdate, 2.4f));

            }
        });



        return rootView;
    }

}