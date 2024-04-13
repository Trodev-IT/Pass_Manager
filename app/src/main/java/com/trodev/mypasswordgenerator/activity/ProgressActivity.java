package com.trodev.mypasswordgenerator.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.trodev.mypasswordgenerator.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProgressActivity extends AppCompatActivity {

    private TextView dayTextView, timeTextView;
    private ProgressBar progressBar;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "prefs";
    private static final String DAYS_PASSED = "daysPassed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_progress);
        dayTextView = findViewById(R.id.dayTextView);
        timeTextView = findViewById(R.id.timeTextView);
        progressBar = findViewById(R.id.progressBar);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int daysPassed = sharedPreferences.getInt(DAYS_PASSED, 1);
        dayTextView.setText(String.format(Locale.getDefault(), "Day %d", daysPassed));

        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            long timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            int updatedTime = (int) (timeInMilliseconds / 1000);
            int progress = updatedTime % 86400; // Seconds in a day
            int days = updatedTime / 86400;

            if (days > 0) {
                int daysPassed = sharedPreferences.getInt(DAYS_PASSED, 1) + days;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(DAYS_PASSED, daysPassed);
                editor.apply();
                dayTextView.setText(String.format(Locale.getDefault(), "Day %d", daysPassed));
                startTime += days * 86400 * 1000L; // Reset startTime for the next day
            }

            progressBar.setProgress(progress);
            timeTextView.setText(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date(timeInMilliseconds)));

            customHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        customHandler.removeCallbacks(updateTimerThread);
    }

    @Override
    protected void onResume() {
        super.onResume();
        customHandler.postDelayed(updateTimerThread, 0);
    }
}