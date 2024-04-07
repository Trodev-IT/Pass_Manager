package com.trodev.mypasswordgenerator.activity.social;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.trodev.mypasswordgenerator.R;
import com.trodev.mypasswordgenerator.activity.browser.BrowserOnlineModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SocialCreateActivity extends AppCompatActivity {

    TextInputEditText soc_name_ET, soc_url_ET, soc_username_ET, soc_pass_ET;
    TextView soc_name_tv, soc_url_tv, soc_username_tv, soc_pass_tv;
    MaterialButton save_btn;
    String soc_name, soc_link, soc_uname, soc_pass;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_create);

        getSupportActionBar().setTitle("Create Credentials");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /*database location*/
        reference = FirebaseDatabase.getInstance().getReference("Online_DB").child("Social_BD");

        soc_name_tv = findViewById(R.id.soc_name_tv);
        soc_url_tv = findViewById(R.id.soc_url_tv);
        soc_username_tv = findViewById(R.id.soc_username_tv);
        soc_pass_tv = findViewById(R.id.soc_pass_tv);


        soc_name_ET = findViewById(R.id.soc_name_ET);
        soc_url_ET = findViewById(R.id.soc_url_ET);
        soc_username_ET = findViewById(R.id.soc_username_ET);
        soc_pass_ET = findViewById(R.id.soc_pass_ET);

        save_btn = findViewById(R.id.save_soc_online_btn);

        // Set up a text changed listener on the EditText
        soc_name_ET.addTextChangedListener(new TextWatcher() {
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
                soc_name_tv.setText(s.toString());
            }
        });


        soc_url_ET.addTextChangedListener(new TextWatcher() {
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
                soc_url_tv.setText("https://" + s.toString());
            }
        });

        soc_username_ET.addTextChangedListener(new TextWatcher() {
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
                soc_username_tv.setText(s.toString());
            }
        });

        soc_pass_ET.addTextChangedListener(new TextWatcher() {
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
                soc_pass_tv.setText(s.toString());
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_to_db();
            }
        });

    }

    private void save_to_db() {

        /*get text from edit text*/
        soc_name = soc_name_ET.getText().toString().trim();
        soc_link = soc_url_ET.getText().toString().trim();
        soc_uname = soc_username_ET.getText().toString().trim();
        soc_pass = soc_pass_ET.getText().toString().trim();

        /*making condition*/
        if (soc_name.isEmpty()) {
            soc_name_ET.setError("website name required");
        } else if (soc_link.isEmpty()) {
            soc_url_ET.setError("website url required");
        } else if (soc_uname.isEmpty()) {
            soc_username_ET.setError("website username required");
        } else if (soc_pass.isEmpty()) {
            soc_pass_ET.setError("website password required");
        } else {

            Calendar calForDate = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
            String date = currentDate.format(calForDate.getTime());

            Calendar calForTime = Calendar.getInstance();
            SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
            String time = currentTime.format(calForTime.getTime());


            String Key = reference.push().getKey();

            if (Key != null) {
                SocialOnlineModel socialOnlineModel = new SocialOnlineModel(soc_name, soc_link, soc_uname, soc_pass, date, time, FirebaseAuth.getInstance().getCurrentUser().getUid());

                /*these data save on new uid and also store user id*/
                reference.child(Key).setValue(socialOnlineModel);

                /*these data save on user id*/
                // reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(browserOnlineModel);

                Toast.makeText(this, "save successful", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "un-successful", Toast.LENGTH_SHORT).show();
            }

        }

    }
}