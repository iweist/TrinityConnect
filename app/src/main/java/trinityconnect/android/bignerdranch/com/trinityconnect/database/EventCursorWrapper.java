package trinityconnect.android.bignerdranch.com.trinityconnect.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import trinityconnect.android.bignerdranch.com.trinityconnect.Event;

/**
 * Created by Adam on 11/16/2015.
 */
public class EventCursorWrapper extends CursorWrapper {
    public EventCursorWrapper (Cursor cursor){
        super(cursor);
    }

    public Event getEvent(){
        String uuidString = getString(getColumnIndex(EventDbSchema.EventTable.Cols.UUID));
        String title = getString(getColumnIndex(EventDbSchema.EventTable.Cols.TITLE));
        String descript = getString(getColumnIndex(EventDbSchema.EventTable.Cols.DESCRIPT));
        String loc = getString(getColumnIndex(EventDbSchema.EventTable.Cols.LOC));
        long date = getLong(getColumnIndex(EventDbSchema.EventTable.Cols.DATE));
        int rsvps = getInt(getColumnIndex(EventDbSchema.EventTable.Cols.RSVP));

        Event event = new Event(UUID.fromString(uuidString));
        event.setTitle(title);
        event.setDescription(descript);
        event.setLoc(loc);
        event.setDate(new Date(date));
        event.setRSVP(rsvps);

        return event;
    }
}
