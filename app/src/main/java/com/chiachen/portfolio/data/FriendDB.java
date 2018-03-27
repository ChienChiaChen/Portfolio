package com.chiachen.portfolio.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chiachen.portfolio.models.Friend;
import com.chiachen.portfolio.models.ListFriend;

/**
 * Created by jianjiacheng on 27/03/2018.
 */

public class FriendDB {
    private volatile static FriendDBHelper mDbHelper = null;
    private volatile static FriendDB instance = null;

    private FriendDB() {
    }

    public static FriendDB getInstance(Context context) {
        if (instance == null) {
            synchronized (FriendDB.class) {
                instance = new FriendDB();
                mDbHelper = new FriendDBHelper(context);
            }
        }
        return instance;
    }

    public long addFriend(Friend friend) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FriendFeedEntry.COLUMN_NAME_ID, friend.id);
        contentValues.put(FriendFeedEntry.COLUMN_NAME_NAME, friend.name);
        contentValues.put(FriendFeedEntry.COLUMN_NAME_EMAIL, friend.email);
        contentValues.put(FriendFeedEntry.COLUMN_NAME_AVATAR, friend.avatar);
        contentValues.put(FriendFeedEntry.COLUMN_NAME_ID_ROOM, friend.idRoom);
        return db.insert(FriendFeedEntry.TABLE_NAME, null, contentValues);
    }

    public ListFriend getListFriend() {
        ListFriend listFriend = new ListFriend();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("select * from " + FriendFeedEntry.TABLE_NAME, null);
            while (cursor.moveToNext()) {
                Friend friend = new Friend();
                friend.id = cursor.getString(0);
                friend.name = cursor.getString(1);
                friend.email = cursor.getString(2);
                friend.idRoom = cursor.getString(3);
                friend.avatar = cursor.getString(4);
                listFriend.getFriends().add(friend);
            }

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listFriend;
    }

    public void dropDB() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(FriendDBHelper.SQL_DELETE_ENTRIES);
        db.execSQL(FriendDBHelper.SQL_CREATE_ENTRIES);
    }
}
