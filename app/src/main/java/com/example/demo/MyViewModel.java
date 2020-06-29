package com.example.demo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MyViewModel extends AndroidViewModel {
    private MyRepository myRepository;
    public MyViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
    }

    public void SaveUser(User ...users){
        myRepository.saveUser(users);
    }
    public User FindUser(String s){
        return myRepository.findUser(s);
    }
}
