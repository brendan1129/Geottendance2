package com.example.shellforproximitycheck.data.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"Student_ID", "Teacher_ID"})
public class AppRoster {
    public int Student_ID;
    public int Teacher_ID;
}