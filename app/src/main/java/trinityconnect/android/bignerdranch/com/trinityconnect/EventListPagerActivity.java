package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;


public class EventListPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Event> mEvents;
    private static final String EXTRA_EVENT_ID = "trinityconnect.android.bignerdranch.event_id";

    public static Intent newIntent(Context packageContext, UUID eventID){
        Intent intent = new Intent(packageContext, EventListPagerActivity.class);
        intent.putExtra(EXTRA_EVENT_ID, eventID);
        return intent;
    }


    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);

        setContentView(R.layout.activity_event_pager);

        UUID eventId = (UUID) getIntent().getSerializableExtra(EXTRA_EVENT_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_event_pager_view_pager);

        mEvents = EventLab.get(this).getEvents();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Event event = mEvents.get(position);
                return InListEventFragment.newInstance(event.getId());
            }

            @Override
            public int getCount() {
                return mEvents.size();
            }
        });

        for(int i = 0; i < mEvents.size(); i++){
            if (mEvents.get(i).getId().equals(eventId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}

