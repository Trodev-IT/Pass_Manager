package com.trodev.mypasswordgenerator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.trodev.mypasswordgenerator.R;
import com.trodev.mypasswordgenerator.activity.payment.PaymentOnlineActivity;

public class NotificationActivity extends AppCompatActivity {

    private TextView switchStateText, Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getSupportActionBar().setTitle("Notification");
        switchStateText = findViewById(R.id.switchStateText);
        Tv = findViewById(R.id.Tv);

        // Get switch state from Intent
        Intent intent = getIntent();
        PaymentOnlineActivity.SwitchState state = intent.getParcelableExtra("switch_state");

        if (state != null) {
            boolean isChecked = state.isChecked;
            String text = "Switch is " + (isChecked ? "On" : "Off");
            switchStateText.setText(text);
            Tv.setVisibility(View.VISIBLE);
        } else {
            // Handle case where state is null
            Tv.setVisibility(View.GONE);
        }
    }
}