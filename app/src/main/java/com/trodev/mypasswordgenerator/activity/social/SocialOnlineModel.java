package com.trodev.mypasswordgenerator.activity.social;

public class SocialOnlineModel {

    String soc_name, soc_link, soc_uname, soc_pass, date, time, uid;
    public SocialOnlineModel() {
    }

    public SocialOnlineModel(String soc_name, String soc_link, String soc_uname, String soc_pass, String date, String time, String uid) {
        this.soc_name = soc_name;
        this.soc_link = soc_link;
        this.soc_uname = soc_uname;
        this.soc_pass = soc_pass;
        this.date = date;
        this.time = time;
        this.uid = uid;
    }

    public String getSoc_name() {
        return soc_name;
    }

    public void setSoc_name(String soc_name) {
        this.soc_name = soc_name;
    }

    public String getSoc_link() {
        return soc_link;
    }

    public void setSoc_link(String soc_link) {
        this.soc_link = soc_link;
    }

    public String getSoc_uname() {
        return soc_uname;
    }

    public void setSoc_uname(String soc_uname) {
        this.soc_uname = soc_uname;
    }

    public String getSoc_pass() {
        return soc_pass;
    }

    public void setSoc_pass(String soc_pass) {
        this.soc_pass = soc_pass;
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
