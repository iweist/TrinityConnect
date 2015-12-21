package trinityconnect.android.bignerdranch.com.trinityconnect;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;


@ParseClassName("Event")
public class Event extends ParseObject{

    private UUID mId;

    //Sets parameters to default setting encase of user error
    public Event() {
        mId = UUID.randomUUID();
        if(getNumber("RSVPs") == null) {
            setRSVP(0);
        }

        if(getDate("Date") == null) {
            setDate(new Date());
        }

        if(getDate("Time") == null) {
            setTime(new Date());
        }

        if(getString("Location") == null){
            setLoc("Not Specified");
        }

    }


    //Below are get and set methods of all parameters in Event

    public UUID getId() {

        return mId;
    }

    //Title methods
    public String getTitle() {

        return getString("Title");
    }
    public void setTitle(String title) {

        put("Title", title);
        saveInBackground();
    }

    //Description methods
    public String getDescription() {

        return getString("Description");
    }
    public void setDescription(String description) {

        put("Description", description);
        saveInBackground();
    }

    //Date methods
    public Date getDate() {

        return getDate("Date");
    }
    public void setDate(Date date) {

        put("Date", date);
        saveInBackground();
    }

    //Time methods
    public Date getTime() {

        return getDate("Time");
    }
    public void setTime(Date time) {

        put("Time", time);
        saveInBackground();
    }

    //RSVP methods
    public String getRSVP() {

        return getNumber("RSVPs").toString();
    }

    public int getRSVPInt() {

        return (int)getNumber("RSVPs");
    }
    public void incrementRSVP() {

        put("RSVPs", (int)getNumber("RSVPs")+1);
        saveInBackground();
    }
    public void setRSVP(int rsvp){

        put("RSVPs", rsvp);
        saveInBackground();

    }

    //Location methods
    public String getLoc() {

        return getString("Location");
    }
    public void setLoc(String loc) {

        put("Location" , loc);
        saveInBackground();
    }

}

