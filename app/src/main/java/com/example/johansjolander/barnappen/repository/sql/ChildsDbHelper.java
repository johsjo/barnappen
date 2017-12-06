package com.example.johansjolander.barnappen.repository.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class ChildsDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "users.db";
    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + ChildDbContract.ChildEntry.TABLE_NAME + " (" +
                    ChildDbContract.ChildEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ChildDbContract.ChildEntry.COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                    ChildDbContract.ChildEntry.COLUMN_NAME_AGE + " INTEGER NOT NULL);";

    private static final String DROP_TABLE_USERS =

            "DROP TABLE IF EXISTS " + ChildDbContract.ChildEntry.TABLE_NAME;

    private static ChildsDbHelper instance;

    static synchronized ChildsDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ChildsDbHelper(context);
        }

        return instance;
    }

    private ChildsDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USERS);
        onCreate(db);
    }
}
