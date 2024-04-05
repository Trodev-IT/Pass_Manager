package com.trodev.mypasswordgenerator.activity.browser;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.trodev.mypasswordgenerator.R;

public class BrowserOnlineActivity extends AppCompatActivity {

    TextInputEditText web_name_ET, web_url_ET, web_username_ET, web_pass_ET;
    TextView web_name_tv, web_url_tv, web_username_tv, web_pass_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_online);
        getSupportActionBar().setTitle("Online Saver");

        web_name_tv = findViewById(R.id.web_name_tv);
        web_url_tv = findViewById(R.id.web_url_tv);
        web_username_tv = findViewById(R.id.web_username_tv);
        web_pass_tv = findViewById(R.id.web_pass_tv);



        web_name_ET = findViewById(R.id.web_name_ET);
        web_url_ET = findViewById(R.id.web_url_ET);
        web_username_ET = findViewById(R.id.web_username_ET);
        web_pass_ET = findViewById(R.id.web_pass_ET);

        // Set up a text changed listener on the EditText
        web_name_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called to notify you that, within s,
                // the count characters beginning at start are about to be replaced by new text with length after.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called to notify you that, within s,
                // the count characters beginning at start have just replaced old text that had length before.
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called to notify you that, somewhere within s, the text has been changed.
                // Updating the TextView with the text in the EditText
                web_name_tv.setText(s.toString());
            }
        });


        web_url_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called to notify you that, within s,
                // the count characters beginning at start are about to be replaced by new text with length after.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called to notify you that, within s,
                // the count characters beginning at start have just replaced old text that had length before.
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called to notify you that, somewhere within s, the text has been changed.
                // Updating the TextView with the text in the EditText
                web_url_tv.setText("https://"+s.toString());
            }
        });

        web_username_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called to notify you that, within s,
                // the count characters beginning at start are about to be replaced by new text with length after.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called to notify you that, within s,
                // the count characters beginning at start have just replaced old text that had length before.
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called to notify you that, somewhere within s, the text has been changed.
                // Updating the TextView with the text in the EditText
                web_username_tv.setText(s.toString());
            }
        });

        web_pass_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called to notify you that, within s,
                // the count characters beginning at start are about to be replaced by new text with length after.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called to notify you that, within s,
                // the count characters beginning at start have just replaced old text that had length before.
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called to notify you that, somewhere within s, the text has been changed.
                // Updating the TextView with the text in the EditText
                web_pass_tv.setText(s.toString());
            }
        });

    }
}