package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class EventListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new EventListFragment();
    }

    public static final String LOC_TAG = "trinityconnect.android.bignerdranch.com.trinityconnect.bundlelocation";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle args = new Bundle();

        //Used when filtering for certain events by location
        if(intent.getStringExtra("trinityconnect.android.bignerdranch.com.trinityconnect.LOCATION") != null){
            String location = intent.getStringExtra("trinityconnect.android.bignerdranch.com.trinityconnect.LOCATION");
            args.putString(LOC_TAG, location);
            Log.i("EventListActivity", location);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();

            if(intent != null){
                fragment.setArguments(args);
            }

            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }


}
