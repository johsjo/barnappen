package com.example.johansjolander.barnappen.repository.sql;

import android.provider.BaseColumns;

class ChildDbContract {

    private ChildDbContract() {}

    static class ChildEntry implements BaseColumns{
        public static final String TABLE_NAME = "childs";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
    }
}
