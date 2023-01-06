package com.mec.msr;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "msr_db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "forklifts_requests";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FORKLIFT_TYPE = "forklift_type";
    private static final String COLUMN_RESERVE_TIME = "reserve_time";
    private static final String COLUMN_REQUEST_BY = "requested_by";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_FORKLIFT_TYPE + " TEXT, "
                + COLUMN_RESERVE_TIME + " TEXT, "
                + COLUMN_REQUEST_BY + " TEXT)";

        //execSQL will execute our query !
        sqLiteDatabase.execSQL(query);
    }

    public void addRequest(MyRequests newRequest) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FORKLIFT_TYPE, newRequest.getForkliftType());
        cv.put(COLUMN_RESERVE_TIME, newRequest.getReserveTime());
        cv.put(COLUMN_REQUEST_BY, newRequest.getRequestedBy());

        //getWritableDatabase this function allow you to write into sqlite db
        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_NAME, null, cv);

    }


}
