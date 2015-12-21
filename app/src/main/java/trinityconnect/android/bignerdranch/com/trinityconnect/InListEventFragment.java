package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Adam on 11/30/2015.
 */
public class InListEventFragment extends Fragment {
    private static final String ARG_EVENT_ID = "event_id";

    private Event mEvent;
    private TextView mTitleField;
    private TextView mDescriptionField;
    private TextView mDateField;
    private TextView mTimeField;
    private TextView mLocationField;
    private TextView mRSVPField;
    private Button mRSVPButton;



    public static InListEventFragment newInstance(UUID eventId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_EVENT_ID, eventId);
        InListEventFragment fragment = new InListEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID eventId = (UUID) getArguments().getSerializable(ARG_EVENT_ID);
        mEvent = EventLab.get(getActivity()).getEvent(eventId);

    }

    @Override
    public void onPause() {
        super.onPause();
        EventLab.get(getActivity()).updateEvent(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_selected_event, container, false);



        mTitleField = (TextView) v.findViewById(R.id.event_title);
        mTitleField.setText(mEvent.getTitle());

        mDescriptionField = (TextView) v.findViewById(R.id.event_description);
        mDescriptionField.setText(mEvent.getDescription());

        mLocationField = (TextView) v.findViewById(R.id.event_loc);
        mLocationField.setText(mEvent.getLoc());

        mDateField = (TextView) v.findViewById(R.id.event_date);
        mDateField.setText(DateFormat.format("MM/dd/yyyy", mEvent.getDate()));

        mTimeField = (TextView) v.findViewById(R.id.event_time);
        mTimeField.setText(DateFormat.format("hh:mm a", mEvent.getTime()));

        mRSVPField = (TextView) v.findViewById(R.id.event_rsvp);
        mRSVPField.setText(mEvent.getRSVP());


        mRSVPButton = (Button) v.findViewById(R.id.RSVPs);
        mRSVPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = mEvent.getRSVPInt();
                temp++;
                mEvent.setRSVP(temp);
                //mEvent.incrementRSVP();
                mRSVPButton.setClickable(false);
                mRSVPField.setText(mEvent.getRSVP());
            }

        });


        mDateField.setTextSize(22);
        mDescriptionField.setTextSize(22);
        mLocationField.setTextSize(22);
        mRSVPField.setTextSize(22);
        mRSVPButton.setTextSize(22);
        mTimeField.setTextSize(22);
        mTitleField.setTextSize(22);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

    }

}
