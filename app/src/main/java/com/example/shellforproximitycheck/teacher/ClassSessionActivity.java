package com.example.shellforproximitycheck.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shellforproximitycheck.R;

public class ClassSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_session);

        final TextView codeText = findViewById(R.id.textCode);
        final Button endSessionButton = findViewById(R.id.endSessionBtn);

        codeText.setText(CodeGenerator.code);

        endSessionButton.setOnClickListener(v -> {
            CodeGenerator.resetCode();
            Intent a = new Intent(ClassSessionActivity.this, CreateGeofenceActivity.class);
            startActivity(a);
        });

    }

}
