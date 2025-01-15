package com.example.travelmate.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelmate.R;
import com.example.travelmate.data.DatabaseHelper;
import com.example.travelmate.models.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputLayout tlUsername, tlEmail, tlPhone, tlPassword, tlConfirmPassword;
    private TextInputEditText etUsername, etEmail, etPhone, etPassword, etConfirmPassword;
    private MaterialButton btnSubmit;
    private ProgressBar progressBar;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // 初始化视图组件
        tlUsername = findViewById(R.id.tl_username);
        tlEmail = findViewById(R.id.tl_email);
        tlPhone = findViewById(R.id.tl_phone);
        tlPassword = findViewById(R.id.tl_password);
        tlConfirmPassword = findViewById(R.id.tl_confirm_password);

        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);

        btnSubmit = findViewById(R.id.btn_submit);
        progressBar = findViewById(R.id.progressBar);

        // 初始化数据库帮助类
        dbHelper = new DatabaseHelper(this);

        // 提交按钮点击事件
        btnSubmit.setOnClickListener(view -> {
            try {
                onSubmitClicked();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    // 提交按钮点击时的处理逻辑
    private void onSubmitClicked() throws Exception {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // 输入校验
        if (TextUtils.isEmpty(username)) {
            tlUsername.setError("请输入用户名");
            return;
        } else if (TextUtils.isEmpty(email)) {
            tlEmail.setError("请输入邮箱");
            return;
        } else if (TextUtils.isEmpty(phone)) {
            tlPhone.setError("请输入手机号");
            return;
        } else if (TextUtils.isEmpty(password)) {
            tlPassword.setError("请输入新密码");
            return;
        } else if (TextUtils.isEmpty(confirmPassword)) {
            tlConfirmPassword.setError("请确认新密码");
            return;
        } else if (!password.equals(confirmPassword)) {
            tlConfirmPassword.setError("密码不一致");
            return;
        }

        // 校验用户名对应的邮箱和手机号
        boolean isValidUser = checkUserDetails(username, email, phone);
        if (!isValidUser) {
            Toast.makeText(ForgotPasswordActivity.this, "用户名、邮箱或手机号不匹配", Toast.LENGTH_SHORT).show();
            return;
        }

        // 显示进度条，禁用提交按钮
        progressBar.setVisibility(View.VISIBLE);
        btnSubmit.setEnabled(false);

        // 模拟网络请求：重置密码
        resetPassword(username, email, phone, password);
    }

    // 校验用户名对应的邮箱和手机号的方法
    private boolean checkUserDetails(String username, String email, String phone) {
        // 根据用户名查询数据库中的邮箱和手机号
        User userDetails = dbHelper.getUserDetailsByUsername(username);  // 假设该方法返回用户名对应的邮箱和手机号
        if (userDetails == null) {
            return false;  // 用户不存在
        }

        // 从 User 对象中获取邮箱和手机号
        String storedEmail = userDetails.getEmail();
        String storedPhone = userDetails.getPhone();

        // 比较数据库中的邮箱和手机号与用户输入的是否一致
        return storedEmail.equals(email) && storedPhone.equals(phone);
    }

    // 重置密码的方法（调用DatabaseHelper更新密码）
    private void resetPassword(String username, String email, String phone, String password) throws Exception {
        // 在数据库中更新密码
        boolean result = dbHelper.updatePassword(username, password);

        // 模拟网络请求延迟
        new android.os.Handler().postDelayed(() -> {
            // 网络请求结束，隐藏进度条，启用提交按钮
            progressBar.setVisibility(View.GONE);
            btnSubmit.setEnabled(true);

            if (result) {
                // 密码重置成功
                Toast.makeText(ForgotPasswordActivity.this, "密码重置成功", Toast.LENGTH_SHORT).show();
                // 可以跳转到登录页面
                finish();
            } else {
                // 密码重置失败，可能是用户不存在
                Toast.makeText(ForgotPasswordActivity.this, "无法重置密码，请检查用户名", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}
