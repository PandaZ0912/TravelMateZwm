package com.example.travelmate.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelmate.R;
import com.example.travelmate.data.DatabaseConstants;
import com.example.travelmate.data.DatabaseHelper;
import com.example.travelmate.utils.EncryptionUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etUsername;
    private TextInputEditText etPhone;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextInputEditText etConfirmPassword;
    private MaterialButton btnRegister;
    private MaterialButton btnLoginLink;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);  // 使用新布局

        // 初始化视图组件
        etUsername = findViewById(R.id.et_username);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnRegister = findViewById(R.id.btn_register);
        btnLoginLink = findViewById(R.id.btn_login_link);

        // 初始化数据库帮助类
        dbHelper = new DatabaseHelper(this);

        // 设置注册按钮的点击事件
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                // 校验用户名、手机号、邮箱、密码、确认密码
                if (username.isEmpty()) {
                    etUsername.setError("请输入用户名");
                    etUsername.requestFocus();
                    return;
                }
                if (phone.isEmpty() || !phone.matches("^[1][3-9][0-9]{9}$")) {
                    etPhone.setError("请输入有效的手机号");
                    etPhone.requestFocus();
                    return;
                }
                if (email.isEmpty() || !email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$")) {
                    etEmail.setError("请输入有效的邮箱");
                    etEmail.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    etPassword.setError("请输入密码");
                    etPassword.requestFocus();
                    return;
                }
                if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
                    etConfirmPassword.setError("确认密码与密码不一致");
                    etConfirmPassword.requestFocus();
                    return;
                }

                try {
                    // 生成 salt
                    SecureRandom random = new SecureRandom();
                    byte[] salt = new byte[16];
                    random.nextBytes(salt);
                    String saltString = bytesToHex(salt);

                    // 加密密码
                    String encryptedPassword = EncryptionUtils.encrypt(password, saltString);

                    // 检查用户名是否已存在
                    if (doesUserExist(username)) {
                        Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // 插入新用户
                    if (dbHelper.registerUser(username, phone, email, encryptedPassword, saltString)) {

                        // 注册成功后跳转到登录页面
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "加密失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 设置已有账号登录链接的点击事件
        btnLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到登录页面
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean doesUserExist(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseConstants.TABLE_USERS + " WHERE " + DatabaseConstants.COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

}