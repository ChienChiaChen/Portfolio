package com.chiachen.portfolio.models;

import java.util.ArrayList;

/**
 * Created by jianjiacheng on 27/03/2018.
 */

public class ListFriend {

    private ArrayList<Friend> mFriends;

    public ListFriend() {
        mFriends = new ArrayList<>();
    }

    public ArrayList<Friend> getFriends() {
        return mFriends;
    }
}
