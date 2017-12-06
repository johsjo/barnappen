package com.example.johansjolander.barnappen.repository.sql;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.johansjolander.barnappen.model.Child;

class ChildCursorWrapper extends CursorWrapper {
    public ChildCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    Child getUser() {
        long id = getLong(getColumnIndexOrThrow(ChildDbContract.ChildEntry._ID));
        String username = getString(getColumnIndexOrThrow(ChildDbContract.ChildEntry.COLUMN_NAME_NAME));
        int age = getInt(getColumnIndexOrThrow(ChildDbContract.ChildEntry.COLUMN_NAME_AGE));

        return new Child(id, username, age);
    }

    public Child getFirstUser() {
        moveToFirst();
        return getUser();
    }
}
