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
import com.example.sqltest.db.StuGrade_DBHelper;

import java.util.ArrayList;

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
//        System.out.println("sno:'" + query_Sno.getText().toString().equals("") + "' cno:'" + query_Cno.getText().toString().equals("")+"'");
        String sno = query_Sno.getText().toString().equals("") ? "NULL": query_Sno.getText().toString();
        String cno = query_Cno.getText().toString().equals("") ? "NULL": query_Cno.getText().toString();
        StuGrade_DBHelper dbhelper = new StuGrade_DBHelper(this);
        //得到可读的SQLiteDatabase对象
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //参数1：表名
        //参数2：要想显示的列
        //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
        Cursor cursor;
        if (sno.equals("NULL") && cno.equals("NULL")) {
            cursor = db.query("SC", new String[]{"Sno", "Cno", "Grade"}, "1", null, null, null, null);
        } else if (sno.equals("NULL")) {
            cursor = db.query("SC", new String[]{"Sno", "Cno", "Grade"}, "cno=?", new String[]{cno}, null, null, null);
        } else if (cno.equals("NULL")) {
            cursor = db.query("SC", new String[]{"Sno", "Cno", "Grade"}, "sno=?", new String[]{sno}, null, null, null);
        } else {
            cursor = db.query("SC", new String[]{"Sno", "Cno", "Grade"}, "sno=? and cno=?", new String[]{sno, cno}, null, null, null);
        }
        ArrayList<Integer> L_sno = new ArrayList<>();
        ArrayList<Integer> L_cno = new ArrayList<>();
        ArrayList<Integer> L_grade = new ArrayList<>();
        while(cursor.moveToNext()) {
            L_sno.add(cursor.getInt(cursor.getColumnIndex("sno")));
            L_cno.add(cursor.getInt(cursor.getColumnIndex("cno")));
            L_grade.add(cursor.getInt(cursor.getColumnIndex("grade")));
        }
        Intent intent = new Intent(this, Querygrade_res.class);
        intent.putIntegerArrayListExtra("sno", L_sno);
        intent.putIntegerArrayListExtra("cno", L_cno);
        intent.putIntegerArrayListExtra("grade", L_grade);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        this.db_que();


    }
}