package com.example.shellforproximitycheck.student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shellforproximitycheck.R;
import com.example.shellforproximitycheck.teacher.TeacherActivity;

public class CheckInSuccessActivity extends AppCompatActivity {
    final String success = "You've successfully checked into class!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin_success);

        final TextView tv = findViewById(R.id.success);
        final Button backBtn = findViewById(R.id.student_success_back);

        tv.setText(success);

        backBtn.setOnClickListener (v->
                changeActivityEnterCode()
        );
    }

    private void changeActivityEnterCode(){
        Intent intent = new Intent(this, StudentCodeActivity.class);
        startActivity(intent);
    }

}