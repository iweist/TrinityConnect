package trinityconnect.android.bignerdranch.com.trinityconnect.database;

/**
 * Created by Adam on 11/16/2015.
 */
public class EventDbSchema {
    public static final class EventTable {
        public static final String NAME = "events";


        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DESCRIPT = "descript";
            public static final String LOC = "location";
            public static final String DATE = "date";
            public static final String TIME = "time";
            public static final String RSVP = "RSVP";

        }
    }

}