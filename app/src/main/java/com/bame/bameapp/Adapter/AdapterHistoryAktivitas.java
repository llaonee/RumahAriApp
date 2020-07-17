package com.bame.bameapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bame.bameapp.Model.DataAktivitasPintu;
import com.bame.bameapp.Model.DataHistory;
import com.bame.bameapp.R;

import java.util.List;

public class AdapterHistoryAktivitas extends RecyclerView.Adapter<AdapterHistoryAktivitas.myHolder> {
    List<DataHistory>data;
    Context context;

    public AdapterHistoryAktivitas(Context context, List<DataHistory> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterHistoryAktivitas.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_aktivitas, parent, false);

        return new AdapterHistoryAktivitas.myHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistoryAktivitas.myHolder holder, int position) {
        DataHistory aktivitas = data.get(position);
        holder.tvStatusPintu.setText(aktivitas.getStatusPintu());
        holder.tvStatusKunci.setText(aktivitas.getStatusKunci());
        holder.tvDate.setText(aktivitas.getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class myHolder extends RecyclerView.ViewHolder {
        private TextView tvStatusPintu, tvStatusKunci, tvDate;


        public myHolder(View itemView) {
            super(itemView);

            tvStatusPintu = itemView.findViewById(R.id.tvStatusPintu);
            tvStatusKunci = itemView.findViewById(R.id.tvStatusKunci);
            tvDate = itemView.findViewById(R.id.tvDate);


        }
    }
}
