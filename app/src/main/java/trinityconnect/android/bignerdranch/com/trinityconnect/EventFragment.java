package trinityconnect.android.bignerdranch.com.trinityconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.UUID;


/**
 * Created by owner on 11/15/2015.
 */
public class EventFragment extends Fragment {
    private static final String ARG_EVENT_ID = "event_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private Event mEvent;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private Button mDateButton;
    private Button mTimeButton;
    private TextView mDateText;
    private TextView mTimeText;
    private Button mCompleteButton;


    public static EventFragment newInstance(UUID eventId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_EVENT_ID, eventId);
        EventFragment fragment = new EventFragment();
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
        View v = inflater.inflate(R.layout.fragment_event, container, false);

        mDateText= (TextView) v.findViewById(R.id.event_date_picked);
        mDateText.setText("");
        mTimeText= (TextView) v.findViewById(R.id.event_time_picked);
        mTimeText.setText("");

        mTitleField = (EditText) v.findViewById(R.id.event_title);
        mTitleField.setText(mEvent.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //intentional blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEvent.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this one too
            }
        });

        mDescriptionField = (EditText) v.findViewById(R.id.event_description);
        mDescriptionField.setText(mEvent.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //intentional blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEvent.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this one too
            }
        });

        Spinner staticSpinner = (Spinner) v.findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this.getActivity(), R.array.loc_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
        staticSpinner.setSelection(0);
        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        mEvent.setLoc("AD");
                        break;
                    case 2:
                        mEvent.setLoc("Austin Arts");
                        break;
                    case 3:
                        mEvent.setLoc("Baseball Field");
                        break;
                    case 4:
                        mEvent.setLoc("The Bistro");
                        break;
                    case 5:
                        mEvent.setLoc("Chapel");
                        break;
                    case 6:
                        mEvent.setLoc("Cinestudio");
                        break;
                    case 7:
                        mEvent.setLoc("Elton");
                        break;
                    case 8:
                        mEvent.setLoc("Ferris");
                        break;
                    case 9:
                        mEvent.setLoc("Funston");
                        break;
                    case 10:
                        mEvent.setLoc("The Hall");
                        break;
                    case 11:
                        mEvent.setLoc("Hamlin");
                        break;
                    case 12:
                        mEvent.setLoc("Jackson");
                        break;
                    case 13:
                        mEvent.setLoc("Jones");
                        break;
                    case 14:
                        mEvent.setLoc("Lacrosse Field");
                        break;
                    case 15:
                        mEvent.setLoc("LSC");
                        break;
                    case 16:
                        mEvent.setLoc("LSC Quad");
                        break;
                    case 17:
                        mEvent.setLoc("Main Quad");
                        break;
                    case 18:
                        mEvent.setLoc("Mather");
                        break;
                    case 19:
                        mEvent.setLoc("McCook");
                        break;
                    case 20:
                        mEvent.setLoc("MECC");
                        break;
                    case 21:
                        mEvent.setLoc("Miller Field");
                        break;
                    case 22:
                        mEvent.setLoc("North");
                        break;
                    case 23:
                        mEvent.setLoc("Psi Upsilon");
                        break;
                    case 24:
                        mEvent.setLoc("Raether Library");
                        break;
                    case 25:
                        mEvent.setLoc("Sheppard Field");
                        break;
                    case 26:
                        mEvent.setLoc("Smith");
                        break;
                    case 27:
                        mEvent.setLoc("Summit East");
                        break;
                    case 28:
                        mEvent.setLoc("Summit North");
                        break;
                    case 29:
                        mEvent.setLoc("Summit South");
                        break;
                    case 30:
                        mEvent.setLoc("Vernon Social");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        mDateButton = (Button) v.findViewById(R.id.event_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mEvent.getDate());
                dialog.setTargetFragment(EventFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);

            }
        });


        mTimeButton = (Button) v.findViewById(R.id.event_time);
        updateTime();
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mEvent.getTime());
                dialog.setTargetFragment(EventFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);

            }
        });



        mCompleteButton = (Button) v.findViewById(R.id.complete_event);
        mCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }

        });


        mTimeText.setTextSize(22);
        mDateText.setTextSize(22);
        mDescriptionField.setTextSize(22);
        mCompleteButton.setTextSize(15);
        mDateButton.setTextSize(15);
        mTimeButton.setTextSize(15);
        mTitleField.setTextSize(22);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mEvent.setDate(date);
            mDateText.setText(DateFormat.format("MM/dd/yyyy", date));
            updateDate();
        } else if (requestCode == REQUEST_TIME) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mEvent.setTime(date);

            mTimeText.setText(DateFormat.format("hh:mm a", date));
            updateTime();
        }

    }

    private void updateDate() {
        mDateButton.setText("Pick A Date!");
    }

    private void updateTime() {
        mTimeButton.setText("Pick A Time!");

    }
}

