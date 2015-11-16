package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class EventDisplayActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new EventListFragment();
    }
}
