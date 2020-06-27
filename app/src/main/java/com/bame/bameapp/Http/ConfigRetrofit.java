package com.bame.bameapp.Http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kale on 11/13/18.
 */

public class ConfigRetrofit {

    public static Retrofit getRetrofit(){

        return new Retrofit.Builder().baseUrl("http://rumahari.mamminasa.com/index.php/Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // https://abidzarmakkasau97.000webhostapp.com/index.php/Api/
        // http://192.6.1.114/server_pp/index.php/Api/

    }

    public static ApiService getInstanceRetrofit(){
        return getRetrofit().create(ApiService.class);
    }
}
