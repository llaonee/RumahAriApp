package com.bame.bameapp.Activity;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bame.bameapp.Adapter.AdapterHistoryPintu;
import com.bame.bameapp.BaseApp;
import com.bame.bameapp.Http.ApiService;
import com.bame.bameapp.Http.ConfigRetrofit;
import com.bame.bameapp.Model.DataAktivitasPintu;
import com.bame.bameapp.Model.ResponseAktivitasPintu;
import com.bame.bameapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPintu extends BaseApp {
    RecyclerView rvHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pintu);
        rvHistory = findViewById(R.id.rvHistory);
        getHitory();
    }

    private void getHitory() {
        ApiService api = ConfigRetrofit.getInstanceRetrofit();
        api.get_history(sesi.getUserId()).enqueue(new Callback<ResponseAktivitasPintu>() {
            @Override
            public void onResponse(Call<ResponseAktivitasPintu> call, Response<ResponseAktivitasPintu> response) {
                Log.d("Response truck : ", response.message());

                if (response.isSuccessful()) {
                    Boolean status = response.body().isStatus();
                    //String pesan = response.body().getPesan();
                    if (status == true) {

                        List<DataAktivitasPintu> dataAktivitasPintus = response.body().getData();
                        AdapterHistoryPintu adappterHistory  = new AdapterHistoryPintu(HistoryPintu.this, dataAktivitasPintus);
                        rvHistory.setAdapter(adappterHistory);
                        rvHistory.setLayoutManager(new LinearLayoutManager(context));

//                        data = response.body().getData();
                        Log.d("data response :", String.valueOf(dataAktivitasPintus.size()));
//                        // Log.d("response truck 2 :" ,data.get(1).getNamaTransporter());
//
//                        setSearch(data);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAktivitasPintu> call, Throwable t) {
                Log.d("fail truck : ", String.valueOf(t.getCause()));

            }
        });
    }
}

