package com.sym.demoretrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sym.demoretrofit.R;
import com.sym.demoretrofit.bean.User;
import com.sym.demoretrofit.common.Api;
import com.sym.demoretrofit.service.UserService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private TextView showTv;
    private Button registerBtn, getUserBtn;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    private void init() {
        showTv = (TextView) findViewById(R.id.main_show_tv);
        registerBtn = (Button) findViewById(R.id.main_register_btn);
        registerBtn.setOnClickListener(this);
        getUserBtn = (Button) findViewById(R.id.main_getUser_btn);
        getUserBtn.setOnClickListener(this);
        userService = Api.getInstance().getRetrofit().create(UserService.class);
        this.login();
    }

    /**
     * login
     */
    private void login() {
        Call<ResponseBody> response = userService.login("sym", "12345678");
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    if (!TextUtils.isEmpty(result)) {
                        showTv.setText(result);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

    }

    /**
     * register
     */
    private void register() {
        Call<ResponseBody> response = userService.register("shaoyaming", "hahaha");
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * get user
     */
    private void getUser() {
        Call<User> response = userService.getUser();
        response.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.e(TAG, "username: " + user.getUsername());
                Log.e(TAG, "password: " + user.getPassword());
                Log.e(TAG, "age: " + user.getAge());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_register_btn:
                register();
                break;
            case R.id.main_getUser_btn:
                getUser();
                break;
        }
    }

}
