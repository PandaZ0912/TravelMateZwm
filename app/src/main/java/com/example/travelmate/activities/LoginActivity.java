package com.example.travelmate.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelmate.R;
import com.example.travelmate.data.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegisterLink;
    private Button btnForgotPassword;
    private CheckBox rememberMeCheckBox;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 初始化视图组件
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegisterLink = findViewById(R.id.btn_register_link);
        btnForgotPassword = findViewById(R.id.btn_forgot_password);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);

        // 初始化数据库帮助类
        dbHelper = new DatabaseHelper(this);

        // 检查是否记住密码
        checkRememberedUser();

        // 设置登录按钮的点击事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // 输入验证
                if (username.isEmpty()) {
                    etUsername.setError("请输入用户名");
                    etUsername.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    etPassword.setError("请输入密码");
                    etPassword.requestFocus();
                    return;
                }

                try {
                    // 登录验证
                    if (dbHelper.loginUser(username, password)) {
                        // 登录成功
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        // 如果勾选了“记住密码”，保存用户名和密码
                        if (rememberMeCheckBox.isChecked()) {
                            saveUserCredentials(username, password);
                        }
                        // 跳转到主页面
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // 结束登录页面
                    } else {
                        // 登录失败
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "登录失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 设置注册链接的点击事件
        btnRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到注册页面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // 设置忘记密码按钮的点击事件
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到忘记密码页面
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    // 检查是否记住了用户的用户名和密码
    private void checkRememberedUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        boolean rememberMe = sharedPreferences.getBoolean("remember_me", false);

        if (rememberMe) {
            String savedUsername = sharedPreferences.getString("username", "");
            String savedPassword = sharedPreferences.getString("password", "");

            etUsername.setText(savedUsername);
            etPassword.setText(savedPassword);
            rememberMeCheckBox.setChecked(true);
        }
    }

    // 保存用户名和密码到 SharedPreferences
    private void saveUserCredentials(String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putBoolean("remember_me", true);
        editor.apply();
    }

    // 如果你以后打算添加第三方登录（例如微信、QQ），可以在这里添加按钮的点击事件
    // 示例：
    // btnWechatLogin.setOnClickListener(new View.OnClickListener() {
    //     @Override
    //     public void onClick(View v) {
    //         // 调用第三方登录API
    //         thirdPartyLogin("wechat");
    //     }
    // });

    // private void thirdPartyLogin(String platform) {
    //     // 实现第三方登录逻辑
    // }
}

