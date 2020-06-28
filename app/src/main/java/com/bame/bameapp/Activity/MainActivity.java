package com.bame.bameapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bame.bameapp.BaseApp;
import com.bame.bameapp.Http.ApiService;
import com.bame.bameapp.Http.ConfigRetrofit;
import com.bame.bameapp.Model.ResponsePintu;
import com.bame.bameapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseApp {
    CardView bPintu, bStatus;
    TextView tvUsername, tvStatus;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bStatus = findViewById(R.id.bStatus);
        bPintu = findViewById(R.id.bPintu);
        tvUsername = findViewById(R.id.tvUsername);
        tvStatus = findViewById(R.id.tvStatus);
        tvUsername.setText(sesi.getUsername());
        bStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, HistoryPintu.class));
            }
        });

        bPintu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ApiService api = ConfigRetrofit.getInstanceRetrofit();
                Call<ResponsePintu> call = api.buka_pintu(sesi.getUserId() );
                call.enqueue(new Callback<ResponsePintu>() {
                    @Override
                    public void onResponse(Call<ResponsePintu> call, Response<ResponsePintu> response) {
                        Log.d("response login : ", response.message());
                        if (response.isSuccessful()) {
                            boolean result = response.body().isStatus();
                            String pesan = response.body().getPesan();
                            if (result == true) {
                                Date HariSekarang = new Date( );
                                SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd ' ' hh:mm:ss ");
                                tvStatus.setText(ft.format(HariSekarang));
                                Snackbar.make(v, "Berhasil", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                            } else {
                                Snackbar.make(v, pesan, Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePintu> call, Throwable t) {

                        Log.d("error login : ", String.valueOf(t.getCause()));
                    }
                });
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.user_area, menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu); //inflate our menu

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.logout:
                sesi.logout();
                startActivity(new Intent(context, Login.class));
                finish();
// your code
                return true;
            default:
                return true;
        }
    }
}
