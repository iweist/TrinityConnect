package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class EventListFragment extends Fragment {

    private static  final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private RecyclerView mEventRecyclerView;
    private EventAdapter mAdapter;
    private UUID true_id;
    private int index;
    private boolean mSubtitleVisible;
    private Boolean delChange = null;
    private String location;
    private int filter = 0;

    private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDescriptTextView;
        private TextView mLocTextView;
        private TextView mRSVPTextView;
        private TextView mDateTextView;
        private TextView mTimeTextView;
        private Event mEvent;
        private static final int REQUEST_EVENT = 1;

        public EventHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            //Set objects from layout to variables
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_event_title_text_view);
            mDescriptTextView = (TextView) itemView.findViewById(R.id.list_item_event_descript_text_view);
            mLocTextView = (TextView) itemView.findViewById(R.id.list_item_event_loc_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_event_date_text_view);
            mTimeTextView = (TextView) itemView.findViewById(R.id.list_item_event_time_text_view);
            mRSVPTextView = (TextView) itemView.findViewById(R.id.list_item_event_RSVP_text_view);

            //Set text settings
            mTitleTextView.setTextColor(Color.BLUE);
            mTitleTextView.setTextSize(22);
            mTimeTextView.setTextSize(22);
            mRSVPTextView.setTextSize(22);
            mLocTextView.setTextSize(22);
            mDateTextView.setTextSize(22);
            mDescriptTextView.setTextSize(22);


        }

        @Override
        public void onClick(View v){

            Intent intent = EventListPagerActivity.newIntent(getActivity(), mEvent.getId());
            startActivity(intent);
        }

        //Used to place events that will be displayed
        public void bindEvent(Event event){
            mEvent = event;
            mTitleTextView.setText(mEvent.getTitle());
            mDescriptTextView.setText(mEvent.getDescription());
            mLocTextView.setText(mEvent.getLoc());
            mDateTextView.setText("Date: " + DateFormat.format("MM/dd/yyyy", mEvent.getDate()));
            mTimeTextView.setText("Time: " + DateFormat.format("hh:mm a", mEvent.getTime()));
            mRSVPTextView.setText("RSVPs: " + mEvent.getRSVP());

        }

    }

    private class EventAdapter extends RecyclerView.Adapter<EventHolder>{
        private List<Event> mEvents;

        public EventAdapter(List<Event> Events){
            mEvents = Events;
            List<Event> tempEvents = new ArrayList<>();

            //Filters events if a location was chosen on the map
            if(filter == 1){
                for(int i = 0; i < mEvents.size(); i++){

                    if( mEvents.get(i).getLoc().equals(location) ){
                        tempEvents.add(mEvents.get(i));
                    }
                }

                mEvents=tempEvents;
            }
        }


        @Override
        public EventHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_event,parent,false);
            return new EventHolder(view);
        }

        @Override
        public void onBindViewHolder(EventHolder holder, int position){
            Event event = mEvents.get(position);

            holder.bindEvent(event);
        }

        @Override
        public int getItemCount(){

            return mEvents.size();
        }

        public void setEvents(List<Event> Events){
            mEvents = Events;
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        mEventRecyclerView = (RecyclerView) view.findViewById(R.id.event_recycler_view);
        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        //This is a check to see if the EventListFragment is being started from google maps,
        // in which it will turn a switch on to filter the events
        Bundle args = getArguments();
        if(args.getString("trinityconnect.android.bignerdranch.com.trinityconnect.bundlelocation") != null){
            location = args.getString("trinityconnect.android.bignerdranch.com.trinityconnect.bundlelocation");
            filter = 1;
            Log.i("EventListFragment", location);
        }

        if(savedInstanceState != null){
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);

        }

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_event_list, menu);

    }

    private void updateUI(){
        final EventLab eventLab = EventLab.get(getActivity());
        eventLab.updateEvent(new AfterCallBack() {
            @Override
            public void done(List<Event> events) {
                List<Event> Events = eventLab.getEvents();
                List<Event> tempEvents = new ArrayList<>();

                //If the filter is on, filter the events based on the location
                if (filter == 1) {
                    for (int i = 0; i < Events.size(); i++) {

                        if (Events.get(i).getLoc().equals(location)) {
                            tempEvents.add(Events.get(i));
                            Log.i("In loop", Events.get(i).getLoc());
                        }
                    }

                    Events = tempEvents;
                }

                if (mAdapter == null) {
                    mAdapter = new EventAdapter(Events);
                    mEventRecyclerView.setAdapter(mAdapter);
                    delChange = true;
                } else {
                    for (int i = 0; i < Events.size(); i++) {
                        if (Events.get(i).getId() == true_id) {
                            index = i;
                            delChange = false;
                        }
                    }
                    mAdapter.setEvents(Events);
                    mAdapter.notifyItemChanged(index);

                }


                if (delChange == null)
                    mAdapter.notifyDataSetChanged();
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_item_new_event:

                Event event = new Event();
                EventLab.get(getActivity()).addEvent(event);
                Intent intent = EventPagerActivity.newIntent(getActivity(), event.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_my_events:

                Intent intent1 = MapsActivity.newIntent(getActivity());
                startActivity(intent1);
                return true;
            case R.id.menu_item_refresh:

                updateUI();
            default:

                return super.onOptionsItemSelected(item);
        }
    }

}
