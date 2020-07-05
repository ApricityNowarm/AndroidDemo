package com.example.demo;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class MyViewModel extends AndroidViewModel {
    private MyRepository myRepository;
    private MutableLiveData<StringBuilder> money = new MutableLiveData<>(new StringBuilder("0.00"));
    private int USER_ID;

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public MyViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
    }

    public void SaveUser(User... users) {
        myRepository.saveUser(users);
    }

    public User FindUser(String s) {
        return myRepository.findUser(s);
    }

    public void SaveData(UserData... userData) {
        myRepository.saveData(userData);
    }

    public LiveData<List<UserData>> getAllData(int u_id) {
        return myRepository.getAllData(u_id);
    }

    public void setMoney(MutableLiveData<StringBuilder> money) {
        this.money = money;
    }

    public void setMoney(String money) {
        this.money.setValue(new StringBuilder(money));
    }

    public MutableLiveData<StringBuilder> getMoney() {
        return money;
    }

    public void appendMoney(String s) {
        this.money.setValue(Objects.requireNonNull(money.getValue()).append(s));
    }

    public void calculation() {
        String[] split = new String[2];
        if (money.getValue() != null) {
            String sMoney = money.getValue().toString();
            if (sMoney.startsWith("-") && sMoney.substring(1).contains("+")) {
                split = sMoney.substring(1).split("\\+");
                if (sMoney.substring(1).endsWith("+")) {
                    setMoney(new BigDecimal(split[0])
                            .add(new BigDecimal("0.00"))
                            .setScale(1, BigDecimal.ROUND_HALF_UP)
                            .toString());
                } else
                    setMoney(new BigDecimal("-" + split[0])
                            .add(new BigDecimal(split[1]))
                            .setScale(1, BigDecimal.ROUND_HALF_UP)
                            .toString());
            } else if (sMoney.startsWith("-") && sMoney.substring(1).contains("-")) {
                split = sMoney.substring(1).split("\\-");
                if (sMoney.substring(1).endsWith("-")) {
                    setMoney(new BigDecimal(split[0])
                            .subtract(new BigDecimal("0.00"))
                            .setScale(1, BigDecimal.ROUND_HALF_UP)
                            .toString());
                } else
                    setMoney(new BigDecimal("-" + split[0])
                            .subtract(new BigDecimal(split[1]))
                            .setScale(1, BigDecimal.ROUND_HALF_UP)
                            .toString());
            } else if (sMoney.contains("+")) {
                split = sMoney.split("\\+");
                if (sMoney.endsWith("+")) {
                    setMoney(new BigDecimal(split[0])
                            .add(new BigDecimal("0.00"))
                            .setScale(1, BigDecimal.ROUND_HALF_UP)
                            .toString());
                } else
                    setMoney(new BigDecimal(split[0])
                            .add(new BigDecimal(split[1]))
                            .setScale(1, BigDecimal.ROUND_HALF_UP)
                            .toString());
            } else if (sMoney.contains("-")) {
                split = sMoney.split("-");
                if (sMoney.endsWith("-")) {
                    setMoney(new BigDecimal(split[0])
                            .subtract(new BigDecimal("0.00"))
                            .setScale(1, BigDecimal.ROUND_HALF_UP)
                            .toString());
                } else
                    setMoney(new BigDecimal(split[0])
                            .subtract(new BigDecimal(split[1]))
                            .setScale(1, BigDecimal.ROUND_HALF_UP)
                            .toString());
            }
        }
    }
}
