package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class NamesHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "names.db";

    private static final int SCHEMA = 1;

    static final String TABLE_NAME = "names";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_NAME = "name";

    private static NamesHelper sInstance = null;

    static synchronized NamesHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NamesHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private NamesHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_NAMES_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL"
                + ");";

        sqLiteDatabase.execSQL(SQL_CREATE_NAMES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        throw new UnsupportedOperationException("Database upgrades not currently supported");
    }

}
