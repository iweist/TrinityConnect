package trinityconnect.android.bignerdranch.com.trinityconnect.model;

import com.parse.ParseObject;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

/**
 * Created by owner on 11/15/2015.
 */
public class Event extends ParseObject {

    private UUID mId;
    private String mTitle;
    private String mDescription;
    private String mLoc;
    private Date mDate;
    private Date mTime;
    private int mRSVP;

    public Event() {
        //generate unique id
        this(UUID.randomUUID());
    }
    //this.getObjectID();
    public Event(UUID id) {
        put("UUID", id.toString());
        put("RSVP", 0);
        mDate = new Date();
        mTime = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return getString("title");
    }
    public void setTitle(String title) {
        put("title", title);
    }

    public String getDescription() {
        return getString("description");
    }
    public void setDescription(String description) {
        put("description", description);
    }

    public Date getDate() {
        return getDate("date");
    }
    public void setDate(Date date) {
        put("date", date);
    }

    public Date getTime() {
        return getDate("time");
    }
    public void setTime(Date time) {
        put("time", time);
    }

    public int getRSVP() {
        return getInt("RSVP");
    }

    public void incrementRSVP() {
        put("RSVP", getInt("RSVP")+1);
    }

    public String getLoc() {
        return getString("loc");
    }
    public void setLoc(String loc) {
        put("loc", loc);
    }

}

