package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by owner on 10/8/2015.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();
    /*
    public static final String LOC_TAG = "trinityconnect.android.bignerdranch.com.trinityconnect.bundlelocation";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle args = new Bundle();

        if(intent != null){
            String location = intent.getStringExtra(MapsActivity.EXTRA_LOCATION);
            args.putString(LOC_TAG, location);
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
    }*/
}
