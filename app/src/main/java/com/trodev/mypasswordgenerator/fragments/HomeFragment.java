package com.trodev.mypasswordgenerator.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.trodev.mypasswordgenerator.activity.browser.BrowserCreateActivity;
import com.trodev.mypasswordgenerator.R;
import com.trodev.mypasswordgenerator.activity.browser.BrowserOfflineActivity;
import com.trodev.mypasswordgenerator.activity.browser.BrowserOnlineActivity;
import com.trodev.mypasswordgenerator.activity.payment.PaymentOfflineActivity;
import com.trodev.mypasswordgenerator.activity.payment.PaymentOnlineActivity;
import com.trodev.mypasswordgenerator.onlinedb.User;

public class HomeFragment extends Fragment {

    CardView browser_btn, social_btn, payment_btn;
    TextView profile_name_tv;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    TextView nameET;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        profile_name_tv = view.findViewById(R.id.profile_name_tv);

        browser_btn = view.findViewById(R.id.browser_btn);
        social_btn = view.findViewById(R.id.social_btn);
        payment_btn = view.findViewById(R.id.payment_btn);
        browser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();

            }
        });

        social_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSocialDialog();

            }
        });

        payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaymentDialog();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        profile_name_tv = view.findViewById(R.id.profile_name_tv);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {

                    /*get user name from firebase database*/
                    String uname = userProfile.uname;

                    /*set name on profile*/
                    profile_name_tv.setText(uname);

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

    private void showPaymentDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout layoutOnline = dialog.findViewById(R.id.layouOnline);
        LinearLayout layoutOffline = dialog.findViewById(R.id.layoutOffline);


        layoutOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(getContext(), "welcome! online section", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), PaymentOnlineActivity.class);
                startActivity(intent);

            }
        });

        layoutOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(getContext(), "welcome! offline section", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), PaymentOfflineActivity.class);
                startActivity(intent);

            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }


    private void showSocialDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout layoutOnline = dialog.findViewById(R.id.layouOnline);
        LinearLayout layoutOffline = dialog.findViewById(R.id.layoutOffline);


        layoutOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(getContext(), "welcome! online section", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BrowserOnlineActivity.class);
                startActivity(intent);

            }
        });

        layoutOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(getContext(), "welcome! offline section", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BrowserOfflineActivity.class);
                startActivity(intent);

            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }


    private void showDialog() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout layoutOnline = dialog.findViewById(R.id.layouOnline);
        LinearLayout layoutOffline = dialog.findViewById(R.id.layoutOffline);
        LinearLayout create = dialog.findViewById(R.id.create);


        layoutOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(getContext(), "welcome! online section", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BrowserOnlineActivity.class);
                startActivity(intent);

            }
        });

        layoutOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(getContext(), "welcome! offline section", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BrowserOfflineActivity.class);
                startActivity(intent);

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(getContext(), "welcome! create your credentials", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BrowserCreateActivity.class);
                startActivity(intent);

            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}