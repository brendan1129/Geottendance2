package com.example.shellforproximitycheck.data.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

import org.jetbrains.annotations.NotNull;

public class Database {

    @Entity
    public class Student {
        @PrimaryKey
        public int Student_ID;
        @NonNull
        public String SName;
        @NonNull
        public String SUserName;
        @NonNull
        public String Spassword;

    }

    @Entity
    public class Teacher {
        @PrimaryKey
        public int Teacher_ID;
        @NonNull
        public String TName;
        @NonNull
        public String TUserName;
        @NonNull
        public String Tpassword;

    }

    @Entity
    public class Course {
        @PrimaryKey
        public int Course_ID;
        @NonNull
        public String CName;
        @NonNull
        public String Subject;

    }

    @Entity (primaryKeys = {"Student_ID", "Course_ID"})
    public class CourseRoster {
        public int Student_ID;
        public int Course_ID;
    }

    public class CourseWithStudents {
        @Embedded public Course course;
        @Relation(
                parentColumn = "Course_ID",
                entityColumn = "Student_ID",
                associateBy = @Junction(CourseRoster.class)
        )
        public List<Student> students;
    }

    public class StudentWithCourses {
        @Embedded public Student student;
        @Relation(
                parentColumn = "Student_ID",
                entityColumn = "Course_ID",
                associateBy = @Junction(CourseRoster.class)
        )
        public List<Course> courses;
    }
}
