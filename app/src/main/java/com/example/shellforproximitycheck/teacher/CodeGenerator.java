package com.example.shellforproximitycheck.teacher;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class CodeGenerator  {
    public static String code = "";
    static final int LENGTH = 5;
    //static ArrayList <String> codeList = new ArrayList<String>();



    public static void generateCode(){
        Random rg = new Random();
        StringBuilder c = new StringBuilder();

        int i = 0;
        while (i < LENGTH){
            int numOrDigit = rg.nextInt(2);

            if (numOrDigit == 0){
                int num = rg.nextInt(10);
                c.append(num);
            }
            else {
                int letter = rg.nextInt(26);
                c.append((char) ('A' + letter));
            }
            i++;
        }
        //if (!codeList.contains(c)){
            //codeList.add(c);
            //containsCode = true;
        //}
        //boolean containsCode = false;
        //while (!containsCode){
        //}

        code = c.toString();
        //codeText.setText(c);
        //TeacherActivity tA = new TeacherActivity();
        //ExpirationTime eT = new ExpirationTime(tA);
        //eT.setTimer((int) (hour * 60 * 60 + min * 60));
    }

    public static void resetCode(){
        code = "";
    }

}
