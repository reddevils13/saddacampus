package com.example.win10.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by win10 on 5/8/2018.
 */
@Entity
public class User
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="first_name")
    private String firstname;
    @ColumnInfo(name="last_name")
    private String lastname;
    @ColumnInfo(name="email")
    private String email;

    public User( String firstname, String lastname, String email) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}