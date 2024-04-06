package com.trodev.mypasswordgenerator.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.trodev.mypasswordgenerator.R;
import com.trodev.mypasswordgenerator.fragments.HomeFragment;
import com.trodev.mypasswordgenerator.fragments.NotesFragment;
import com.trodev.mypasswordgenerator.fragments.PassMakerFragment;
import com.trodev.mypasswordgenerator.fragments.ProfileFragment;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class DashboardActivity extends AppCompatActivity {
    SmoothBottomBar smoothBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /*hide actionbar*/
        getSupportActionBar().hide();

        /*init views*/
        smoothBottomBar = findViewById(R.id.bottombar);

        /*When apps start show HomeFragments*/
        setTitle("Dashboard");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new HomeFragment());
        fragmentTransaction.commit();

        /*smooth bar working process*/
        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {

                if (i == 0) {
                    setTitle("Dashboard");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, new HomeFragment());
                    fragmentTransaction.commit();

                } else if (i == 1) {
                    setTitle("Pass Generator");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, new PassMakerFragment());
                    fragmentTransaction.commit();

                } else if (i == 2) {
                    setTitle("Personal Notes");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, new NotesFragment());
                    fragmentTransaction.commit();

                } else if (i == 3) {
                    setTitle("Profile");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, new ProfileFragment());
                    fragmentTransaction.commit();
                }

                return false;
            }
        });
    }
}