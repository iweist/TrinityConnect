package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.support.v4.app.Fragment;

/**
 * Created by Adam on 11/16/2015.
 */
public class EventListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new EventListFragment();
    }


}
