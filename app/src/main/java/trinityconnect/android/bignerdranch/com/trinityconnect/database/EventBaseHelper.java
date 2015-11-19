package trinityconnect.android.bignerdranch.com.trinityconnect.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adam on 11/16/2015.
 */
public class EventBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "eventBase.db";

    public EventBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + EventDbSchema.EventTable.NAME + "(" + "_id integer primary key autoincrement, " + EventDbSchema.EventTable.Cols.UUID+ ", " +
                        EventDbSchema.EventTable.Cols.TITLE + ", " +
                        EventDbSchema.EventTable.Cols.DESCRIPT + ", " +
                        EventDbSchema.EventTable.Cols.LOCATION + ", " +
                        EventDbSchema.EventTable.Cols.DATE + ") "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
