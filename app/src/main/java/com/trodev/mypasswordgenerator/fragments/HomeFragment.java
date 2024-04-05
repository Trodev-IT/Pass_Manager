package com.trodev.mypasswordgenerator.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.trodev.mypasswordgenerator.R;
import com.trodev.mypasswordgenerator.activity.browser.BrowserOfflineActivity;
import com.trodev.mypasswordgenerator.activity.browser.BrowserOnlineActivity;

public class HomeFragment extends Fragment {

    CardView browser_btn;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        browser_btn = view.findViewById(R.id.browser_btn);
        browser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();

            }
        });


        return view;
    }


    private void showDialog() {

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
}