package com.example.shellforproximitycheck.data.model;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
//import android.R;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;
import androidx.room.Room;

import com.example.shellforproximitycheck.R;
import com.example.shellforproximitycheck.student.StudentCodeActivity;
import com.example.shellforproximitycheck.teacher.CreateGeofenceActivity;
import com.example.shellforproximitycheck.data.model.DatabaseRepository;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Button login = (Button)findViewById(R.id.login);
        final Button registration = (Button)findViewById(R.id.registration);
        //viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ViewModel.class);
        GeottendanceDatabase db = Room.databaseBuilder(getApplicationContext(), GeottendanceDatabase.class, "Geottendance-Database").allowMainThreadQueries().build();
        ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter.add("Student");
        adapter.add("Teacher");

        login.setEnabled(true);
        login.setOnClickListener(v -> {
            /*if (username.getText() == null || password.getText() == null || !username.toString().endsWith("@sju.edu")) {
                Toast.makeText(getApplicationContext(), invalidEntry, Toast.LENGTH_SHORT).show();
            }
            else {*/
            DatabaseDAO dbDAO = db.getDatabaseDAO();
            switch(spinner.getSelectedItem().toString()) {
                case "Student":
                    if (dbDAO.getbySEmail(username.getText().toString()).equals(password.getText().toString())) {
                        int StudentID = getIntent().getIntExtra("Student", 0); // Retrieving StudentID from the putExtra() in RegistrationActivity
                        Intent intent1 = new Intent(LoginActivity.this, StudentCodeActivity.class);
                        //intent1.putExtra("Student", StudentID); // Passing that StudentID to the StudentCodeActivity
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "Greetings!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid email/password", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "Teacher":
                    if( dbDAO.getbyTEmail(username.getText().toString()).equals(password.getText().toString()) ) {
                        Intent intent2 = new Intent(LoginActivity.this, CreateGeofenceActivity.class);
                        startActivity(intent2);
                        Toast.makeText(getApplicationContext(), "Hello!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid email/password", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        });

        registration.setOnClickListener(v -> {
            Intent intent3 = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent3);
        });


    }
}