
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

    final String invalidEntry = "Please enter a valid username or password.";
    private ViewModel viewModel;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Button login = (Button)findViewById(R.id.login);
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
                    //Student s1 = new Student(username.hashCode(), "Johnny Student", username.getText().toString(), password.getText().toString() );
                    //dbDAO.insertTeacher(s1);
                    Intent intent1 = new Intent(LoginActivity.this, StudentCodeActivity.class);
                    startActivity(intent1);
                    Toast.makeText(getApplicationContext(), "Greetings!", Toast.LENGTH_SHORT).show();
                    break;
                case "Teacher":
                    //Teacher t1 = new Teacher(username.hashCode(), "Jimmy Teacher", username.getText().toString(), password.getText().toString());
                    //dbDAO.insertTeacher(t1);
                    Intent intent2 = new Intent(LoginActivity.this, CreateGeofenceActivity.class);
                    startActivity(intent2);
                    Toast.makeText(getApplicationContext(), "Hello!", Toast.LENGTH_SHORT).show();

                    break;
            }
        });


    }
}