package com.example.demo;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DataDao {
    @Insert
    public abstract void saveData(UserData ...data);
    @Update
    public abstract void updateData(UserData ...data);
    @Delete
    public abstract void deleteData(UserData ...data);
    @Query("DELETE  FROM UserData WHERE u_id = :search")
    public abstract int deleteUserData(int search);
    @Query("SELECT * FROM UserData WHERE id = :search")
    public abstract UserData findData(int search);
}
