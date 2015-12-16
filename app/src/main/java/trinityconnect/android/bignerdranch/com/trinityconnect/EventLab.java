package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Adam on 11/16/2015.
 */
public class EventLab {
    private static EventLab sEventLab;
    private Context mContext;

    private List<Event> mEvents;

    private EventLab(Context context){
        mContext = context;
        mEvents = new ArrayList<>();
    }

    public void addEvent(Event c){

        mEvents.add(c);

    }

    public static EventLab get(Context context){
        if(sEventLab == null){
            sEventLab = new EventLab(context);
        }

        return sEventLab;
    }

    public List<Event> getEvents(){

        return mEvents;
    }

    public Event getEvent(UUID id){

        for (Event event : mEvents){
            if(event.getId().equals(id)){
                return event;
            }
        }
        return null;
    }


    public void updateEvent(Event event){
       ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    mEvents.clear();

                    for(ParseObject event : objects){
                        mEvents.add((Event)event );
                    }
                }
            }
        });

    }


}
