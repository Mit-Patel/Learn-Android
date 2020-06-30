package com.learnandy.mitpatel.iotmodules2;

public class UserDataFields {
    String fname,lname,username,password,email,mobile_no;
    int house_id,user_id,is_admin;

    public UserDataFields(String fname, String lname, String username, String password, String email, String mobile_no, int user_id) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile_no = mobile_no;
        this.user_id = user_id;
    }

    public UserDataFields(String fname, String lname, String username, String password, String email, String mobile_no, int house_id, int user_id, int is_admin) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile_no = mobile_no;
        this.house_id = house_id;
        this.user_id = user_id;
        this.is_admin = is_admin;
    }

    public UserDataFields(String fname, String lname, String username, String password, String email, String mobile_no, int house_id, int user_id) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile_no = mobile_no;
        this.house_id = house_id;
        this.user_id = user_id;
    }

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
