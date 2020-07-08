package com.example.sqltest.teacher;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.R;
import com.example.sqltest.tool.FindGrades;


public class Querygrades extends AppCompatActivity implements View.OnClickListener {
    private EditText query_Sno;
    private EditText query_Cno;
    private Button query_btn_confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.querygrade);

        query_Sno = findViewById(R.id.query_Sno);
        query_Cno = findViewById(R.id.query_Cno);
        query_btn_confirm = findViewById(R.id.query_btn_confirm);


        query_btn_confirm.setOnClickListener(this);

    }

    public void db_que() {

        String sno = query_Sno.getText().toString().equals("") ? "NULL": query_Sno.getText().toString();
        String cno = query_Cno.getText().toString().equals("") ? "NULL": query_Cno.getText().toString();
        FindGrades.FindGrades(sno, cno, this);

    }

    @Override
    public void onClick(View v) {
        this.db_que();


    }
}