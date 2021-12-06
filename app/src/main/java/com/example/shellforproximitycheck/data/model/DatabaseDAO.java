package com.example.shellforproximitycheck.data.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DatabaseDAO {
    //Gets the entire list of students
    @Query("SELECT * FROM Student")
    LiveData<List<Student>> getAllStudents();
    //List<Student> getAllStudents();

    //Gets the entire list of teachers
    @Query("SELECT * FROM Teacher")
    LiveData<List<Teacher>> getAllTeachers();
    //List<Teacher> getAllTeachers();

    //Get the student from Student_ID
    @Query("SELECT * FROM Student WHERE Student_ID = :sID")
    LiveData<Student> getbySID(int sID);

    //Get the teacher from Teacher_ID
    @Query("SELECT * FROM Teacher WHERE Teacher_ID = :tID")
    LiveData<Teacher> getbyTID(int tID);

    @Query("SELECT Spassword FROM Student WHERE SUserName = :sEmail")
    String getbySEmail(String sEmail);

    @Query("SELECT Spassword FROM Student WHERE SUserName = :sEmail")
    String getPbySEmail(String sEmail);

    @Query("SELECT Tpassword FROM Teacher WHERE TUserName = :tEmail")
    String getbyTEmail(String tEmail);
    //Insert into the student table
    @Insert
    void insertStudent(Student student);

    //Insert into the student table
    @Insert
    void insertTeacher(Teacher teacher);

    //Delete a student from the Student table
    @Delete
    void deleteStudent(Student student);

    //Delete a teacher from the Teacher table
    @Delete
    void deleteTeacher(Teacher teacher);
}
