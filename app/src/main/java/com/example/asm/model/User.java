package com.example.asm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    public String _id;
    public String username;

    public String password;

    public String email;

    public String fullName;

    public User() {
    }

    public User(String _id, String username, String password, String email, String fullName) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }
}
