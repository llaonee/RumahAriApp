package com.bame.bameapp;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bame.bameapp.Helper.SessionManager;


/**
 * Created by Kale on 11/13/18.
 */

public class BaseApp extends AppCompatActivity {

    protected Context context ;
    protected SessionManager sesi ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this ;
        sesi = new SessionManager(context);


    }
}
