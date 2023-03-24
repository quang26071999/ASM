package com.example.asm.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.asm.R;
import com.example.asm.controller.users.CheckLogin;
import com.example.asm.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class LoginScreenActivity extends AppCompatActivity {
    LoginButton loginButton;
    CallbackManager callbackManager;
    TextInputEditText username,password;
    Button btn_login;
    String link = "http://192.168.55.103:3000";
    TextView tv_sign_up;
    private static final String EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();
        loginButton = findViewById(R.id.login_button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        tv_sign_up = findViewById(R.id.tv_sign_up);
        tv_sign_up.setOnClickListener(view -> {
            Intent intent = new Intent(this,SignUpActivity.class); 
            startActivity(intent);
        });
        btn_login.setOnClickListener(view -> {
            String name = username.getText().toString();
            String pass  = password.getText().toString();
            User user = new User(name,pass,"","");
            new CheckLogin().checkLogin(link,user);
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
        });

        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginScreenActivity.this, "thành công", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onSuccess: thành công ");

                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(LoginScreenActivity.this, "thất bại", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onSuccess:  thất bại ");
            }
        });


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(LoginScreenActivity.this, "thành công", Toast.LENGTH_SHORT).show();
                        Log.d("TAG1", "onSuccess: thành công ");
                        Log.d("TAG2", "onSuccess: "+ loginResult.toString());
                        getFbInfo();
                        // App code
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(LoginScreenActivity.this, "thất bại", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onSuccess:  thất bại ");
                        // App code
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void getFbInfo() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            String LOG_TAG= "zzzzzzzzz";
                            Log.d(LOG_TAG, "fb json object: " + object);
                            Log.d(LOG_TAG, "fb graph response: " + response);

                            String id = object.getString("id");
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String gender = object.getString("gender");
                            String birthday = object.getString("birthday");
                            String image_url = "http://graph.facebook.com/" + id + "/picture?type=large";

                            String email;
                            if (object.has("email")) {
                                email = object.getString("email");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name,email,gender,birthday"); // id,first_name,last_name,email,gender,birthday,cover,picture.type(large)
        Log.d("qqqqqqqqqqqq", "getFbInfo: "+parameters.getBundle("id").toString());
        request.setParameters(parameters);
        Log.d("qqqqqqqqqqqq", "getFbInfo: "+request.toString());
        request.executeAsync();
    }
}