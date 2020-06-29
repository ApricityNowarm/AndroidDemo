package com.example.demo;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(foreignKeys = @ForeignKey(entity = User.class,parentColumns = "id",childColumns = "u_id",onDelete = ForeignKey.CASCADE),indices = @Index(value = "u_id"))
public class UserData {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int u_id;

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    private String sort;

    private LocalDate date;
    @Ignore
    public UserData(int u_id, String sort, LocalDate date) {
        this.u_id = u_id;
        this.sort = sort;
        this.date = date;
    }

    public UserData() {
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getU_id() {
        return u_id;
    }
}
