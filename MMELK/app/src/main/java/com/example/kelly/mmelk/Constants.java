package com.example.kelly.mmelk;

import android.content.Context;
import android.content.res.Resources;
import android.util.Pair;

import com.example.kelly.mmelk.data.ActivitiesContract;

import java.util.HashMap;

/**
 * Created by Mahesh Gaya on 12/3/16.
 */

public class Constants {
    public static final String DATA_ACTIVITIES_JSON = "activities.json";
    public static final String DATA_ACTIVITIES_ARRAY = "activities";
    public static final String DATA_ACTIVITIES_NAME = "name";
    public static final String DATA_ACTIVITIES_CATEGORY = "category";
    public static final String DATA_ACTIVITIES_PICTURE = "picture";


    //Projections
    //Activities table
    public static final String[] ACTIVITIES_PROJECTION = {
            ActivitiesContract.ActivityEntry._ID,
            ActivitiesContract.ActivityEntry.COLUMN_NAME,
            ActivitiesContract.ActivityEntry.COLUMN_CATEGORY,
            ActivitiesContract.ActivityEntry.COLUMN_PICTURE
    };

    public static final int COLUMN_ACTIVITIES_ID = 0;
    public static final int COLUMN_ACTIVITIES_NAME = 1;
    public static final int COLUMN_ACTIVITIES_CATEGORY = 2;
    public static final int COLUMN_ACTIVITIES_PICTURE = 3;

    //Questions table
    public static final String[] QUESTIONS_PROJECTION = {
            ActivitiesContract.QuestionEntry._ID,
            ActivitiesContract.QuestionEntry.COLUMN_QUESTION
    };

    public static final int COLUMN_QUESTIONS_ID = 0;
    public static final int COLUMN_QUESTIONS_NAME = 1;

    //Goals table
    public static final String[]  GOALS_PROJECTION = {
            ActivitiesContract.GoalEntry._ID,
            ActivitiesContract.GoalEntry.COLUMN_ACTIVITIES_ID,
            ActivitiesContract.GoalEntry.COLUMN_DURATION,
            ActivitiesContract.GoalEntry.COLUMN_FREQUENCY
    };

    public static final int COLUMN_GOALS_ID = 0;
    public static final int COLUMN_GOALS_ACTIVITIES_ID = 1;
    public static final int COLUMN_GOALS_DURATION = 2;
    public static final int COLUMN_GOALS_FREQUENCY = 3;

    //Answers table
    public static final String[] ANSWERS_PROJECTION = {
            ActivitiesContract.AnswerEntry._ID,
            ActivitiesContract.AnswerEntry.COLUMN_QUESTION_ID,
            ActivitiesContract.AnswerEntry.COLUMN_DATE,
            ActivitiesContract.AnswerEntry.COLUMN_ANSWER
    };

    public static final int COLUMN_ANSWERS_ID = 0;
    public static final int COLUMN_ANSWERS_QUESTION_ID = 1;
    public static final int COLUMN_ANSWERS_DATE = 2;
    public static final int COLUMN_ANSWERS_ANSWER = 3;

    //Points table
    public static final String[] POINTS_PROJECTION = {
            ActivitiesContract.PointEntry._ID,
            ActivitiesContract.PointEntry.COLUMN_DATE,
            ActivitiesContract.PointEntry.COLUMN_POINT
    };

    public static final int COLUMN_POINTS_ID = 0;
    public static final int COLUMN_POINTS_DATE = 1;
    public static final int COLUMN_POINTS_POINT = 2;
}
