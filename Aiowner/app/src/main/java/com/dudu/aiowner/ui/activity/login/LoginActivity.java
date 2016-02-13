package com.dudu.aiowner.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dudu.aiowner.R;
import com.dudu.aiowner.rest.Request;
import com.dudu.aiowner.rest.model.LoginResponse;
import com.dudu.aiowner.ui.base.BaseActivity;
import com.dudu.aiowner.ui.main.MainActivity;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sunny_zhang on 2016/1/27.
 */
public class LoginActivity extends BaseActivity {
    private EditText login_user_edittext;
    private EditText login_password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        login_user_edittext = (EditText) findViewById(R.id.login_user_edittext);
        login_password_et = (EditText) findViewById(R.id.login_password_et);
    }

    @Override
    protected View getChildView() {
        return LayoutInflater.from(this).inflate(R.layout.acitivty_login, null);
    }

    public void startMain(View view) {
        Request.getInstance().getLoginService().login(login_user_edittext.getText().toString(), login_password_et.getText().toString(), "", "", new Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse loginResponse, Response response) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(LoginActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void startForgetPreventTheftPsw(View view) {

        startActivity(new Intent(LoginActivity.this, ForgetLoginPswActitivy.class));
    }

    @Override
    protected void onResume() {
        observableFactory.getTitleObservable().titleText.set("登录");
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
