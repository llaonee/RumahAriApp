package com.bame.bameapp.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.media.tv.TvView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bame.bameapp.BaseApp;
import com.bame.bameapp.FiturWifi.Kunciwifi;
import com.bame.bameapp.Http.ApiService;
import com.bame.bameapp.Http.ConfigRetrofit;
import com.bame.bameapp.Model.DataPintu;
import com.bame.bameapp.Model.ResponsePintu;
import com.bame.bameapp.R;
import com.bame.bameapp.bluetoothFitur.MainActivityBluetooth;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseApp {
    CardView bPintu, bStatus, bHistory;
    TextView tvUsername, tvStatus;
    int updatePintu, statusPintu, idPintu;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bHistory = findViewById(R.id.bHistory);
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
        bHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, History.class));
            }
        });
        
        bPintu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                openDIalog();
                Date HariSekarang = new Date( );
                SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd ' ' hh:mm:ss ");
                tvStatus.setText(ft.format(HariSekarang));

//
            }
        });

    }

    private void openDIalog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialogpintu);
        dialog.show();
        Button btnBluetooth = (Button) dialog.findViewById(R.id.btn_bluetooth);
        Button btnWifi = (Button) dialog.findViewById(R.id.btn_wifi);
        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText("Pilih Kontroler Pintu anda menggunakan ");

        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, MainActivityBluetooth.class));
                dialog.dismiss();
                pintu(view);
            }
        });

        btnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Kunciwifi.class));
                dialog.dismiss();
                pintu(view);
            }
        });

//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                ApiService api = ConfigRetrofit.getInstanceRetrofit();
//                Call<ResponsePintu> call = api.update_pintu(idPintu, updatePintu);
//                call.enqueue(new Callback<ResponsePintu>() {
//                    @Override
//                    public void onResponse(Call<ResponsePintu> call, Response<ResponsePintu> response) {
//                        Log.d("response login : ", response.message());
//                        if (response.isSuccessful()) {
//                            boolean result = response.body().isStatus();
//                            String pesan = response.body().getPesan();
//                            if (result == true) {
//                                Toast.makeText(context, pesan, Toast.LENGTH_LONG).show();
//
//                            } else {
//                                Toast.makeText(context, pesan, Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponsePintu> call, Throwable t) {
//
//                        Log.d("error login : ", String.valueOf(t.getCause()));
//                    }
//                });
//                dialog.dismiss();
//            }
//        });

    }

    private void pintu(final View view){
                ApiService api = ConfigRetrofit.getInstanceRetrofit();
                Call<ResponsePintu> call = api.pintu(sesi.getUserId() );
                call.enqueue(new Callback<ResponsePintu>() {
                    @Override
                    public void onResponse(Call<ResponsePintu> call, Response<ResponsePintu> response) {
                        Log.d("response login : ", response.message());
                        if (response.isSuccessful()) {
                            boolean result = response.body().isStatus();
                            String pesan = response.body().getPesan();
                            if (result == true) {
                                List<DataPintu> data = response.body().getData();
                                 statusPintu = data.get(0).getStatus();
                                 idPintu = data.get(0).getIdPintu();
                                Date HariSekarang = new Date( );
                                SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd ' ' hh:mm:ss ");
                                tvStatus.setText(ft.format(HariSekarang));


                                Log.d("error login1 : ", String.valueOf(idPintu + " " +statusPintu));
                                Snackbar.make(view, "Berhasil", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                            } else {
                                List<DataPintu> data = response.body().getData();
                                statusPintu = data.get(0).getStatus();
                                idPintu = data.get(0).getIdPintu();

                                Log.d("error login3 : ", String.valueOf(idPintu + " " +statusPintu));
                                Snackbar.make(view, pesan, Snackbar.LENGTH_LONG)
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
