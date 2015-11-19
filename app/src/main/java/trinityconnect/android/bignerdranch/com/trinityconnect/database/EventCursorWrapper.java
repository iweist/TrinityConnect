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
        String location = getString(getColumnIndex(EventDbSchema.EventTable.Cols.LOCATION));
        long date = getLong(getColumnIndex(EventDbSchema.EventTable.Cols.DATE));

        Event event = new Event(UUID.fromString(uuidString));
        event.setTitle(title);
        event.setDescription(descript);
        event.setLocation(location);
        event.setDate(new Date(date));

        return event;
    }
}
