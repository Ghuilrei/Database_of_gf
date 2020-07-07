package com.example.sqltest.teacher;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.R;
import com.example.sqltest.db.StuGrade_DBHelper;

public class Addgrades extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_Sno;
    private EditText editText_Cno;
    private EditText editText_Grade;
    private Button btn_confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgrade);

        editText_Sno = findViewById(R.id.eidttext1);
        editText_Cno = findViewById(R.id.eidttext2);
        editText_Grade = findViewById(R.id.eidttext3);
        btn_confirm = findViewById(R.id.btn_confirm);

        btn_confirm.setOnClickListener(this);

    }

    public void db_add() {
        int sno = Integer.parseInt(editText_Sno.getText().toString());
        int cno = Integer.parseInt(editText_Cno.getText().toString());
        int grade = Integer.parseInt(editText_Grade.getText().toString());
        System.out.println("sno:"+sno+" cno:"+cno+" grade:"+grade);
        ContentValues values = new ContentValues();
        values.put("sno", sno);
        values.put("cno", cno);
        values.put("grade", grade);
        StuGrade_DBHelper dbhelper = new StuGrade_DBHelper(this);
        //得到可写的SQLiteDatabase对象
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        //调用insert方法，将数据插入数据库
        //参数1：表名
        //参数2：如果你想插入空值，那么你必须指定它的所在的列
        db.insert("SC", null, values);
    }

    @Override
    public void onClick(View v) {
        this.db_add();
    }
}
