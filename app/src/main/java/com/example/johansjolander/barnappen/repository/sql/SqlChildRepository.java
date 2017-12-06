package com.example.johansjolander.barnappen.repository.sql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.johansjolander.barnappen.model.Child;
import com.example.johansjolander.barnappen.repository.impl.ChildRepository;

import java.util.List;

import static com.example.johansjolander.barnappen.repository.sql.ChildDbContract.*;

public class SqlChildRepository implements ChildRepository {


    private static SqlChildRepository instance;

    public static synchronized SqlChildRepository getInstance(Context context) {
        if(instance == null) {
            instance = new SqlChildRepository(context);
        }

        return instance;
    }

    private final SQLiteDatabase database;

    private SqlChildRepository(Context context) {
        database = ChildsDbHelper.getInstance(context).getWritableDatabase();
    }

    @Override
    public Child getChild(long id) {
        return null;  //TODO Implement method
    }

    @Override
    public List<Child> getAllChildren() {
        return null;  //TODO Implement method
    }

    @Override
    public Child addChild(Child child) {
        return null;  //TODO Implement method
    }

    @Override
    public Child removeChild(long id) {
        return null;  //TODO Implement method
    }


    private ContentValues getContentValues(Child child) {
        ContentValues cv = new ContentValues();
        cv.put(ChildEntry.COLUMN_NAME_NAME, child.getName());
        cv.put(ChildEntry.COLUMN_NAME_AGE, child.getDays());

        return cv;
    }

    private ChildCursorWrapper queryUsers(String where, String[] whereArg) {
        @SuppressLint("Recycle")
        Cursor cursor = database.query(
                ChildEntry.TABLE_NAME,
                null,
                where,
                whereArg,
                null,
                null,
                null
        );

        return new ChildCursorWrapper(cursor);
    }


}
