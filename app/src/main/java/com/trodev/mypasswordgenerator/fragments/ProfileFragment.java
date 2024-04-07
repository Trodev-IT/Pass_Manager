package com.trodev.mypasswordgenerator.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trodev.mypasswordgenerator.offlinedatabase.NotificationActivity;
import com.trodev.mypasswordgenerator.R;
import com.trodev.mypasswordgenerator.activity.LoginActivity;
import com.trodev.mypasswordgenerator.onlinedb.User;

public class ProfileFragment extends Fragment {
    CardView btn_logout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    TextView nameET, vp_tv, email_TV, pass_TV, hd_tv, logout_TV;
    LinearLayout data_ll;
    CardView notification_button;
    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        btn_logout = view.findViewById(R.id.btn_logout);
        nameET = view.findViewById(R.id.nameET);
        vp_tv = view.findViewById(R.id.vp_tv);
        hd_tv = view.findViewById(R.id.hd_tv);
        data_ll = view.findViewById(R.id.data_ll);
        email_TV = view.findViewById(R.id.email_TV);
        pass_TV = view.findViewById(R.id.pass_TV);
        logout_TV = view.findViewById(R.id.logout_TV);

        /*Card view design*/
        notification_button = view.findViewById(R.id.notification_button);

        notification_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                Toast.makeText(getContext(), "log-out successful", Toast.LENGTH_SHORT).show();
                getActivity().finishAffinity();
            }
        });


        data_ll.setVisibility(View.GONE);
        hd_tv.setVisibility(View.GONE);
        vp_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data_ll.setVisibility(view.VISIBLE);
                hd_tv.setVisibility(View.VISIBLE);
                vp_tv.setVisibility(View.GONE);

            }
        });

        hd_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data_ll.setVisibility(view.GONE);
                vp_tv.setVisibility(View.VISIBLE);
                hd_tv.setVisibility(View.GONE);
            }
        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {

                    /*get user name from firebase database*/
                    String uname = userProfile.uname;
                    String uemail = userProfile.email;
                    String pass = userProfile.pass;

                    /*set name on profile*/
                    nameET.setText(uname);
                    email_TV.setText("E-mail:- " + uemail);
                    pass_TV.setText("Password:- " + pass);

                    logout_TV.setText("Good bye "+ uname);

                    /*toast sms*/
                    Toast.makeText(getActivity(), uname + " your data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });




        return view;
    }
}