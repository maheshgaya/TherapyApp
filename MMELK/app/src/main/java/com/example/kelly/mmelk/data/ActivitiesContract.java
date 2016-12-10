package com.example.kelly.mmelk.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Mahesh Gaya on 12/2/16.
 */

public class ActivitiesContract {
    public static final String CONTENT_AUTHORITY = "com.example.kelly.mmelk.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //Constants for paths
    public static final String PATH_ACTIVITIES = "activities";
    public static final String PATH_GOALS = "goals";
    public static final String PATH_QUESTIONS = "questions";
    public static final String PATH_ANSWERS = "answers";
    public static final String PATH_POINTS = "points";

    /**
     * Activity Table
     * Contains: name, category, picture_url
     */
    public static final class ActivityEntry implements BaseColumns{
        //defining the default paths
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ACTIVITIES).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ACTIVITIES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ACTIVITIES;

        //Define table
        //table name
        public static final String TABLE_NAME = "activities";
        //Columns
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_PICTURE = "picture";

        //building the paths
        public static Uri buildActivityUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /**
     * Goal Table
     * Contains: activity_id (foreign key), duration, frequency
     */
    public static final class GoalEntry implements BaseColumns{
        //defining the default paths
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GOALS).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GOALS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GOALS;

        //Define table
        //table name
        public static final String TABLE_NAME = "goals";
        //Columns
        public static final String COLUMN_ACTIVITIES_ID = "activities_id";
        public static final String COLUMN_DURATION = "duration";
        public static final String COLUMN_FREQUENCY = "frequency";

        //building the paths
        public static Uri buildGoalUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /**
     * Question Table
     * Contains: question
     */
    public static final class QuestionEntry implements BaseColumns{
        //defining the default paths
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_QUESTIONS).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_QUESTIONS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_QUESTIONS;

        //Define Table
        //table name
        public static final String TABLE_NAME = "questions";
        //Columns
        public static final String COLUMN_QUESTION = "question";

        //building the paths
        public static Uri buildQuestionUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /**
     * Answer Table
     * Contains: questions_id, answer, date
     */
    public static final class AnswerEntry implements BaseColumns{
        //defining the default paths
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ANSWERS).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ANSWERS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ANSWERS;

        //Define Table
        //table name
        public static final String TABLE_NAME = "answers";
        //Columns
        public static final String COLUMN_QUESTION_ID = "questions_id";
        public static final String COLUMN_ANSWER = "answer";
        public static final String COLUMN_DATE = "date";

        //building the paths
        public static Uri buildAnswerUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /**
     * Point Table
     * Contains: points, date
     */
    public static final class PointEntry implements BaseColumns{
        //defining the default paths
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_POINTS).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_POINTS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_POINTS;

        //Define table
        //table name
        public static final String TABLE_NAME = "points";
        //Columns
        public static final String COLUMN_POINT = "point";
        public static final String COLUMN_DATE = "date";

        //building the paths
        public static Uri buildPointUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


}
