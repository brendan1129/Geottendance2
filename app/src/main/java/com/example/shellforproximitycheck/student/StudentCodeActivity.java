package com.example.shellforproximitycheck.student;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shellforproximitycheck.R;
import com.example.shellforproximitycheck.teacher.CodeGenerator;
import com.example.shellforproximitycheck.teacher.TeacherActivity;

public class StudentCodeActivity extends AppCompatActivity {
    final String incorrectLength = "The code must be 5-digits long!";
    final String incorrectCode = "Incorrect code.";
    final String accessGranted = "You've been checked into class.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);
        //final TextView enterCodeTV = findViewById(R.id.enterCodeText);
        final EditText password = findViewById(R.id.password);
        //final Button checkinButton = findViewById(R.id.checkin);
        final Button testButton = findViewById(R.id.test_student);
        System.out.println("Code: " + CodeGenerator.code);
        

        /*
        checkinButton.setOnClickListener(v -> {
            validate(password, password.getText().toString());
        }); */

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = password.getText().toString();
                validate(password, text);
            }
        };


        password.addTextChangedListener(afterTextChangedListener);

        testButton.setOnClickListener(v ->
            changeActivityTest()
        );

    }

    private void changeActivityTest(){
        Intent intent = new Intent(this, TeacherActivity.class);
        startActivity(intent);
    }

    private void changeActivitySuccess(){
        Intent intent = new Intent(this, CheckInSuccessActivity.class);
        startActivity(intent);
    }


    void validate(TextView tv, String text){
        if (text.length() == 5) {
            if (text.equals(CodeGenerator.code)){
                Toast.makeText(getApplicationContext(), accessGranted, Toast.LENGTH_SHORT).show();
                changeActivitySuccess();
            } else {
                Toast.makeText(getApplicationContext(), incorrectCode, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), incorrectLength, Toast.LENGTH_SHORT).show();
        }
    }
}
