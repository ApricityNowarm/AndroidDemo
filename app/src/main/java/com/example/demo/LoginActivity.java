package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText account, password;
    private Button login_in, sign_up;
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        init();
        action();
    }

    private void init() {
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        sign_up = findViewById(R.id.to_sign_up);
        login_in = findViewById(R.id.login_in_btn);
    }

    private void action() {
        login_in.setOnClickListener((View view) -> {
            if (account.getText().toString().equals("") || password.getText().toString().equals("")) {
                Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            } else {
                User rUser = myViewModel.FindUser(account.getText().toString());
                if (rUser != null) {
                    if (rUser.getUserPassword().equals(password.getText().toString())) {
                        Intent intent = new Intent(this,MainActivity.class);
                        intent.putExtra("ID",rUser.getId());
                        this.startActivity(intent);
                        //跳转MainActivity
                    } else
                        Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "请输入正确的用户名", Toast.LENGTH_SHORT).show();
            }
        });
        sign_up.setOnClickListener((View v)->{
            Intent intent = new Intent(this,SignUpActivity.class);
            this.startActivity(intent);
        });
    }
}
