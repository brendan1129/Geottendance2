package com.example.shellforproximitycheck.data.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class StudentWithTeachers {
    @Embedded
    public Student student;
    @Relation(
            parentColumn = "Student_ID",
            entityColumn = "Teacher_ID",
            associateBy = @Junction(AppRoster.class)
    )
    public List<Teacher> teachers;
}
