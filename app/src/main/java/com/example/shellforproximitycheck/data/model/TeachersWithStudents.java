package com.example.shellforproximitycheck.data.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class TeachersWithStudents {
    @Embedded
    public Teacher teacher;
    @Relation(
            parentColumn = "Teacher_ID",
            entityColumn = "Student_ID",
            associateBy = @Junction(AppRoster.class)
    )
    public List<Student> students;
}
