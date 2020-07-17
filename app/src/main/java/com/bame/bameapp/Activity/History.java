package com.bame.bameapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.RelativeLayout;

import com.bame.bameapp.Adapter.AdapterHistoryAktivitas;
import com.bame.bameapp.Adapter.AdapterHistoryPintu;
import com.bame.bameapp.BaseApp;
import com.bame.bameapp.Http.ApiService;
import com.bame.bameapp.Http.ConfigRetrofit;
import com.bame.bameapp.Model.DataAktivitasPintu;
import com.bame.bameapp.Model.DataHistory;
import com.bame.bameapp.Model.ResponseAktivitasPintu;
import com.bame.bameapp.Model.ResponseHistory;
import com.bame.bameapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends BaseApp {
    RecyclerView rvHistory;
    SwipeRefreshLayout srHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rvHistory = findViewById(R.id.rvHistory);
        srHistory = findViewById(R.id.srHistory);
        getHitory();
        srHistory.setColorSchemeResources(R.color.colorPrimary);
        srHistory.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srHistory.setRefreshing(false);
                        getHitory();
                    }
                }, 3000);
            }
        });
    }
    private void getHitory() {
        ApiService api = ConfigRetrofit.getInstanceRetrofit();
        api.getAktivitas(sesi.getUserId()).enqueue(new Callback<ResponseHistory>() {
            @Override
            public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                Log.d("Response truck : ", response.message());

                if (response.isSuccessful()) {
                    Boolean status = response.body().isStatus();
                    //String pesan = response.body().getPesan();
                    if (status == true) {

                        List<DataHistory> dataHistories = response.body().getData();
                        AdapterHistoryAktivitas adappterHistory  = new AdapterHistoryAktivitas(History.this, dataHistories);
                        rvHistory.setAdapter(adappterHistory);
                        rvHistory.setLayoutManager(new LinearLayoutManager(context));

//                        data = response.body().getData();
                        Log.d("data response :", String.valueOf(dataHistories.size()));
//                        // Log.d("response truck 2 :" ,data.get(1).getNamaTransporter());
//
//                        setSearch(data);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseHistory> call, Throwable t) {
                Log.d("fail truck : ", String.valueOf(t.getCause()));

            }
        });
    }
}