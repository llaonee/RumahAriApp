package com.bame.bameapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bame.bameapp.Model.DataAktivitasPintu;
import com.bame.bameapp.R;

import java.util.List;

public class AdapterHistoryPintu extends RecyclerView.Adapter<AdapterHistoryPintu.myHolder> {
    List<DataAktivitasPintu>data;
    Context context;

    public AdapterHistoryPintu(Context context, List<DataAktivitasPintu> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterHistoryPintu.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_history, parent, false);

        return new AdapterHistoryPintu.myHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistoryPintu.myHolder holder, int position) {
        DataAktivitasPintu history = data.get(position);
        holder.tvUsername.setText(history.getUsername());
        holder.tvWaktu.setText(history.getWaktuBuka());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class myHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private TextView tvWaktu;


        public myHolder(View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvWaktu = itemView.findViewById(R.id.tvWaktu);

        }
    }
}
