package com.example.kelly.mmelk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.kelly.mmelk.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuestionsFragment extends Fragment {
    @BindView(R.id.answer_three_spinner)Spinner mAnswerThreeSpinner;
    @BindView(R.id.answer_seven_spinner)Spinner mAnswerSevenSpinner;

    @BindView(R.id.answer_two_number_edittext)EditText mAnswerTwoEditText;
    @BindView(R.id.answer_four_number_edittext)EditText mAnswerFourEditText;
    @BindView(R.id.answer_five_number_edittext)EditText mAnswerFiveEditText;
    @BindView(R.id.answer_six_number_edittext)EditText mAnswerSixEditText;

    public QuestionsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_questions, container, false);
        ButterKnife.bind(this, rootView);

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
        return rootView;
    }






}
