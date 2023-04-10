package com.example.asm.activity;

import static com.example.asm.constant.url;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.asm.R;
import com.example.asm.controller.SharedPreferencesUtils;
import com.example.asm.controller.users.ControllerUser;
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
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginScreenActivity extends AppCompatActivity {
    LoginButton loginButton;
    CallbackManager callbackManager;
    EditText username,password;
    Button btn_login;
    TextView tv_sign_up;
    TextInputLayout usernameInputLayout, passwordInputLayout;
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
        usernameInputLayout = findViewById(R.id.til_username);
        passwordInputLayout = findViewById(R.id.til_password);
        username.setText(SharedPreferencesUtils.getUsername(this));
        password.setText(SharedPreferencesUtils.getPassword(this));
        tv_sign_up.setOnClickListener(view -> {
            Intent intent = new Intent(this,SignUpActivity.class); 
            startActivity(intent);
        });
        btn_login.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass  = password.getText().toString();
            login(user,pass);
            SharedPreferencesUtils.setUsername(this,user);
            SharedPreferencesUtils.setPassword(this,pass);
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
                        Toast.makeText(LoginScreenActivity.this, "thành công1", Toast.LENGTH_SHORT).show();
                        Log.d("TAG1", "onSuccess: thành công ");
                        Log.d("TAG2", "onSuccess: "+ loginResult.toString());
                        GraphRequest request = GraphRequest.newMeRequest(
                                AccessToken.getCurrentAccessToken(),
                                (object, response) -> {
                                    try {
                                        String name = object.getString("name");
                                        String email = object.getString("email");
                                        String id = object.getString("id");
                                        SharedPreferencesUtils.setName(LoginScreenActivity.this,name);
                                        SharedPreferencesUtils.setUrl(LoginScreenActivity.this,email);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "name,email,id");
                        request.setParameters(parameters);
                        request.executeAsync();
                        Intent intent = new Intent(LoginScreenActivity.this, MainActivity2.class);
                        startActivity(intent);
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
    void login(String user,String pass){
        if (!isEmpty(user) && !isEmpty(pass) && checkPhone(user)){
            checkLogin(url,user,pass);
        }
        if (isEmpty(pass)) {
            passwordInputLayout.setError("Mật khẩu không được để trống");
        } else {
            passwordInputLayout.setError(null);
        }
        if (!checkPhone(user)) {
            usernameInputLayout.setError("Số điện thoại k đúng định dạng");
        }else {
            usernameInputLayout.setError(null);
        }
    }
    private boolean isEmpty(String data){
        if (data.isEmpty()){
            return true;
        }
        return false;
    }
    public boolean checkPhone(String phoneNumber){
        String regex ="[0][0-9]{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean matchFound = matcher.matches();
        if(matchFound) {
            System.out.println("Match found");
            return true;
        } else {
            System.out.println("Match not found");
            return false;
        }
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
                    public void onCompleted(JSONObject object, GraphResponse response) {
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
                            SharedPreferencesUtils.setName(LoginScreenActivity.this,first_name+last_name);
                            SharedPreferencesUtils.setUrl(LoginScreenActivity.this,image_url);
                            String email;
                            if (object.has("email")) {
                                email = object.getString("email");
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "id,first_name,last_name,email,gender,birthday"); // id,first_name,last_name,email,gender,birthday,cover,picture.type(large)
//        Log.d("qqqqqqqqqqqq", "getFbInfo: "+parameters.getBundle("id").toString());
//        request.setParameters(parameters);
//        Log.d("qqqqqqqqqqqq", "getFbInfo: "+request.toString());
//        request.executeAsync();
    }
    private void toMain(){
        startActivity(new Intent(LoginScreenActivity.this,MainActivity2.class));
        finish();
    }
    public void checkLogin(String link,String username,String password){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ControllerUser controllerUser = retrofit.create(ControllerUser.class);
        Call<List<User>> call = controllerUser.login(username,password);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("TAG", "onResponse: thành công"+ response);
                List<User> list = response.body();
                if (list.size()!=0){
                    Toast.makeText(LoginScreenActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    toMain();
                }else {
                    passwordInputLayout.setError("sai mật khẩu");
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("qqqqqqqqqq", "onFailure: ", t );
            }
        });
    }
}