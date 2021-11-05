package com.example.shellforproximitycheck.teacher;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shellforproximitycheck.R;

public class TeacherActivity extends AppCompatActivity {
    private boolean codeGenerated = false;

    private final String codeAppears = "Press Generate to create your code";
    private final String yourCodeIs = "Your code is";
    private final String codeString = "_ _ _ _ _";
    private final String end = "End";
    private final String generate = "Generate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);
        final TextView codeText = findViewById(R.id.generated_code);
        final TextView yourText = findViewById(R.id.yourCode);
        final Button generateButton = findViewById(R.id.generate_btn);

        yourText.setText(codeAppears);
        codeText.setText(codeString);




        generateButton.setOnClickListener(v -> {
            //Generate 5-digit Code
            if (!codeGenerated){
                CodeGenerator.generateCode();
                codeText.setText(CodeGenerator.code);
                //yourText.setVisibility(View.VISIBLE);
                yourText.setText(yourCodeIs);
                generateButton.setText(end);
            }
            //Reset the 5-digit Code
            else {
                CodeGenerator.resetCode();
                codeText.setText(codeString);
                //yourText.setVisibility(View.INVISIBLE);
                yourText.setText(codeAppears);
                generateButton.setText(generate);
            }
            codeGenerated = !codeGenerated;

        });

    }

    /*
    public void updateText(String s){
        countdownText.setText(s);
    }

    public static void setTimer(int seconds, TextView tv){
        new CountDownTimer(seconds* 1000+1000, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                int hours = minutes / 60;
                seconds = seconds % 60;
                String time = ("TIME : " + hours + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
                tv.setText(time);
                //if (mCB != null)
                //mCB.updateMyText(time);
            }
            @Override
            public void onFinish() {
                CodeGenerator.code = "";
            }

        }.start();
    }

    @Override
    public void updateMyText(String myString) {
        TextView tv = (TextView)findViewById(R.id.countdownText);
        System.out.println("tv: " + tv.getText());
        tv.setText(myString);
        tv.setVisibility(View.VISIBLE);
    }

    private void changeActivity(){
        Intent intent = new Intent(this, SetExpirationActivity.class);
        startActivity(intent);
    } */


}