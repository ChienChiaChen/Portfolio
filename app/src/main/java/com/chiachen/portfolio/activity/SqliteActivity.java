package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.data.FriendDB;
import com.chiachen.portfolio.models.Friend;

public class SqliteActivity extends AppCompatActivity {

    private FriendDB mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        mDatabase = FriendDB.getInstance(SqliteActivity.this);

        Friend friend = new Friend();
        friend.id = "1";
        friend.name = "jason";
        friend.email = "s124202468@gmail.com";
        friend.avatar = "eee";
        friend.idRoom = "A";

        Log.e("JASON_CHIEN", "\nrow id: "+mDatabase.addFriend(friend));
    }
}
