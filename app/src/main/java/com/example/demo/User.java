package com.example.demo;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(indices = {@Index(value = "userId",unique = true)})
public class User {

    private String userId;

    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo
    private String userPassword;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public User() {
    }
    @Ignore
    public User(String userId, String uPs) {
        this.userId = userId;
        this.userPassword = uPs;
    }

}
