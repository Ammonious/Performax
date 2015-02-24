package parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ammonrees on 9/24/14.
 */
@ParseClassName("Schedule")

public class ScheduleItems extends ParseObject {

    public ScheduleItems(){



    }


    public String getEvent(){
        return getString("Event");
    }
    public void setEvent(String Event){
        put("Event", Event);
    }

    public String getTime(){
        return getString("Time");
    }
    public void setTime(String Time){
        put("Time", Time);
    }

    public String getInstructor(){
        return getString("Instructor");
    }
    public void setInstructor(String Instructor){
        put("Instructor", Instructor);
    }

    public String getInsUrl(){
        return getString("InstructorPic");
    }
    public void setInsUrl(String InstructorPic){
        put("InstructorPic", InstructorPic);
    }

    public String getDescription(){
        return getString("Description");
    }
    public void setDescription(String Description){
        put("Description", Description);
    }

}
