package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.data.FriendDB;
import com.chiachen.portfolio.models.Friend;
import com.chiachen.portfolio.network.AppSchedulerProvider;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class SqliteActivity extends AppCompatActivity {
    private FriendDB mDatabase;
    private EditText mEditText;
    private ImageView mClear;

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

        initEditText();
    }

    private void initEditText() {
        mEditText =findViewById(R.id.edit_text);
        mClear =findViewById(R.id.clear);

        RxTextView.textChanges(mEditText)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AppSchedulerProvider.ui())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        mClear.setVisibility(charSequence.length() != 0 ? View.VISIBLE : View.INVISIBLE);
                    }
                });

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
    }
}
