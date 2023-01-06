package com.mec.msr.myclasses;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelperRequests extends SQLiteOpenHelper {

    private static final String DB_NAME = "msr_db";
    private static final int DB_VERSION = 4;

    private static final String TABLE_NAME = "equipments_requests";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER_ID = "uid";
    private static final String COLUMN_EQUIPMENT_TYPE = "equipment_type";
    private static final String COLUMN_RESERVE_TIME = "reserve_time";
    private static final String COLUMN_REQUEST_BY = "requested_by";

    @Override
    public void onOpen(SQLiteDatabase db) {
        onCreate(db);
    }

    public MyDBHelperRequests(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_USER_ID + " INTEGER,"
                + COLUMN_EQUIPMENT_TYPE + " TEXT, "
                + COLUMN_RESERVE_TIME + " TEXT, "
                + COLUMN_REQUEST_BY + " TEXT,"
                + " FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES "
                + MyDBHelperUser.TABLE_NAME + "(" + MyDBHelperUser.COLUMN_ID + "))";

        //execSQL will execute our query !
        sqLiteDatabase.execSQL(query);
    }

    public void addRequest(MyRequest newRequest) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EQUIPMENT_TYPE, newRequest.getEquipmentType());
        cv.put(COLUMN_RESERVE_TIME, newRequest.getReserveTime());
        cv.put(COLUMN_REQUEST_BY, newRequest.getRequestedBy());

        //getWritableDatabase this function allow you to write into sqlite db
        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_NAME, null, cv);

    }

    // read data from sqLite db and return them as arrayList
    public ArrayList<MyRequest> getMyRequestsArrayList(Context context) {

        ArrayList<MyRequest> requests = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        //getReadableDatabase allow you to read from sqLite
        SQLiteDatabase db = getReadableDatabase();

        // get data from sqLite db row bay row
        Cursor cursor = db.rawQuery(query, null);

        //check if there is data in table
        if (cursor.moveToFirst()) {

            do {
                @SuppressLint("Range")
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range")
                int uid = cursor.getInt(cursor.getColumnIndex(MyDBHelperUser.COLUMN_ID));
                @SuppressLint("Range")
                String equipmentType = cursor.getString(cursor.getColumnIndex(COLUMN_EQUIPMENT_TYPE));
                @SuppressLint("Range")
                String reserveTime = cursor.getString(cursor.getColumnIndex(COLUMN_RESERVE_TIME));
                @SuppressLint("Range")
                String requestedBy = cursor.getString(cursor.getColumnIndex(COLUMN_REQUEST_BY));

                if (uid == getUserId(context)) {
                    MyRequest myRequest = new MyRequest(id, uid, equipmentType, reserveTime, requestedBy);
                    requests.add(myRequest);
                }

            } while (cursor.moveToNext());// check if there is rows available

        }

        return requests;
    }


    public void updateRequest(MyRequest myRequest) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EQUIPMENT_TYPE, myRequest.getEquipmentType());
        cv.put(COLUMN_RESERVE_TIME, myRequest.getReserveTime());
        cv.put(COLUMN_REQUEST_BY, myRequest.getRequestedBy());

//getWritableDatabase this function allow you to write into sqlite db
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, cv, COLUMN_ID + " = " + myRequest.getId(), null);

    }


    public void deleteRequest(int requestId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = " + requestId, null);
    }

    private int getUserId(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("msr_pref", Context.MODE_PRIVATE);
        int defaultValue = 1;
        int userId = sharedPref.getInt("user_id", defaultValue);
        return userId;
    }
}
