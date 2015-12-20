package trinityconnect.android.bignerdranch.com.trinityconnect;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

/**
 * Created by owner on 11/15/2015.
 */
@ParseClassName("Event")
public class Event extends ParseObject{

    private UUID mId;
    private String mTitle;
    private String mDescription;
    private String mLoc;
    private Date mDate;
    private Date mTime;
    private int mRSVP;


    public Event() {
        mId = UUID.randomUUID();
        if(getNumber("RSVPs") == null) {
            setRSVP(0);
        }

    }

    public UUID getId() {

        return mId;
    }

    public String getTitle() {

        return getString("Title");
    }
    public void setTitle(String title) {

        put("Title", title);
        saveInBackground();
    }

    public String getDescription() {

        return getString("Description");
    }
    public void setDescription(String description) {

        put("Description", description);
        saveInBackground();
    }

    public Date getDate() {

        return getDate("Date");
    }
    public void setDate(Date date) {

        put("Date", date);
        saveInBackground();
    }

    public Date getTime() {

        return getDate("Time");
    }
    public void setTime(Date time) {

        put("Time", time);
        saveInBackground();
    }

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

    public String getLoc() {

        return getString("Location");
    }
    public void setLoc(String loc) {

        put("Location" , loc);
        saveInBackground();
    }

}

