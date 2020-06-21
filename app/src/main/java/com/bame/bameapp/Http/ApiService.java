package com.bame.bameapp.Http;




import com.bame.bameapp.Model.ResponseLogin;

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
}
