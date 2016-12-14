package com.example.kelly.mmelk.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;
import com.example.kelly.mmelk.Utilities;
import com.example.kelly.mmelk.data.ActivitiesContract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuestionsFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,
        Spinner.OnItemSelectedListener{
    private static final String TAG = QuestionsFragment.class.getSimpleName();
    @BindView(R.id.answer_three_spinner)Spinner mAnswerThreeSpinner;
    @BindView(R.id.answer_seven_spinner)Spinner mAnswerSevenSpinner;

    @BindView(R.id.answer_two_number_edittext)EditText mAnswerTwoEditText;
    @BindView(R.id.answer_four_number_edittext)EditText mAnswerFourEditText;
    @BindView(R.id.answer_five_number_edittext)EditText mAnswerFiveEditText;
    @BindView(R.id.answer_six_number_edittext)EditText mAnswerSixEditText;

    @BindView(R.id.answer_two_seekbar)SeekBar mAnswerTwoSeekbar;
    @BindView(R.id.answer_four_seekbar)SeekBar mAnswerFourSeekbar;
    @BindView(R.id.answer_five_seekbar)SeekBar mAnswerFiveSeekbar;
    @BindView(R.id.answer_six_seekbar)SeekBar mAnswerSixSeekbar;

    @BindView(R.id.answer_one_radio_grp)RadioGroup mAnswerOneRadioGroup;
    @BindView(R.id.answer_one_yes_radio)RadioButton mAnswerOneRadioButtonYes;
    @BindView(R.id.answer_one_no_radio)RadioButton mAnswerOneRadioButtonNo;

    private String mAnswerThreeStr;
    private String mAnswerSevenStr;

    public QuestionsFragment(){

    }

    /**
     * set retain instance true
     * and hasOptionsMenu to true
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    /**
     * create the views and configures them
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_questions, container, false);
        ButterKnife.bind(this, rootView);

        populateSpinners(); //populate the spinners from String resources array
        disableEditText(); //disables the edittexts
        addListenersToSeekBars(); //adds listeners to seekBars

        mAnswerThreeSpinner.setOnItemSelectedListener(this);
        mAnswerSevenSpinner.setOnItemSelectedListener(this);

        return rootView;
    }

    /**
     * Populates the spinners
     */
    private void populateSpinners(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> answerThreeAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.feelings, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        answerThreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mAnswerThreeSpinner.setAdapter(answerThreeAdapter);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> answerSevenAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.interaction, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        answerSevenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mAnswerSevenSpinner.setAdapter(answerSevenAdapter);
    }

    /**
     * Disables the editText
     */
    private void disableEditText(){
        mAnswerTwoEditText.setEnabled(false);
        mAnswerFourEditText.setEnabled(false);
        mAnswerFiveEditText.setEnabled(false);
        mAnswerSixEditText.setEnabled(false);
    }

    /**
     * adds listeners to the seekbars
     */
    private void addListenersToSeekBars(){
        //add listeners to seekbars
        mAnswerTwoSeekbar.setOnSeekBarChangeListener(this);
        mAnswerFourSeekbar.setOnSeekBarChangeListener(this);
        mAnswerFiveSeekbar.setOnSeekBarChangeListener(this);
        mAnswerSixSeekbar.setOnSeekBarChangeListener(this);
    }

    /**
     * checks the data before saving it to database
     */
    private void saveToDatabase(){
        SparseArray<String> answers = new SparseArray<String>();

        //answer 1: Yes or No
        int selectedAnswer1 = mAnswerOneRadioGroup.getCheckedRadioButtonId();
        String answer1;
        if (selectedAnswer1 == R.id.answer_one_yes_radio){
            answer1 = getString(R.string.answer_yes);
        } else {
            answer1 = getString(R.string.answer_no);
        }

        answers.put(1, answer1);
        //answer 2
        String answer2 = mAnswerTwoEditText.getText().toString();
        answers.put(2, answer2);
        //answer 3
        if (mAnswerThreeStr.equals(getString(R.string.spinner_hint))){
            Toast.makeText(getActivity(), getString(R.string.spinner_not_selected_error), Toast.LENGTH_SHORT).show();
            return;
        } else {
            answers.put(3, mAnswerThreeStr);
        }

        //answer 4
        String answer4 = mAnswerFourEditText.getText().toString();
        answers.put(4, answer4);

        //answer 5
        String answer5 = mAnswerFiveEditText.getText().toString();
        answers.put(5, answer5);

        //answer 6
        String answer6 = mAnswerSixEditText.getText().toString();
        answers.put(6, answer6);

        //answer 7
        if (mAnswerSevenStr.equals(getString(R.string.spinner_hint))){
            Toast.makeText(getActivity(), getString(R.string.spinner_not_selected_error), Toast.LENGTH_SHORT).show();
            return;
        } else {
            answers.put(7, mAnswerSevenStr);
        }
        addToDatabase(answers);

    }

    /**
     * Once checked, it goes ahead and add data to database
     * @param answers
     */
    private void addToDatabase(SparseArray<String> answers){
        String currentDateTimeStr = Utilities.getCurrentTime();

        //adds Answers to database
        for (int i = 1; i <= answers.size(); i++){
            ContentValues values = new ContentValues();
            values.put(ActivitiesContract.AnswerEntry.COLUMN_QUESTION_ID, i);
            values.put(ActivitiesContract.AnswerEntry.COLUMN_DATE, currentDateTimeStr);
            values.put(ActivitiesContract.AnswerEntry.COLUMN_ANSWER, answers.get(i));
            Log.d(TAG, "addToDatabase: " + values.toString());
            //add to database
            getActivity().getContentResolver().insert(ActivitiesContract.AnswerEntry.CONTENT_URI,
                    values);
        }

        //adds Points to database
        ContentValues pointValues = new ContentValues();
        pointValues.put(ActivitiesContract.PointEntry.COLUMN_POINT, 10);
        pointValues.put(ActivitiesContract.PointEntry.COLUMN_DATE, currentDateTimeStr);
        getActivity().getContentResolver().insert(ActivitiesContract.PointEntry.CONTENT_URI,
                pointValues);
        Log.d(TAG, "addToDatabase: " + pointValues.toString());
        Toast.makeText(getContext(), getString(R.string.ten_earned_points), Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    /**
     * onCreateOptionsMenu
     * @param menu
     * @return true
     * inflates the menu for QuestionActivity
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.question_menu, menu);
    }

    /**
     * onOptionsItemSelected
     * @param item
     * @return the item selected
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.pref_general.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            saveToDatabase();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * SeekBar methods: listens for changes in progress
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == mAnswerTwoSeekbar){
            mAnswerTwoEditText.setText(String.valueOf(progress));
        } else if (seekBar == mAnswerFourSeekbar){
            mAnswerFourEditText.setText(String.valueOf(progress));
        } else if (seekBar == mAnswerFiveSeekbar){
            mAnswerFiveEditText.setText(String.valueOf(progress));
        } else if (seekBar == mAnswerSixSeekbar){
            mAnswerSixEditText.setText(String.valueOf(progress));
        }
    }

    /**
     * Seekbar method: does nothing
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * SeekBar method: does nothing
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * Spinner
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == mAnswerThreeSpinner){
            mAnswerThreeStr = parent.getItemAtPosition(position).toString();
        } else if (parent == mAnswerSevenSpinner){
            mAnswerSevenStr = parent.getItemAtPosition(position).toString();
        }

    }

    /**
     * does nothing
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
