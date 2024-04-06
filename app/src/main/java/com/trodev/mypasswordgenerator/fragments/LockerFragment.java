package com.trodev.mypasswordgenerator.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.trodev.mypasswordgenerator.BrowserOnlineAdapter;
import com.trodev.mypasswordgenerator.BrowserOnlineModel;
import com.trodev.mypasswordgenerator.R;
import com.trodev.mypasswordgenerator.activity.browser.BrowserOnlineActivity;

import java.util.ArrayList;

public class LockerFragment extends Fragment {

    RecyclerView rv;
    DatabaseReference reference;
    ArrayList<BrowserOnlineModel> list;
    BrowserOnlineAdapter adapter;

    public LockerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_locker, container, false);

        rv = view.findViewById(R.id.rv);

        reference = FirebaseDatabase.getInstance().getReference("Online_DB");

        load_data();

        return view;
    }


    private void load_data() {

        Query query = reference.child("Browser_BD").orderByChild("uid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    Toast.makeText(getContext(), "no data found", Toast.LENGTH_SHORT).show();
                } else {
                    rv.setVisibility(View.VISIBLE);
                    //animationView.setVisibility(View.GONE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        BrowserOnlineModel data = snapshot.getValue(BrowserOnlineModel.class);
                        list.add(0, data);
                    }
                    rv.setHasFixedSize(true);
                    rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new BrowserOnlineAdapter(getContext(), list, "Browser_BD");
                    rv.setAdapter(adapter);
                    Toast.makeText(getContext(), " data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}