package com.example.sqltest.teacher;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.db.StuGrade_DBHelper;

public class Updatagrades extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void db_updata() {
        int sno = Integer.parseInt(editText_Sno.getText().toString());
        int cno = Integer.parseInt(editText_Cno.getText().toString());
        int grade = Integer.parseInt(editText_Grade.getText().toString());
        System.out.println("sno:"+sno+" cno:"+cno+" grade:"+grade);
        ContentValues values = new ContentValues();
        values.put("sno", sno);
        values.put("cno", cno);
        values.put("grade", grade);
        StuGrade_DBHelper dbhelper = new StuGrade_DBHelper(this, "SQLtest", null, 1);
        //得到可写的SQLiteDatabase对象
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        //调用insert方法，将数据插入数据库
        //参数3：where 子句 "?"是占位符号，对应后面的"1",这和web开发时的语法是一样的
        db.update("MSG", values, "id=?", new String[]{"1"});
        System.out.println("更新了：hello-->my dear!");
    }

    @Override
    public void onClick(View v) {

    }
}
