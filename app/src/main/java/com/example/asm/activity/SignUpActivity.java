package com.example.asm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm.R;
import com.example.asm.controller.users.SignUp;
import com.example.asm.model.User;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    EditText sign_in_fullName,sign_in_username,sign_in_password,sign_in_rePassword,sign_in_email;
    TextInputLayout fullNameInputLayout,usernameInputLayout,passwordInputLayout,rePasswordInputLayout,emailInputLayout;
    Button btn_sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        init();
        btn_sign_up.setOnClickListener(view -> {
            String username = sign_in_username.getText().toString();
            String password = sign_in_password.getText().toString();
            String rePassword = sign_in_rePassword.getText().toString();
            String email = sign_in_email.getText().toString();
            String fullName = sign_in_fullName.getText().toString();
            User user = new User("",username,password,email,fullName);
            if (checkUser(user,rePassword).length()==0){
                new SignUp().signUp(user);
                Toast.makeText(this, "Tạo tài khoản thàn công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
            }

        });
    }
    void init(){
        sign_in_fullName = findViewById(R.id.sign_in_fullName);
        sign_in_username = findViewById(R.id.sign_in_username);
        sign_in_password = findViewById(R.id.sign_in_password);
        sign_in_rePassword = findViewById(R.id.sign_in_rePassword);
        sign_in_email = findViewById(R.id.sign_in_email);
        fullNameInputLayout = findViewById(R.id.fullNameInputLayout);
        usernameInputLayout = findViewById(R.id.usernameInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        rePasswordInputLayout = findViewById(R.id.rePasswordInputLayout);
        emailInputLayout = findViewById(R.id.emailInputLayout);
        btn_sign_up = findViewById(R.id.btn_sign_up);
    }
    boolean isEmpty(String data){
        if (data.isEmpty()){
            return true;
        }
        return false;
    }
    boolean checkPass(String pass, String rePass){
        if (pass.equals(rePass)){
            return true;
        }
        return false;
    }
    private boolean checkEmail(String email){
        Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(email);
        return matcher.matches();
    }
    String checkUser(User user, String rePass){
        StringBuilder str = new StringBuilder();
        if (!isEmpty(user.email) && checkEmail(user.email)){
            emailInputLayout.setError(null);
        }else if (!checkEmail(user.email)) {
            emailInputLayout.setError("Không đúng định dạng");
            str.append("1");
        } else {
            emailInputLayout.setError("Không được để trống");
            str.append("1");
        }
        if (!isEmpty(user.fullName)){
            fullNameInputLayout.setError(null);

        }else {
            fullNameInputLayout.setError("Không được để trống");
            str.append("1");
        }
        if (!isEmpty(user.username)){
            usernameInputLayout.setError(null);

        }else {
            usernameInputLayout.setError("Không được để trống");
            str.append("1");
        }
        if (!isEmpty(user.password)){
            passwordInputLayout.setError(null);

        }else {
            passwordInputLayout.setError("Không được để trống");
            str.append("1");
        }

         if (rePass.isEmpty()) {
            rePasswordInputLayout.setError("Không được để trống");
            str.append("1");
         }else if (checkPass(user.password,rePass)){
             rePasswordInputLayout.setError(null);
         }
         else {
            rePasswordInputLayout.setError("Mật khẩu không khớp");
            str.append("!");
        }
    return str.toString();
    }
}