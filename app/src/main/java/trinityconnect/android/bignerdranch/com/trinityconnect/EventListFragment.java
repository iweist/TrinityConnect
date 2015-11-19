package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import java.util.List;
import java.util.UUID;

/**
 * Created by owner on 11/15/2015.
 */
public class EventListFragment extends Fragment {

    private static  final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private RecyclerView mEventRecyclerView;
    private EventAdapter mAdapter;
    private UUID true_id;
    private int index;
    private boolean mSubtitleVisible;
    private Boolean delChange = null;

    private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDescriptTextView;
        private TextView mDateTextView;
        private TextView mTimeTextView;
        private Event mEvent;
        private static final int REQUEST_EVENT = 1;


        public EventHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_event_title_text_view);
            mDescriptTextView = (TextView) itemView.findViewById(R.id.list_item_event_descript_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_event_date_text_view);
            mTimeTextView = (TextView) itemView.findViewById(R.id.list_item_event_time_text_view);

        }

        @Override
        public void onClick(View v){
            Intent intent = EventPagerActivity.newIntent(getActivity(), mEvent.getId());
            true_id = mEvent.getId();
            startActivity(intent);

        }


        public void bindEvent(Event event){
            mEvent = event;
            mTitleTextView.setText(mEvent.getTitle());
            mDescriptTextView.setText(mEvent.getDescription());
            mDateTextView.setText("Date: " + DateFormat.format("dd/MM/yyyy", mEvent.getDate()));
            mEvent = event;
            mTitleTextView.setText(mEvent.getTitle());
            mTimeTextView.setText("Time: " + DateFormat.format("hh:mm", mEvent.getTime()));

        }

    }

    private class EventAdapter extends RecyclerView.Adapter<EventHolder>{
        private List<Event> mEvents;

        public EventAdapter(List<Event> Events){
            mEvents = Events;
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


    private void updateUI(){
        EventLab eventLab = EventLab.get(getActivity());
        List<Event> Events = eventLab.getEvents();

        if(mAdapter == null) {
            mAdapter = new EventAdapter(Events);
            mEventRecyclerView.setAdapter(mAdapter);
            delChange = true;
        } else {
            for(int i = 0; i < Events.size(); i++){
                if(Events.get(i).getId() == true_id) {
                    index = i;
                    delChange = false;
                }
            }
            mAdapter.setEvents(Events);
            mAdapter.notifyItemChanged(index);

        }


        if(delChange == null)
            mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_event_list, menu);

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
