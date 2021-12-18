package com.example.shellforproximitycheck.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.shellforproximitycheck.R;
import com.example.shellforproximitycheck.data.model.DatabaseDAO;
import com.example.shellforproximitycheck.data.model.GeottendanceDatabase;
import com.example.shellforproximitycheck.data.model.RegistrationActivity;
import com.example.shellforproximitycheck.student.CheckInSuccessActivity;

import java.util.ArrayList;
import java.util.List;

public class ClassSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_session);

        final TextView codeText = findViewById(R.id.textCode);
        final Button endSessionButton = findViewById(R.id.endSessionBtn);
        ListView studentList = (ListView) findViewById(R.id.ListView);


        List<String> names = CheckInSuccessActivity.currentStudents;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        GeottendanceDatabase db = Room.databaseBuilder(getApplicationContext(), GeottendanceDatabase.class, "Geottendance-Database").allowMainThreadQueries().build();
        DatabaseDAO dbDAO = db.getDatabaseDAO();
        adapter.addAll(dbDAO.getStudentNames());
        studentList.setAdapter(adapter);
        adapter.notifyDataSetChanged(); // Should Listen for changes to list

        codeText.setText(CodeGenerator.code);

        endSessionButton.setOnClickListener(v -> {
            CodeGenerator.resetCode();
            Intent a = new Intent(ClassSessionActivity.this, CreateGeofenceActivity.class);
            startActivity(a);
        });

    }

}
