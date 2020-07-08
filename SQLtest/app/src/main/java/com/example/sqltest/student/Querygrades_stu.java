package com.example.sqltest.student;


import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.tool.FindGrades;


public class Querygrades_stu extends AppCompatActivity {

    public static void Querygrades_stu(Context context, String sno) {
        FindGrades.FindGrades(sno, "NULL", context);
    }
}
