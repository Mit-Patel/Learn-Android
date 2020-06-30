package com.learnandy.mitpatel.ledtestapp;

import java.io.Serializable;

public class ListItem  implements Serializable {
    String fname,lname,username,password,email,mobile_no;
    int house_id,user_id,is_admin;

    public ListItem(String fname, String lname, String username, String password, String email, String mobile_no, int house_id, int id, int is_admin) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile_no = mobile_no;
        this.house_id = house_id;
        this.user_id = id;
        this.is_admin = is_admin;
    }
/*
    public ListItem(int user_id, String fname, String lname, String username) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.user_id = user_id;
    }
*/
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public int getHouse_id() {
        return house_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getIs_admin() {
        return is_admin;
    }
}
