package com.udacity.gradle.builditbigger.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.udacity.gradle.builditbigger.data.NameModel;
import com.udacity.gradle.builditbigger.data.NamesHelper;

import java.util.ArrayList;

public final class DataUtils {

    private DataUtils() { throw new AssertionError(); }

    public static long addNameToDatabase(Context context, NameModel nameModel) {
        return addNameToDatabase(context, nameModel.name);
    }

    public static long addNameToDatabase(Context context, String name) {
        ContentValues cv = new ContentValues(1);
        cv.put(NamesHelper.COLUMN_NAME, name);
        return NamesHelper.getInstance(context).getWritableDatabase().insert(
                NamesHelper.TABLE_NAME, null, cv
        );
    }

    public static int deleteNameFromDatabase(Context context, NameModel nameModel) {
        return deleteNameFromDatabase(context, nameModel.id);
    }

    public static int deleteNameFromDatabase(Context context, long id) {

        final String where = NamesHelper.COLUMN_ID + "=?";
        final String[] whereArgs = { String.valueOf(id) };

        return NamesHelper.getInstance(context).getWritableDatabase().delete(
                NamesHelper.TABLE_NAME, where, whereArgs
        );

    }

    public static @NonNull ArrayList<NameModel> getNameModelsFromDatabase(Context context) {

        ArrayList<NameModel> names = null;

        final String[] columns = { NamesHelper.COLUMN_ID, NamesHelper.COLUMN_NAME };
        Cursor c = null;

        try {
            c = NamesHelper.getInstance(context).getReadableDatabase().query(
                    NamesHelper.TABLE_NAME, columns, null, null, null, null, NamesHelper.COLUMN_ID
            );
            names = getNameModelsListFromCursor(c);
        } finally {
            if (c != null && !c.isClosed()) c.close();
        }

        if (names == null) names = new ArrayList<>();

        return names;

    }

    private static ArrayList<NameModel> getNameModelsListFromCursor(Cursor cursor) {

        if (cursor == null || !cursor.moveToFirst()) {
            return null;
        }

        final ArrayList<NameModel> names = new ArrayList<>(cursor.getCount());

        long id = -1;
        String name = null;

        do {
            id = cursor.getLong(cursor.getColumnIndex(NamesHelper.COLUMN_ID));
            name = cursor.getString(cursor.getColumnIndex(NamesHelper.COLUMN_NAME));
            names.add(new NameModel(id, name));
        } while (cursor.moveToNext());

        return names;
    }

}
