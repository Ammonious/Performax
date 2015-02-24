package adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.RoundedImageView;
import com.nffs.performax.EventDescription;
import com.nffs.performax.MainActivity;
import com.nffs.performax.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import parse.ScheduleItems;

/**
 * Created by ammonrees on 9/24/14.
 */
public class ScheduleAdapter extends ArrayAdapter<ScheduleItems> {
    private Context mContext;
    private List<ScheduleItems> mEvent,mTime,mInstructor,mUrl,mDescription;

    ViewHolder viewHolder;

    public ScheduleAdapter(Context context, List<ScheduleItems> objects) {
        super(context, R.layout.schedule_row, objects);
        this.mContext = context;
        this.mEvent = objects;
        this.mTime = objects;
        this.mInstructor = objects;
        this.mUrl = objects;
        this.mDescription = objects;





    }



    private class ViewHolder
    {

        TextView Event,Time,Instructor;
        RoundedImageView InstructorPic;
        RelativeLayout description;

    }

    public View getView(final int position, View convertView, ViewGroup parent){

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Thin.ttf");
        //  Typeface tf2 = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoSlab-Bold.ttf");
        Typeface tf3 = Typeface.createFromAsset(getContext().getAssets(), "fonts/RobotoSlab-Regular.ttf");


        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
            convertView = mLayoutInflater.inflate(R.layout.schedule_row, null);
            viewHolder = new ViewHolder();
            viewHolder.Event = (TextView) convertView.findViewById(R.id.event);
            viewHolder.Time = (TextView) convertView.findViewById(R.id.day);
            viewHolder.Instructor = (TextView) convertView.findViewById(R.id.instructor);
            viewHolder.InstructorPic = (RoundedImageView) convertView.findViewById(R.id.instructor_pic);
            viewHolder.description = (RelativeLayout) convertView.findViewById(R.id.description);


            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();


        }

        final ScheduleItems parseEvent = mEvent.get(position);
        final ScheduleItems parseTime = mTime.get(position);
        final ScheduleItems parseInstructor = mInstructor.get(position);
        final ScheduleItems parseUrl = mUrl.get(position);
        final ScheduleItems parseDesc = mDescription.get(position);



        TextView eventView = (TextView) convertView.findViewById(R.id.event);
        eventView.setText(parseEvent.getEvent());
        eventView.setTypeface(tf3);

        TextView timeView = (TextView) convertView.findViewById(R.id.day);
        timeView.setText(parseTime.getTime());
        timeView.setTypeface(tf);

        TextView InstructorView = (TextView) convertView.findViewById(R.id.instructor);
        InstructorView.setText(parseInstructor.getInstructor());
        InstructorView.setTypeface(tf3);

        Picasso.with(getContext()).load(parseUrl.getInsUrl()).into(viewHolder.InstructorPic);

        // Handle any needed button clicks
        viewHolder.description.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String url = parseUrl.getInsUrl();
                String instructorName = parseInstructor.getInstructor();
                String eventName = parseEvent.getEvent();
                String descriptionInfo = parseDesc.getDescription();
                EventDescription.launch(((MainActivity) getContext()), v.findViewById(R.id.instructor_pic), url, instructorName,eventName,descriptionInfo);



            }

        });

        return convertView;
    }
}