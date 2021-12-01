package com.example.shellforproximitycheck.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Teacher {
    @PrimaryKey(autoGenerate = true)
    public int Teacher_ID;
    @NonNull
    public String TName;
    @NonNull
    public String TUserName;
    @NonNull
    public String Tpassword;

    public Teacher(int Teacher_ID, String TName, String TUserName, String Tpassword) {
        this.Teacher_ID = Teacher_ID;
        this.TName = TName;
        this.TUserName = TUserName;
        this.Tpassword = Tpassword;
    }

    public int getTeacher_ID() {
        return this.Teacher_ID;
    }

    public String getTName() {
        return this.TName;
    }

    public String getTUserName() {
        return this.TUserName;
    }

    public String getTpassword() {
        return this.Tpassword;
    }

}