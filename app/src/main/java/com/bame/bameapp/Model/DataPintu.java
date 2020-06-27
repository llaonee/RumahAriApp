package com.bame.bameapp.Model;

import com.google.gson.annotations.SerializedName;

public class DataPintu {
    @SerializedName("idPintu")
    private int idPintu;
    @SerializedName("statur")
    private int status;

    public int getIdPintu() {
        return idPintu;
    }

    public void setIdPintu(int idPintu) {
        this.idPintu = idPintu;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DataPintu{" +
                "idPintu=" + idPintu +
                ", status=" + status +
                '}';
    }
}
