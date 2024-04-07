package com.trodev.mypasswordgenerator.activity.browser;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.trodev.mypasswordgenerator.R;

import java.util.ArrayList;

public class BrowserOnlineActivity extends AppCompatActivity {

    RecyclerView rv;
    DatabaseReference reference;
    ArrayList<BrowserOnlineModel> list;
    BrowserOnlineAdapter adapter;
    LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_online);


        getSupportActionBar().setTitle("Database History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv = findViewById(R.id.rv);
        animationView = findViewById(R.id.animationView);
        animationView.loop(true);

        reference = FirebaseDatabase.getInstance().getReference("Online_DB");

        load_data();

    }


    private void load_data() {

        Query query = reference.child("Browser_BD").orderByChild("uid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    animationView.setVisibility(View.VISIBLE);
                    Toast.makeText(BrowserOnlineActivity.this, "no data found", Toast.LENGTH_SHORT).show();
                } else {
                    rv.setVisibility(View.VISIBLE);
                    animationView.setVisibility(View.GONE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BrowserOnlineModel data = snapshot.getValue(BrowserOnlineModel.class);
                        list.add(0, data);
                    }
                    rv.setHasFixedSize(true);
                    rv.setLayoutManager(new LinearLayoutManager(BrowserOnlineActivity.this));
                    adapter = new BrowserOnlineAdapter(BrowserOnlineActivity.this, list, "Browser_BD");
                    rv.setAdapter(adapter);
                    Toast.makeText(BrowserOnlineActivity.this, " data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}