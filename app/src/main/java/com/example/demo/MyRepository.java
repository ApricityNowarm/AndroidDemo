package com.example.demo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MyRepository {
    private UserDao userDao;
    private DataDao dataDao;

    public MyRepository(Context context) {
        MyDatabase myDatabase = MyDatabase.getInstance(context.getApplicationContext());
        userDao = myDatabase.getUserDao();
        dataDao = myDatabase.getDataDao();
    }

    public void saveUser(User... users) {
        new SaveUserAsyncTask(userDao).execute(users);
    }

    public User findUser(String s) {
        try {
            return new FindUserAsyncTask(userDao).execute(s).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveData(UserData... userData) {
        new SaveDataAsyncTask(dataDao).execute(userData);
    }

    public LiveData<List<UserData>> getAllData(Integer... integers) {
        try {
            return new GetAllDataByU_idAsyncTask(dataDao).execute(integers).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    static class FindUserAsyncTask extends AsyncTask<String, Void, User> {
        private UserDao userDao;

        public FindUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.findUserById(strings[0]);
        }
    }

    static class SaveUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public SaveUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.saveUser(users);
            return null;
        }
    }

    static class SaveDataAsyncTask extends AsyncTask<UserData, Void, Void> {
        private DataDao dataDao;

        public SaveDataAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(UserData... userData) {
            dataDao.saveData(userData);
            return null;
        }
    }

    static class GetAllDataByU_idAsyncTask extends AsyncTask<Integer, Void, LiveData<List<UserData>>> {
        private DataDao dataDao;

        public GetAllDataByU_idAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected LiveData<List<UserData>> doInBackground(Integer... integers) {
            return dataDao.getAllDataByU_id(integers[0]);
        }
    }
}
