package trinityconnect.android.bignerdranch.com.trinityconnect;

import java.util.UUID;

/**
 * Created by owner on 11/15/2015.
 */
public class Event {
    private UUID mId;

    private String mTitle;

    public Event() {
        mId = UUID.randomUUID();
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


}
