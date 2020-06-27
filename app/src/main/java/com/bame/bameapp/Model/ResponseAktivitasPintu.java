package com.bame.bameapp.Model;

import java.util.List;

public class ResponseAktivitasPintu {
    private String pesan;
    private List<DataAktivitasPintu> data;
    private boolean status;

    public void setPesan(String pesan){
        this.pesan = pesan;
    }

    public String getPesan(){
        return pesan;
    }

    public void setData(List<DataAktivitasPintu> data){
        this.data = data;
    }

    public List<DataAktivitasPintu> getData(){
        return data;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "ResponseAktivitasPintu{" +
                        "pesan = '" + pesan + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
