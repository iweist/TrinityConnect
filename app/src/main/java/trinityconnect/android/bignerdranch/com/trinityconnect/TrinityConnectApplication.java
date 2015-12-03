package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

import trinityconnect.android.bignerdranch.com.trinityconnect.model.Event;
import trinityconnect.android.bignerdranch.com.trinityconnect.model.TestObject;

/**
 * Created by owner on 12/1/2015.
 */
public class TrinityConnectApplication extends Application{
    @Override
    public void onCreate(){
        super.onCreate();

        ParseObject.registerSubclass(Event.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "y0QpHJOj4bXvei2I2P4s8GRxQRXGTVInm2asNxv2", "HA0lDl7dwr0fWdzGZm6VE863j8J8ucWp6wH3wjel");


        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
