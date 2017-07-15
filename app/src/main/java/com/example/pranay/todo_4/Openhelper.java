package com.example.pranay.todo_4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pranay on 7/7/2017.
 */

public class Openhelper extends SQLiteOpenHelper {


    public static final String TABLE_NAME="TO_DO";

    public static final String ACTIVITY_NAME="activity_name";

    public static final String ACTIVITY_NUMBER = "activity_number";



    public Openhelper(Context context) {
        super(context, "TO_DO_database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table "+TABLE_NAME+"("+ACTIVITY_NAME+" text, "
                +ACTIVITY_NUMBER+" integer" +");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
