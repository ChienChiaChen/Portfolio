package com.chiachen.portfolio.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jianjiacheng on 15/04/2018.
 */

public class UserRepository {
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("pwd")
    @Expose
    public String pwd;

    public UserRepository withId(String id) {
        this.id = id;
        return this;
    }

    public UserRepository withName(String name) {
        this.name = name;
        return this;
    }

    public UserRepository withPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }
}
