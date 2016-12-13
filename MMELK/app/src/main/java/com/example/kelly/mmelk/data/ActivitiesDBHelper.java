package com.example.kelly.mmelk.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mahesh Gaya on 12/2/16.
 */

public class ActivitiesDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "therapy";
    public static final int DATABASE_VERSION = 4;

    /**
     * Required Constructor
     * @param context
     */
    public ActivitiesDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates the tables
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Activities Table
        /**
         * CREATE TABLE activites (_id INTEGER PRIMARY KEY AUTOINCREMENT,
         * name TEXT NOT NULL,
         * category TEXT NOT NULL
         * );
         */
        final String SQL_CREATE_ACTIVITIES_TABLE =
                "CREATE TABLE " + ActivitiesContract.ActivityEntry.TABLE_NAME + "(" +
                        ActivitiesContract.ActivityEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ActivitiesContract.ActivityEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                        ActivitiesContract.ActivityEntry.COLUMN_CATEGORY + " TEXT NOT NULL, " +
                        ActivitiesContract.ActivityEntry.COLUMN_PICTURE + " TEXT NOT NULL " +
                ");";

        /**
         * CREATE TABLE goals (_id INTEGER PRIMARY KEY AUTOINCREMENT,
         * activities_id INTEGER NOT NULL,
         * duration TEXT NOT NULL,
         * frequency TEXT NOT NULL,
         * FOREIGN KEY (activities_id) REFERENCES activities(_id)
         * );
         */
        final String SQL_CREATE_GOALS_TABLE =
                "CREATE TABLE " + ActivitiesContract.GoalEntry.TABLE_NAME + "(" +
                        ActivitiesContract.GoalEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ActivitiesContract.GoalEntry.COLUMN_ACTIVITIES_ID + " INTEGER NOT NULL, " +
                        ActivitiesContract.GoalEntry.COLUMN_DURATION + " TEXT NOT NULL, " +
                        ActivitiesContract.GoalEntry.COLUMN_FREQUENCY + " TEXT NOT NULL, " +
                "FOREIGN KEY (" + ActivitiesContract.GoalEntry.COLUMN_ACTIVITIES_ID + ") " +
                "REFERENCES " + ActivitiesContract.ActivityEntry.TABLE_NAME + "(" +
                    ActivitiesContract.ActivityEntry._ID + ")" +
                ");";

        /**
         * CREATE TABLE questions (_id INTEGER PRIMARY KEY AUTOINCREMENT,
         * question TEXT NOT NULL);
         *
         */
        final String SQL_CREATE_QUESTIONS_TABLE =
                "CREATE TABLE " + ActivitiesContract.QuestionEntry.TABLE_NAME + "(" +
                        ActivitiesContract.QuestionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ActivitiesContract.QuestionEntry.COLUMN_QUESTION + " TEXT NOT NULL" +
                ");";

        /**
         * CREATE TABLE answers (_id INTEGER PRIMARY KEY AUTOINCREMENT,
         * questions_id INTEGER NOT NULL,
         * answer TEXT NOT NULL,
         * date TEXT NOT NULL,
         * FOREIGN KEY (questions_id) REFRENECES questions(_id)
         * );
         */
        final String SQL_CREATE_ANSWERS_TABLE =
                "CREATE TABLE " + ActivitiesContract.AnswerEntry.TABLE_NAME + "(" +
                        ActivitiesContract.ActivityEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ActivitiesContract.AnswerEntry.COLUMN_QUESTION_ID + " INTEGER NOT NULL, " +
                        ActivitiesContract.AnswerEntry.COLUMN_ANSWER + " TEXT NOT NULL, " +
                        ActivitiesContract.AnswerEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                "FOREIGN KEY (" + ActivitiesContract.AnswerEntry.COLUMN_QUESTION_ID + ") " +
                "REFERENCES " + ActivitiesContract.QuestionEntry.TABLE_NAME + "(" +
                        ActivitiesContract.QuestionEntry._ID + ")" +
                ");";

        /**
         * CREATE TABLE points (_id INTEGER PRIMARY KEY AUTOINCREMENT,
         * point INTEGER NOT NULL,
         * date TEXT NOT NULL
         * );
         */
        final String SQL_CREATE_POINTS_TABLE =
                "CREATE TABLE " + ActivitiesContract.PointEntry.TABLE_NAME + "(" +
                        ActivitiesContract.PointEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ActivitiesContract.PointEntry.COLUMN_DATE + " TEXT NOT NULL" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_ACTIVITIES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_GOALS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ANSWERS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_POINTS_TABLE);
    }

    /**
     *
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ActivitiesContract.PointEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ActivitiesContract.AnswerEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ActivitiesContract.QuestionEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ActivitiesContract.GoalEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ActivitiesContract.ActivityEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
