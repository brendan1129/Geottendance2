package com.example.shellforproximitycheck.data.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.shellforproximitycheck.R;

//Comment to see if it gets pushed to git this time
public class RegistrationActivity extends AppCompatActivity {
    private Student s;
    private Teacher t;
    String sName, sEmail, sPass, tName, tEmail, tPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final EditText name = (EditText) findViewById(R.id.PersonName);
        final EditText email = (EditText) findViewById(R.id.EmailAddress);
        final EditText password = (EditText) findViewById(R.id.Password);
        final Button register = (Button) findViewById(R.id.register);
        final Spinner spinner = (Spinner) findViewById(R.id.dropdown_spinner);

        GeottendanceDatabase db = Room.databaseBuilder(getApplicationContext(), GeottendanceDatabase.class, "Geottendance-Database").allowMainThreadQueries().build();
        ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter.add("Student");
        adapter.add("Teacher");
        Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);

        register.setOnClickListener(v -> {
            DatabaseDAO dbDAO = db.getDatabaseDAO();
            switch(spinner.getSelectedItem().toString()) {
                case "Student":
                    sName = name.getText().toString();
                    sEmail = email.getText().toString();
                    sPass = password.getText().toString();
                    s = new Student(0, sName, sEmail, sPass);
                    dbDAO.insertStudent(s);
                    startActivity(i);
                    break;
                case "Teacher":
                    tName = name.getText().toString();
                    tEmail = email.getText().toString();
                    tPass = password.getText().toString();
                    t = new Teacher(0, tName, tEmail, tPass);
                    dbDAO.insertTeacher(t);
                    startActivity(i);
                    break;
            }

        });
    }
}