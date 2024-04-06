package com.trodev.mypasswordgenerator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BrowserCreateActivity extends AppCompatActivity {

    TextInputEditText web_name_ET, web_url_ET, web_username_ET, web_pass_ET;
    TextView web_name_tv, web_url_tv, web_username_tv, web_pass_tv;
    MaterialButton save_btn;
    RecyclerView rv;
    String web_name, web_link, web_uname, web_pass;
    DatabaseReference reference, references;
    ArrayList<BrowserOnlineModel> list;
    BrowserOnlineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_create);

        getSupportActionBar().setTitle("Create Credentials");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /*database location*/
        reference = FirebaseDatabase.getInstance().getReference("Online_DB").child("Browser_BD");

        web_name_tv = findViewById(R.id.web_name_tv);
        web_url_tv = findViewById(R.id.web_url_tv);
        web_username_tv = findViewById(R.id.web_username_tv);
        web_pass_tv = findViewById(R.id.web_pass_tv);


        web_name_ET = findViewById(R.id.web_name_ET);
        web_url_ET = findViewById(R.id.web_url_ET);
        web_username_ET = findViewById(R.id.web_username_ET);
        web_pass_ET = findViewById(R.id.web_pass_ET);

        save_btn = findViewById(R.id.save_btn);

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
                web_url_tv.setText("https://" + s.toString());
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

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_to_db();
            }
        });

    }

    private void save_to_db() {

        /*get text from edit text*/
        web_name = web_name_ET.getText().toString().trim();
        web_link = web_url_ET.getText().toString().trim();
        web_uname = web_username_ET.getText().toString().trim();
        web_pass = web_pass_ET.getText().toString().trim();

        /*making condition*/
        if (web_name.isEmpty()) {
            web_name_ET.setError("website name required");
        } else if (web_link.isEmpty()) {
            web_url_ET.setError("website url required");
        } else if (web_uname.isEmpty()) {
            web_username_ET.setError("website username required");
        } else if (web_pass.isEmpty()) {
            web_pass_ET.setError("website password required");
        } else {

            Calendar calForDate = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
            String date = currentDate.format(calForDate.getTime());

            Calendar calForTime = Calendar.getInstance();
            SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
            String time = currentTime.format(calForTime.getTime());


            String Key = reference.push().getKey();

            if (Key != null) {
                BrowserOnlineModel browserOnlineModel = new BrowserOnlineModel(web_name, web_link, web_uname, web_pass, date, time, FirebaseAuth.getInstance().getCurrentUser().getUid());

                /*these data save on new uid and also store user id*/
                reference.child(Key).setValue(browserOnlineModel);

                /*these data save on user id*/
                // reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(browserOnlineModel);

                Toast.makeText(this, "save successful", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "un-successful", Toast.LENGTH_SHORT).show();
            }

        }

    }
}