package com.example.shellforproximitycheck.teacher;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shellforproximitycheck.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GenerateCodeActivity extends AppCompatActivity {
    private boolean codeGenerated;

    //private final String codeAppears = "Press Generate to create your code";
    //private final String yourCodeIs = "Your code is";
    //private final String codeString = "_ _ _ _ _";
    //private final String end = "End";
    //private final String generate = "Generate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);

        //final TextView codeText = findViewById(R.id.generated_code);
        //final TextView yourText = findViewById(R.id.yourCode);
        final Button generateButton = findViewById(R.id.generate_btn);
        final Button testButton = findViewById(R.id.test_teacher);
        BottomNavigationView navView = (BottomNavigationView)findViewById(R.id.teacherNavView);

        CodeGenerator.resetCode();
        codeGenerated = false;
        /*if (CodeGenerator.code.equals("")) {
            codeText.setText(codeString);
            yourText.setText(codeAppears);
            generateButton.setText(generate);
            codeGenerated = false;
        }
        else {
            codeText.setText(CodeGenerator.code);
            yourText.setText(yourCodeIs);
            generateButton.setText(end);
            codeGenerated = true;
        }*/

        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                //R.id.generateCode, R.id.createGeofence)
                //.build();
        //NavController navController = Navigation.findNavController(this, R.id.fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(navView, navController);

        navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_generate:
                    break;
                case R.id.navigation_geofence:
                    Intent a = new Intent(GenerateCodeActivity.this, CreateGeofenceActivity.class);
                    startActivity(a);
                    break;
            }
            return false;
        });

        generateButton.setOnClickListener(v -> {
            CodeGenerator.generateCode();
            //Generate 5-digit Code
            /*if (!codeGenerated){
                CodeGenerator.generateCode();
                //codeText.setText(CodeGenerator.code);
                //yourText.setVisibility(View.VISIBLE);
                //yourText.setText(yourCodeIs);
                //generateButton.setText(end);
            }*/
            //Reset the 5-digit Code
            /*else {
                CodeGenerator.resetCode();
                //codeText.setText(codeString);
                //yourText.setVisibility(View.INVISIBLE);
                //yourText.setText(codeAppears);
                //generateButton.setText(generate);
            }*/
            codeGenerated = !codeGenerated;

            Intent a = new Intent(GenerateCodeActivity.this, ClassSessionActivity.class);
            startActivity(a);
        });

        testButton.setOnClickListener(v ->
            changeActivity()
        );

    }

    /*
    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        BottomNavigationView navBar = findViewById(R.id.teacherNavBar);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.generateCode, R.id.createGeofence)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navBar, navController);

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.generateCode:
                        break;
                    case R.id.createGeofence:
                        changeActivity();
                        break;
                }
                return false;
            }
        });
    } */

    private void changeActivity(){
        Intent intent = new Intent(this, CreateGeofenceActivity.class);
        startActivity(intent);
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
    */



}