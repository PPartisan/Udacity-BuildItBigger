package com.udacity.gradle.builditbigger.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class NamesHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "names.db";

    private static final int SCHEMA = 1;

    public static final String TABLE_NAME = "names";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";

    private static NamesHelper sInstance = null;

    public static synchronized NamesHelper getInstance(Context context) {
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
