package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import trinityconnect.android.bignerdranch.com.trinityconnect.database.EventBaseHelper;
import trinityconnect.android.bignerdranch.com.trinityconnect.database.EventCursorWrapper;
import trinityconnect.android.bignerdranch.com.trinityconnect.database.EventDbSchema;

/**
 * Created by Adam on 11/16/2015.
 */
public class EventLab {
    private static EventLab sEventLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private EventLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new EventBaseHelper(mContext).getWritableDatabase();

    }

    public void addEvent(Event c){

        ContentValues values = getContentValues(c);

        mDatabase.insert(EventDbSchema.EventTable.NAME, null, values);

    }

    public List<Event> getEvents(){
        List<Event> Events = new ArrayList<>();

        EventCursorWrapper cursor = queryEvents(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Events.add(cursor.getEvent());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return Events;
    }

    public Event getEvent(UUID id){

        EventCursorWrapper cursor = queryEvents(EventDbSchema.EventTable.Cols.UUID + " = ?", new String[]{id.toString()});

        try{
            if(cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getEvent();
        } finally{
            cursor.close();
        }
    }


    public void updateEvent(Event event){
        String uuidString = event.getId().toString();
        ContentValues values = getContentValues(event);

        mDatabase.update(EventDbSchema.EventTable.NAME, values, EventDbSchema.EventTable.Cols.UUID + " = ?", new String[] { uuidString});

    }

    private static ContentValues getContentValues(Event event){
        ContentValues values = new ContentValues();
        values.put(EventDbSchema.EventTable.Cols.UUID, event.getId().toString());
        values.put(EventDbSchema.EventTable.Cols.TITLE, event.getTitle());
        values.put(EventDbSchema.EventTable.Cols.DESCRIPT, event.getDescription());
        values.put(EventDbSchema.EventTable.Cols.LOCATION, event.getLocation());
        values.put(EventDbSchema.EventTable.Cols.DATE, event.getDate().getTime());

        return values;
    }

    private EventCursorWrapper queryEvents(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(EventDbSchema.EventTable.NAME, null, whereClause, whereArgs, null, null, null);

        return new EventCursorWrapper(cursor);
    }

    public static EventLab get(Context context){
        if(sEventLab == null){
            sEventLab = new EventLab(context);
        }
        return sEventLab;
    }

}
