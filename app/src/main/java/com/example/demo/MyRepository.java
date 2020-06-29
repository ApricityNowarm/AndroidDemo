package com.example.demo;

import android.content.Context;
import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;

public class MyRepository {
    private UserDao userDao;
    private DataDao dataDao;

    public MyRepository(Context context){
        MyDatabase myDatabase = MyDatabase.getInstance(context.getApplicationContext());
        userDao = myDatabase.getUserDao();
        dataDao = myDatabase.getDataDao();
    }

    public void saveUser(User ...users){
        new SaveUserAsyncTask(userDao).execute(users);
    }
    public User findUser(String s){
        try {
            return new FindUserAsyncTask(userDao).execute(s).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    static class FindUserAsyncTask extends  AsyncTask<String,Void,User>{
        private UserDao userDao;
        public FindUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected User doInBackground(String... strings) {
            return userDao.findUserById(strings[0]);
        }
    }
    static class SaveUserAsyncTask extends AsyncTask<User,Void,Void> {
        private  UserDao userDao;
        public SaveUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.saveUser(users);
            return null;
        }
    }
}
