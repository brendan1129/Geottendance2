package com.example.shellforproximitycheck.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    @NonNull
    public int isAttending;

    public Student(int Student_ID, String SName, String SUserName, String Spassword, int isAttending ) {
        this.Student_ID = Student_ID;
        this.SName = SName;
        this.SUserName = SUserName;
        this.Spassword = Spassword;
        this.isAttending = isAttending;
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

    public int getIsAttending() { return this.isAttending; }
}