package trinityconnect.android.bignerdranch.com.trinityconnect;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

/**
 * Created by owner on 11/15/2015.
 */
public class Event {

    private UUID mId;
    private String mTitle;
    private String mDescription;
    private Date mDate;
    private Date mTime;


    public Event() {
        //generate unique id
        this(UUID.randomUUID());
    }

    public Event(UUID id) {
        mId = id;
        mDate = new Date();
        mTime = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date time) {
        mTime = time;
    }

}

