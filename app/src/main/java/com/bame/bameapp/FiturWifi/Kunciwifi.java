package com.bame.bameapp.FiturWifi;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bame.bameapp.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Kunciwifi extends AppCompatActivity {

    static String MQTTHOST ="tcp://soldier.cloudmqtt.com:15567";
    static String USERNAME ="mvqmbgfk";
    static String PASSWORD ="9dfVvMvG4S_j";
    String topicStr = "pintuCon";
    MqttAndroidClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunci_wifi);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST,
                clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(Kunciwifi.this, "connected|||", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(Kunciwifi.this,"connected Failed", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void buka(View v) {
        String topic = topicStr;
        String message = "openZ";
        try {
            client.publish(topic, message.getBytes(), 0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void tutup(View v) {
        String topic = topicStr;
        String message = "close";
        try {
            client.publish(topic, message.getBytes(), 0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}