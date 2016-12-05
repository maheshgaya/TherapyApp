package com.example.kelly.mmelk.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Mahesh Gaya on 12/2/16.
 */

public class ActivitiesProvider extends ContentProvider {
    private static final String TAG = ActivitiesProvider.class.getSimpleName();
    private ActivitiesDBHelper mOpenDbHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    //code for UriMatcher
    private static final int ACTIVITIES = 100; //dir
    private static final int ACTIVITIES_WITH_ID = 101; //item

    private static final int GOALS = 200;
    private static final int GOALS_WITH_ID = 201;

    private static final int QUESTIONS = 300;
    private static final int QUESTIONS_WITH_ID = 301;

    private static final int ANSWERS = 400;
    private static final int ANSWERS_WITH_ID = 401;

    private static final int POINTS = 500;
    private static final int POINTS_WITH_ID = 501;

    /**
     * build the different uris
     * @return
     */
    private static UriMatcher buildUriMatcher(){
        //initialize uri matcher
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ActivitiesContract.CONTENT_AUTHORITY;
        //add each uri
        //content://authority/activities
        matcher.addURI(authority, ActivitiesContract.ActivityEntry.TABLE_NAME, ACTIVITIES);
        //content://authority/activities/#
        matcher.addURI(authority, ActivitiesContract.ActivityEntry.TABLE_NAME + "/#", ACTIVITIES_WITH_ID);

        //content://authority/goals
        matcher.addURI(authority, ActivitiesContract.GoalEntry.TABLE_NAME, GOALS);
        //content://authority/goals/#
        matcher.addURI(authority, ActivitiesContract.GoalEntry.TABLE_NAME + "/#", GOALS_WITH_ID);

        //content://authority/questions
        matcher.addURI(authority, ActivitiesContract.QuestionEntry.TABLE_NAME, QUESTIONS);
        //content://authority/questions/#
        matcher.addURI(authority, ActivitiesContract.QuestionEntry.TABLE_NAME + "/#", QUESTIONS_WITH_ID);

        //content://authority/answers
        matcher.addURI(authority, ActivitiesContract.AnswerEntry.TABLE_NAME, ANSWERS);
        //content://authority/answers/#
        matcher.addURI(authority, ActivitiesContract.AnswerEntry.TABLE_NAME + "/#", ANSWERS_WITH_ID);

        //content://authority/points
        matcher.addURI(authority, ActivitiesContract.PointEntry.TABLE_NAME, POINTS);
        //content://authority/points/#
        matcher.addURI(authority, ActivitiesContract.PointEntry.TABLE_NAME + "/#", POINTS_WITH_ID);

        return matcher;
    }

    /**
     * Create an instance of DBHelper
     * @return
     */
    @Override
    public boolean onCreate() {
        mOpenDbHelper = new ActivitiesDBHelper(getContext());
        return false;
    }

    /**
     * Query the database based on uri
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)){
            case ACTIVITIES:{
                retCursor = mOpenDbHelper.getReadableDatabase().query(
                        ActivitiesContract.ActivityEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case ACTIVITIES_WITH_ID:{
                retCursor = mOpenDbHelper.getReadableDatabase().query(
                        ActivitiesContract.ActivityEntry.TABLE_NAME,
                        projection,
                        ActivitiesContract.ActivityEntry._ID + " = ? ",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case GOALS:{
                retCursor = mOpenDbHelper.getReadableDatabase().query(
                        ActivitiesContract.GoalEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case GOALS_WITH_ID:{
                retCursor = mOpenDbHelper.getReadableDatabase().query(
                        ActivitiesContract.GoalEntry.TABLE_NAME,
                        projection,
                        ActivitiesContract.GoalEntry._ID + " = ? ",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case QUESTIONS:{
                retCursor = mOpenDbHelper.getReadableDatabase().query(
                        ActivitiesContract.QuestionEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case QUESTIONS_WITH_ID:{
                retCursor = mOpenDbHelper.getReadableDatabase().query(
                        ActivitiesContract.QuestionEntry.TABLE_NAME,
                        projection,
                        ActivitiesContract.QuestionEntry._ID + " = ? ",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case ANSWERS:{
                retCursor = mOpenDbHelper.getReadableDatabase().query(
                        ActivitiesContract.AnswerEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case ANSWERS_WITH_ID:{
                retCursor = mOpenDbHelper.getReadableDatabase().query(
                        ActivitiesContract.AnswerEntry.TABLE_NAME,
                        projection,
                        ActivitiesContract.AnswerEntry._ID + " = ? ",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case POINTS:{
                retCursor = mOpenDbHelper.getReadableDatabase().query(
                        ActivitiesContract.PointEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case POINTS_WITH_ID:{
                retCursor = mOpenDbHelper.getReadableDatabase().query(
                        ActivitiesContract.PointEntry.TABLE_NAME,
                        projection,
                        ActivitiesContract.PointEntry._ID + " = ? ",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default: {
                // Bad URI
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }

        //notifies changes
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    /**
     * gets the type of the uri
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case ACTIVITIES: {
                return ActivitiesContract.ActivityEntry.CONTENT_TYPE;
            }
            case ACTIVITIES_WITH_ID:{
                return ActivitiesContract.ActivityEntry.CONTENT_ITEM_TYPE;
            }
            case GOALS: {
                return ActivitiesContract.GoalEntry.CONTENT_TYPE;
            }
            case GOALS_WITH_ID:{
                return ActivitiesContract.GoalEntry.CONTENT_ITEM_TYPE;
            }
            case QUESTIONS: {
                return ActivitiesContract.QuestionEntry.CONTENT_TYPE;
            }
            case QUESTIONS_WITH_ID:{
                return ActivitiesContract.QuestionEntry.CONTENT_ITEM_TYPE;
            }
            case ANSWERS: {
                return ActivitiesContract.AnswerEntry.CONTENT_TYPE;
            }
            case ANSWERS_WITH_ID:{
                return ActivitiesContract.AnswerEntry.CONTENT_ITEM_TYPE;
            }
            case POINTS: {
                return ActivitiesContract.PointEntry.CONTENT_TYPE;
            }
            case POINTS_WITH_ID:{
                return ActivitiesContract.PointEntry.CONTENT_ITEM_TYPE;
            }
        }
        return null;
    }

    /**
     * insert values into database
     * @param uri
     * @param contentValues
     * @return
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mOpenDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case ACTIVITIES: {
                long _id = db.insert(ActivitiesContract.ActivityEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = ActivitiesContract.ActivityEntry.buildActivityUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case GOALS: {
                long _id = db.insert(ActivitiesContract.GoalEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = ActivitiesContract.GoalEntry.buildGoalUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case QUESTIONS: {
                long _id = db.insert(ActivitiesContract.QuestionEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = ActivitiesContract.QuestionEntry.buildQuestionUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case ANSWERS: {
                long _id = db.insert(ActivitiesContract.AnswerEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = ActivitiesContract.AnswerEntry.buildAnswerUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case POINTS: {
                long _id = db.insert(ActivitiesContract.PointEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = ActivitiesContract.PointEntry.buildPointUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        //notifies changes
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        // this makes delete all rows return the number of rows deleted
        if (null == selection) selection = "1";
        switch (match) {
            case ACTIVITIES: {
                rowsDeleted = db.delete(
                        ActivitiesContract.ActivityEntry.TABLE_NAME, selection, selectionArgs
                );
                break;
            }
            case GOALS: {
                rowsDeleted = db.delete(
                        ActivitiesContract.GoalEntry.TABLE_NAME, selection, selectionArgs
                );
                break;
            }
            case QUESTIONS: {
                rowsDeleted = db.delete(
                        ActivitiesContract.QuestionEntry.TABLE_NAME, selection, selectionArgs
                );
                break;
            }
            case ANSWERS: {
                rowsDeleted = db.delete(
                        ActivitiesContract.AnswerEntry.TABLE_NAME, selection, selectionArgs
                );
                break;
            }
            case POINTS: {
                rowsDeleted = db.delete(
                        ActivitiesContract.PointEntry.TABLE_NAME, selection, selectionArgs
                );
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }

        }

        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;

    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case ACTIVITIES: {
                rowsUpdated = db.update(
                        ActivitiesContract.ActivityEntry.TABLE_NAME, contentValues, selection, selectionArgs
                );
                break;
            }
            case GOALS: {
                rowsUpdated = db.update(
                        ActivitiesContract.GoalEntry.TABLE_NAME, contentValues, selection, selectionArgs
                );
                break;
            }
            case QUESTIONS: {
                rowsUpdated = db.update(
                        ActivitiesContract.QuestionEntry.TABLE_NAME, contentValues, selection, selectionArgs
                );
                break;
            }
            case ANSWERS: {
                rowsUpdated = db.update(
                        ActivitiesContract.AnswerEntry.TABLE_NAME, contentValues, selection, selectionArgs
                );
                break;
            }
            case POINTS: {
                rowsUpdated = db.update(
                        ActivitiesContract.PointEntry.TABLE_NAME, contentValues, selection, selectionArgs
                );
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }

        }

        // Because a null deletes all rows
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
