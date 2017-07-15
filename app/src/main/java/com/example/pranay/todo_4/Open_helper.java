package com.example.pranay.todo_4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pranay on 7/7/2017.
 */

public class Open_helper extends SQLiteOpenHelper {



    public static final String TABLE_NAME="TO__DO";
    public static final String ACTIVITY_NAME="activity_name";
    public static final String ACTIVITY_ID = "activity_id";
    public static final String ACTIVITY_DATE="activity_date";
    public static final String ACTIVITY_TIME="activity_time";
    public static final String ACTIVITY_PRIORITY="activity_priority";


    public Open_helper(Context context) {
        super(context, "TO__DOdatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table "+TABLE_NAME+"("+ACTIVITY_NAME+" text, "
                +ACTIVITY_ID+" integer primary key autoincrement, "
                +ACTIVITY_DATE+" text, "
                +ACTIVITY_TIME+" text, "
                +ACTIVITY_PRIORITY+" text" +");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }






}
