package com.example.shellforproximitycheck.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey (autoGenerate = true)
    public int Student_ID;
    @NonNull
    public String SName;
    @NonNull
    public String SUserName;
    @NonNull
    public String Spassword;

    public Student(int Student_ID, String SName, String SUserName, String Spassword) {
        this.Student_ID = Student_ID;
        this.SName = SName;
        this.SUserName = SUserName;
        this.Spassword = Spassword;
    }

    public int getStudent_ID() {
        return this.Student_ID;
    }

    public String getSName() {
        return this.SName;
    }

    public String getSUserName() {
        return this.SUserName;
    }

    public String getSpassword() {
        return this.Spassword;
    }
}