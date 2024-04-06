package com.trodev.mypasswordgenerator.activity.browser;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.trodev.mypasswordgenerator.R;

public class BrowserOnlineActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_online);
        getSupportActionBar().setTitle("Online Saver");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}