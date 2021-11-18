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

import com.example.shellforproximitycheck.R;
import com.example.shellforproximitycheck.student.StudentCodeActivity;
import com.example.shellforproximitycheck.teacher.CreateGeofenceActivity;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    final String invalidEntry = "Please enter a valid username or password.";

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Button login = (Button)findViewById(R.id.login);
        ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter.add("Student");
        adapter.add("Teacher");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (username.getText() == null || password.getText() == null || !username.toString().endsWith("@sju.edu")) {
                    Toast.makeText(getApplicationContext(), invalidEntry, Toast.LENGTH_SHORT).show();
                }
                else {*/
                    switch(spinner.getSelectedItem().toString()) {
                        case "Teacher":
                        Intent intent1 = new Intent(LoginActivity.this, StudentCodeActivity.class);
                        startActivity(intent1);
                        break;
                        case "Student":
                        Intent intent2 = new Intent(LoginActivity.this, CreateGeofenceActivity.class);
                        startActivity(intent2);
                        break;
                    }
                }

        });


    }
}

