package com.trodev.mypasswordgenerator;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.trodev.mypasswordgenerator.activity.browser.BrowserOnlineActivity;

import java.util.ArrayList;

public class BrowserOnlineModel {

    String web_name, web_url, web_uname, web_password,  date, time, uid;


    public BrowserOnlineModel() {
    }

    public BrowserOnlineModel(String web_name, String web_url, String web_uname, String web_password, String date, String time, String uid) {
        this.web_name = web_name;
        this.web_url = web_url;
        this.web_uname = web_uname;
        this.web_password = web_password;
        this.date = date;
        this.time = time;
        this.uid = uid;
    }

    public String getWeb_name() {
        return web_name;
    }

    public void setWeb_name(String web_name) {
        this.web_name = web_name;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getWeb_uname() {
        return web_uname;
    }

    public void setWeb_uname(String web_uname) {
        this.web_uname = web_uname;
    }

    public String getWeb_password() {
        return web_password;
    }

    public void setWeb_password(String web_password) {
        this.web_password = web_password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
