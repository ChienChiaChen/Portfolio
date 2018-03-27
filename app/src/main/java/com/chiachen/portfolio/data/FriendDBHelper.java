package com.chiachen.portfolio.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jianjiacheng on 27/03/2018.
 */

public class FriendDBHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "FriendChat.db";

    static final String TEXT_TYPE = " Text";
    static final String COMMA_SEP = ",";
    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FriendFeedEntry.TABLE_NAME + " (" +
                    FriendFeedEntry.COLUMN_NAME_ID + " TEXT PRIMARY KEY," +
                    FriendFeedEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    FriendFeedEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    FriendFeedEntry.COLUMN_NAME_ID_ROOM + TEXT_TYPE + COMMA_SEP +
                    FriendFeedEntry.COLUMN_NAME_AVATAR + TEXT_TYPE + " )";

    static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FriendFeedEntry.TABLE_NAME;

    public FriendDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
