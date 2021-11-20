package com.example.shellforproximitycheck.teacher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.*;

import com.example.shellforproximitycheck.R;

public class SetExpirationActivity extends AppCompatActivity { //implements MyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expiration);
        final Spinner hourSpinner = findViewById(R.id.hour_spinner);
        final Spinner minuteSpinner = findViewById(R.id.minute_spinner);
        final Button applyButton = findViewById(R.id.apply_btn);
        final Button backButton = findViewById(R.id.backBtn);



        ArrayAdapter<Integer> hourSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item);
        hourSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(hourSpinnerAdapter);
        populateAdapter(hourSpinnerAdapter, 3);

        ArrayAdapter<Integer> minuteSpinnerAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item);
        minuteSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minuteSpinner.setAdapter(minuteSpinnerAdapter);
        populateAdapter(minuteSpinnerAdapter, 59);


        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !(hourSpinner.getSelectedItem().toString().equals("0") && minuteSpinner.getSelectedItem().toString().equals("0")) ) {
                    //.out.println("Code: " + codeText.getText());
                    CodeGenerator.generateCode();

                    changeActivity();

                }
                else{
                    String empty = "The duration of the code must be set!";
                    Toast.makeText(getApplicationContext(), empty, Toast.LENGTH_LONG).show();
                }


            }
        });



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });


    }


    private void changeActivity(){

    }

    void populateAdapter(ArrayAdapter adapter, int n){
        for (int i = 0; i <= n; i++){
            adapter.add(i);
        }
    }
}