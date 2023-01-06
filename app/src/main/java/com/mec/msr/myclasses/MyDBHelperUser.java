package com.mec.msr.myclasses;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelperUser extends SQLiteOpenHelper {

    private static final String DB_NAME = "msr_db";
    private static final int DB_VERSION = 4;

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    private static final String COLUMN_FULL_NAME = "full_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    @Override
    public void onOpen(SQLiteDatabase db) {
        onCreate(db);
    }

    public MyDBHelperUser(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_FULL_NAME + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_PASSWORD + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    public void addUser(MyUser newUser) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FULL_NAME, newUser.getFullName());
        cv.put(COLUMN_EMAIL, newUser.getEmail());
        cv.put(COLUMN_PASSWORD, newUser.getPassword());

        //getWritableDatabase this function allow you to write into sqlite db
        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_NAME, null, cv);

    }


    public MyUser findUser(String emailToCheck, String passwordToCheck) {

        MyUser user = new MyUser("empty", "empty", "empty");
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
                String fullName = cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME));
                @SuppressLint("Range")
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                @SuppressLint("Range")
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                if (emailToCheck.equals(email) && password.equals(passwordToCheck))
                    user = new MyUser(id, fullName, email, password);
            } while (cursor.moveToNext());
        }

        return user;
    }


}
