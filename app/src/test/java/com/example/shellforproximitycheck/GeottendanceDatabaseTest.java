package com.example.shellforproximitycheck;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
//import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.shellforproximitycheck.data.model.DatabaseDAO;
import com.example.shellforproximitycheck.data.model.GeottendanceDatabase;
import com.example.shellforproximitycheck.data.model.Student;
import com.example.shellforproximitycheck.data.model.Teacher;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

/*@RunWith(AndroidJUnit4.class)
public class GeottendanceDatabaseTest {
    private DatabaseDAO databaseDAO;
    private GeottendanceDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, GeottendanceDatabase.class).allowMainThreadQueries().build();
        databaseDAO = db.getDatabaseDAO();
    }
    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeStudentAndGetAll() throws Exception {
        Student s1 = new Student(1111, "John Doe", "s1User", "s1Pass");
        Teacher t1 = new Teacher(1234, "Jane Smith", "TUser", "TPass");
        databaseDAO.insertStudent(s1);
        databaseDAO.insertTeacher(t1);
        //LiveData<List<Student>> listStudents = databaseDAO.getAllStudents();
        LiveData<List<Student>> listStudents = databaseDAO.getAllStudents();
        LiveData<List<Teacher>> teacherList = databaseDAO.getAllTeachers();
        databaseDAO.deleteStudent(s1);
        databaseDAO.deleteTeacher(t1);
        listStudents = databaseDAO.getAllStudents();
        teacherList = databaseDAO.getAllTeachers();
    }

}*/