package com.bame.bameapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bame.bameapp.BaseApp;
import com.bame.bameapp.Http.ApiService;
import com.bame.bameapp.Http.ConfigRetrofit;
import com.bame.bameapp.Model.DataUser;
import com.bame.bameapp.Model.ResponseLogin;
import com.bame.bameapp.R;

import java.util.List;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseApp {

    String username,password;
    EditText etPassword, etUsername;
//    @BindView(R.id.etUsername)
//    EditText etUsername;
//    @BindView((R.id.etPassword))
//    EditText etPassword;
    Button bLogin;
//    @BindView(R.id.bLogin)
//    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        ButterKnife.bind(this);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"kagak boleh kosong goblok", Toast.LENGTH_SHORT).show();
                    Log.d("response login : ", "kagak boleh kosong goblok" + username + " "+ password);
                }else {
                    Log.d("response login : ", " goblok " + username + " "+ password);
                    ApiService api = ConfigRetrofit.getInstanceRetrofit();
                    Call<ResponseLogin> call = api.action_login(username, password);
                    call.enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            Log.d("response login : ", response.message());
                            if (response.isSuccessful()) {
                                boolean result = response.body().isStatus();
                                String pesan = response.body().getPesan();
                                if (result == true) {
                                    sesi.createLoginSession("1");
                                    List<DataUser> data = response.body().getData();
                                    sesi.setUserId(data.get(0).getUserId());
                                    sesi.setUsername(data.get(0).getUsername());

                                    Log.d("login user :" , String.valueOf(sesi.getUserId()));
                                    startActivity(new Intent(context, MainActivity.class));
                                    finish();

                                } else {
                                    //RbHelper.pesan(context, pesan);
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                    builder.setMessage("Username atau Password salah")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {

                            Log.d("error login : ", String.valueOf(t.getCause()));
                        }
                    });
                }
            }
        });

    }
    @OnClick(R.id.bLogin)
    public void onViewClicked() {
    }

}
