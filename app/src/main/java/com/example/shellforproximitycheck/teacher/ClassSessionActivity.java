package com.example.shellforproximitycheck.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shellforproximitycheck.R;
import com.example.shellforproximitycheck.data.model.RegistrationActivity;
import com.example.shellforproximitycheck.student.CheckInSuccessActivity;

import java.util.ArrayList;

public class ClassSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_session);

        final TextView codeText = findViewById(R.id.textCode);
        final Button endSessionButton = findViewById(R.id.endSessionBtn);
        final ListView studentList = (ListView) findViewById(R.id.ListView);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, CheckInSuccessActivity.currentStudents);
        studentList.setAdapter(adapter);
        for ( int i = 0; i < CheckInSuccessActivity.currentStudents.length; i++ ) {
            adapter.add(CheckInSuccessActivity.currentStudents[i]);
        }
        adapter.notifyDataSetChanged(); // Should Listen for changes to list

        codeText.setText(CodeGenerator.code);

        endSessionButton.setOnClickListener(v -> {
            CodeGenerator.resetCode();
            Intent a = new Intent(ClassSessionActivity.this, CreateGeofenceActivity.class);
            startActivity(a);
        });

    }

}
