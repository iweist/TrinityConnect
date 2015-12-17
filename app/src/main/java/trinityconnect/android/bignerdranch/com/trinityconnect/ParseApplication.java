package trinityconnect.android.bignerdranch.com.trinityconnect;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "y0QpHJOj4bXvei2I2P4s8GRxQRXGTVInm2asNxv2", "HA0lDl7dwr0fWdzGZm6VE863j8J8ucWp6wH3wjel");

        ParseObject.registerSubclass(Event.class);

    }

}