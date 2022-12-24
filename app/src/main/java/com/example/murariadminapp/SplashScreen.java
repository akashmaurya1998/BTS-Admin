package com.example.murariadminapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.murariadminapp.Model.States;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {

    RequestQueue queue;
    List<String> statesArray = new ArrayList<>();
    States states;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        queue = Volley.newRequestQueue(this);
        states = new States();

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        getStates();

        new Handler().postDelayed(() -> {
            states.setStates(statesArray);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }, 3000);
    }

    private void getStates(){
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                getString(R.string.states_url),
                null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            statesArray.add(response.getJSONObject(i).getString("name"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("States", statesArray.get(1));
                },
                error -> Log.e("States", error.getMessage())
        ){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> customHeader = new HashMap<>();
                customHeader.put(
                        getString(R.string.header_key_for_states_api),
                        getString(R.string.header_value_for_states_api));
                return customHeader;
            }
        };

        queue.add(request);
    }
}