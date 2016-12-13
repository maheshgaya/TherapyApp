package com.example.kelly.mmelk.data;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.kelly.mmelk.Constants;
import com.example.kelly.mmelk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by Mahesh Gaya on 12/7/16.
 */

/**
 * Loads the data from json files into the database
 */
public class DataService extends IntentService {
    String[] mQuestionArray;
    private static final String TAG = DataService.class.getSimpleName();

    public DataService(){
        super("DataService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        uploadJsonFile();

        //get questions from string array resources and add to database
        mQuestionArray = this.getResources().getStringArray(R.array.questions);
        for (int i = 0; i < mQuestionArray.length; i++){
            addQuestion(mQuestionArray[i]);
        }

    }
    /**
     * read activities from activities.json
     * put that in a buffer and then into the database
     */
    private void uploadJsonFile(){
        try {
            //read from file and into buffer
            InputStream inputStream = this.getAssets().open(Constants.DATA_ACTIVITIES_JSON);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String activitiesJson = new String(buffer, "UTF-8");

            //read the buffer and add that into database
            try{
                readActivities(activitiesJson);
            }catch (JSONException e){
                Log.e(TAG, "loadInBackground: ", e);
                e.printStackTrace();
            }

        }catch (java.io.IOException e){
            Log.e(TAG, "loadInBackground: ", e);
            e.printStackTrace();
        }
    }

    /**
     * loops through each object in the Json Array and add it to database
     * @param activitiesJson
     * @throws JSONException
     */
    private void readActivities(String activitiesJson)
        throws JSONException{
        JSONObject activitiesJsonObject = new JSONObject(activitiesJson);
        JSONArray activitiesArray = activitiesJsonObject.getJSONArray(Constants.DATA_ACTIVITIES_ARRAY);
        for (int i = 0; i < activitiesArray.length(); i++){
            JSONObject activityObject = activitiesArray.getJSONObject(i);
            String name = activityObject.getString(Constants.DATA_ACTIVITIES_NAME);
            String category = activityObject.getString(Constants.DATA_ACTIVITIES_CATEGORY);
            String picture = activityObject.getString(Constants.DATA_ACTIVITIES_PICTURE);
            addActivity(name, category, picture);
        }
    }

    /**
     * Check to see if the activity is already in the database
     * if not the add it
     * @param name
     * @param category
     * @return
     */
    private long addActivity(String name, String category, String picture) {
        long activityId;
        Cursor activityCursor = this.getContentResolver().query(
                ActivitiesContract.ActivityEntry.CONTENT_URI,
                new String[]{ActivitiesContract.ActivityEntry._ID},
                ActivitiesContract.ActivityEntry.COLUMN_NAME + " = ? ",
                new String[]{name},
                null
        );

        try {
            if (activityCursor.moveToFirst()){
                int activityIndex = activityCursor.getColumnIndex(ActivitiesContract.ActivityEntry._ID);
                activityId = activityCursor.getLong(activityIndex);
            } else {
                ContentValues activityValues = new ContentValues();
                activityValues.put(ActivitiesContract.ActivityEntry.COLUMN_NAME, name);
                activityValues.put(ActivitiesContract.ActivityEntry.COLUMN_CATEGORY, category);
                activityValues.put(ActivitiesContract.ActivityEntry.COLUMN_PICTURE, picture);

                Uri insertUri = this.getContentResolver().insert(
                        ActivitiesContract.ActivityEntry.CONTENT_URI,
                        activityValues
                );
                Log.d(TAG, "addActivity: " + insertUri.toString());
                activityId = ContentUris.parseId(insertUri);
            }

        } finally {
            activityCursor.close();
        }

        return activityId;
    }

    /**
     * Add questions to database
     * @param question
     * @return
     */
    private long addQuestion(String question){
        long questionId;
        Cursor questionCursor = this.getContentResolver().query(
                ActivitiesContract.QuestionEntry.CONTENT_URI,
                Constants.QUESTIONS_PROJECTION,
                ActivitiesContract.QuestionEntry.COLUMN_QUESTION + " = ? ",
                new String[]{question},
                null,
                null
        );

        try {
            if (questionCursor.moveToFirst()){
                int questionIndex = questionCursor.getColumnIndex(ActivitiesContract.GoalEntry._ID);
                questionId = questionCursor.getLong(questionIndex);
            } else {
                ContentValues questionValues = new ContentValues();
                questionValues.put(ActivitiesContract.QuestionEntry.COLUMN_QUESTION, question);

                Uri insertUri = this.getContentResolver().insert(
                        ActivitiesContract.QuestionEntry.CONTENT_URI,
                        questionValues
                );
                Log.d(TAG, "addQuestion: " + insertUri.toString());
                questionId = ContentUris.parseId(insertUri);

            }
        } finally {
            questionCursor.close();
        }
        return questionId;
    }

}
