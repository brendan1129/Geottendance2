package com.example.shellforproximitycheck.data.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DatabaseRepository {
    private DatabaseDAO mDatabaseDAO;
    private LiveData<List<Student>> mAllStudents;
    private LiveData<List<Teacher>> mAllTeachers;
    private LiveData<Student> mStudentID;
    private LiveData<Teacher> mTeacherID;
    public String mStudentPassword;
    public String mTeacherPassword;
    public int sid;
    public int tid;

    /*private List<Student> mAllStudents;
    private List<Teacher> mAllTeachers;*/

    DatabaseRepository(Application application) {
        GeottendanceDatabase db = GeottendanceDatabase.getDatabase(application);
        mDatabaseDAO = db.getDatabaseDAO();
        mAllStudents = mDatabaseDAO.getAllStudents();
        mAllTeachers = mDatabaseDAO.getAllTeachers();
        mStudentID = mDatabaseDAO.getbySID(sid);
        mTeacherID = mDatabaseDAO.getbyTID(tid);
        mStudentPassword = mDatabaseDAO.getbySEmail(mStudentPassword);
        mTeacherPassword = mDatabaseDAO.getbyTEmail(mTeacherPassword);

    }

    LiveData<List<Student>> getAllStudents() {
        return mAllStudents;
    }

    LiveData<List<Teacher>> getAllTeachers() {
        return mAllTeachers;
    }

    LiveData<Student> getbySID(int sid) {
        return mStudentID;
    }

    LiveData<Teacher> getbyTID(int tid) {
        return mTeacherID;
    }

    String getbySEmail(String email) { return mStudentPassword; }

    String getbyTEmail(String email) { return mTeacherPassword; }

    /*
    List<Student> getAllStudents() {
        return mAllStudents;
    }

    List<Teacher> getAllTeachers() {
        return mAllTeachers;
    }
    */
    void insertStudent(Student student) {
        GeottendanceDatabase.databaseWriteExecutor.execute(() -> {
            mDatabaseDAO.insertStudent(student);
        });
    }

    void insertTeacher(Teacher teacher) {
        GeottendanceDatabase.databaseWriteExecutor.execute(() -> {
            mDatabaseDAO.insertTeacher(teacher);
        });
    }

    void deleteStudent(Student student) {
        GeottendanceDatabase.databaseWriteExecutor.execute(() -> {
            mDatabaseDAO.deleteStudent(student);
        });
    }

    void deleteTeacher(Teacher teacher) {
        GeottendanceDatabase.databaseWriteExecutor.execute(() -> {
            mDatabaseDAO.deleteTeacher(teacher);
        });
    }
}

