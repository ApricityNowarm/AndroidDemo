package com.example.demo;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    public abstract void saveUser(User ...user);
    @Update
    public abstract void updateUser(User ...user);
    @Delete
    public abstract void deleteUser(User ...user);
    @Query("SELECT * FROM user WHERE userId = :search")
    public abstract User findUserById(String search);
}
