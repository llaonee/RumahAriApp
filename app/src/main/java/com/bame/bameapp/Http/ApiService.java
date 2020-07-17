package com.bame.bameapp.Http;


import com.bame.bameapp.Model.ResponseAktivitasPintu;
import com.bame.bameapp.Model.ResponseHistory;
import com.bame.bameapp.Model.ResponseLogin;
import com.bame.bameapp.Model.ResponsePintu;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Kale on 11/13/18.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> action_login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("pintu")
    Call<ResponsePintu> pintu(
            @Field("userId") int userId
    );

    @FormUrlEncoded
    @POST("update_pintu")
    Call<ResponsePintu> update_pintu(
            @Field("idPintu") int idPintu,
            @Field("updatePintu") int updatePintu
    );
    @FormUrlEncoded
    @POST("get_history")
    Call<ResponseAktivitasPintu> get_history(
            @Field("userId") int userId
    );

    @FormUrlEncoded
    @POST("getAktivitas")
    Call<ResponseHistory> getAktivitas(
            @Field("userId") int userId
    );
}
