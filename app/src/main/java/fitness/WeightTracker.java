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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import mehdi.sakout.fancybuttons.FancyButton;
import utils.TinyDB;
import utils.Weight;
import utils.WeightDBHelper;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class WeightTracker extends Fragment {

    private WeightDBHelper db;
    int fragVal;
    FancyButton Submit;
    EditText weight;
 //   List<ValueLineChart> mValues = new ArrayList<ValueLineChart>();

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
        db = new WeightDBHelper(getActivity().getApplicationContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_weight, container, false);

        ValueLineChart mCubicValueLineChart = (ValueLineChart) rootView.findViewById(R.id.weight_chart);

        final ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);

        List<Weight> weights = db.getAllWeights();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        for(Weight w : weights) {
            Date d = new Date(w.dateInMilliseconds);
            series.addPoint(new ValueLinePoint(dateFormat.format(d), w.weight));
        }

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();


        weight = (EditText) rootView.findViewById(R.id.enter_weight);

        Submit = (FancyButton) rootView.findViewById(R.id.submit_weight);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //series.addPoint(new ValueLinePoint(mCurrentdate, 2.4f));

            }
        });



        return rootView;
    }

}