package com.bame.bameapp.Model;

import com.google.gson.annotations.SerializedName;

public class DataAktivitasPintu {
    @SerializedName("idAktivitas")
    private int idAktivitas;
    @SerializedName("userId")
    private int userId;
    @SerializedName("waktuBuka")
    private String waktuBuka;

    public int getIdAktivitas() {
        return idAktivitas;
    }

    public void setIdAktivitas(int idAktivitas) {
        this.idAktivitas = idAktivitas;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWaktuBuka() {
        return waktuBuka;
    }

    public void setWaktuBuka(String waktuBuka) {
        this.waktuBuka = waktuBuka;
    }

    @Override
    public String toString() {
        return "DataAktivitasPintu{" +
                "idAktivitas=" + idAktivitas +
                ", userId=" + userId +
                ", waktuBuka='" + waktuBuka + '\'' +
                '}';
    }
}