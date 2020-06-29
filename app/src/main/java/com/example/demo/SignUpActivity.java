package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    private MyViewModel myViewModel;
    private EditText account,password,certain_password;
    private ImageButton to_login;
    String log = "log";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        init();
        action();
    }

    private void init(){
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        certain_password = findViewById(R.id.certain_password);
        to_login = findViewById(R.id.to_login_in);
    }

    private void action(){
        to_login.setOnClickListener((View v)->{
            if(account.getText().toString().length() > 16
                    || password.getText().toString().length() >16
                    || certain_password.getText().toString().length() >16)
                Toast.makeText(this,"账号或密码不得超出16位",Toast.LENGTH_SHORT).show();
            else if(!password.getText().toString().equals(certain_password.getText().toString())) {
                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            }else if(account.getText().toString().contains(" ")){
                Toast.makeText(this,"账号不能包含空格",Toast.LENGTH_SHORT).show();
            }
            else if(password.getText().toString().contains(" "))
                Toast.makeText(this,"密码不能包含空格",Toast.LENGTH_SHORT).show();
            else{
                User user = new User(account.getText().toString(),password.getText().toString());
                myViewModel.SaveUser(user);
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,LoginActivity.class);
                this.startActivity(intent);
            }
        });
    }
}