package com.bame.bameapp.Model;

import com.google.gson.annotations.SerializedName;

public class DataHistory {

    @SerializedName("id")
    int id;
    @SerializedName("statusPintu")
    private  String statusPintu;
    @SerializedName("statusKunci")
    private  String statusKunci;
    @SerializedName("date")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusPintu() {
        return statusPintu;
    }

    public void setStatusPintu(String statusPintu) {
        this.statusPintu = statusPintu;
    }

    public String getStatusKunci() {
        return statusKunci;
    }

    public void setStatusKunci(String statusKunci) {
        this.statusKunci = statusKunci;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DataHistory{" +
                "id=" + id +
                ", statusPintu='" + statusPintu + '\'' +
                ", statusKunci='" + statusKunci + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
