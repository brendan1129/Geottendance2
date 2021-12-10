package com.example.shellforproximitycheck.student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.shellforproximitycheck.R;
import com.example.shellforproximitycheck.data.model.DatabaseDAO;
import com.example.shellforproximitycheck.data.model.GeottendanceDatabase;
import com.example.shellforproximitycheck.data.model.LoginActivity;
import com.example.shellforproximitycheck.data.model.RegistrationActivity;

public class CheckInSuccessActivity extends AppCompatActivity {
    final String success = "You've successfully checked into class!";
    public static String[] currentStudents;
    public static int numberOfStudents = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin_success);
        final TextView tv = findViewById(R.id.success);
        final Button backBtn = findViewById(R.id.student_success_back);
        GeottendanceDatabase db = Room.databaseBuilder(getApplicationContext(), GeottendanceDatabase.class, "Geottendance-Database").allowMainThreadQueries().build();
        DatabaseDAO dbDAO = db.getDatabaseDAO();
        int StudentID = getIntent().getIntExtra("Student", 0); // Finally, retrieving StudentID for update query
        dbDAO.updateIsAttending(StudentID, true); // Update query to change checkedIn boolean
        //dbDAO.updateIsAttending(RegistrationActivity.currentSID, true); Use this as alternative to putExtra and getExtra methods
        currentStudents[numberOfStudents] = dbDAO.getNbySEmail(RegistrationActivity.currentSID);
        numberOfStudents++;

        tv.setText(success);
        backBtn.setOnClickListener (v->
                changeActivityEnterCode()
        );
    }

    private void changeActivityEnterCode(){
        getIntent().removeExtra("Student"); // Clearing extras at "Student" for next registration ( Hopefully )
        RegistrationActivity.currentSID = 0; // Use this to get around "putExtra" and "getExtra" methods
        Intent intent = new Intent(this, StudentCodeActivity.class);
        startActivity(intent);
    }

}