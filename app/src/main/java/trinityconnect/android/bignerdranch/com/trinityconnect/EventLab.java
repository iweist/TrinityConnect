package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class EventLab {
    private static EventLab sEventLab;
    private Context mContext;

    private List<Event> mEvents;

    private EventLab(Context context){
        mContext = context;
        mEvents = new ArrayList<>();
    }

    //--Add event to parse
    public void addEvent(Event c){

        mEvents.add(c);
    }

    //--Get event from parse
    public static EventLab get(Context context){
        if(sEventLab == null){
            sEventLab = new EventLab(context);
        }

        return sEventLab;
    }

    //--Returns a list of events
    public List<Event> getEvents(){

        return mEvents;
    }

    //--Gets a specific event based on UUID
    public Event getEvent(UUID id){

        for (Event event : mEvents){
            if(event.getId().equals(id)){
                return event;
            }
        }
        return null;
    }

    //--Retrieves all events from parse
    public void updateEvent(final AfterCallBack callBack){
       ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");

        //Wait until all objects have been pulled from parse before updating the list of events
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> eventList, ParseException e) {
                if (e == null) {

                    mEvents.clear();

                    for (ParseObject event : eventList) {

                        mEvents.add((Event) event);
                    }

                    if(callBack != null){

                        callBack.done(mEvents);
                    }


                } else {
                    Log.d("Post retrieval", "Error: " + e.getMessage());
                }
            }

        });
    }

}
