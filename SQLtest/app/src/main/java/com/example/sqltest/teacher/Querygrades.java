package com.example.sqltest.teacher;

import android.app.AppComponentFactory;
import android.content.ContentValues;
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

public class Querygrades  extends AppCompatActivity implements View.OnClickListener{
    private EditText query_Sno;
    private EditText query_Cno;
    private Button query_btn_confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgrade);

        query_Sno = findViewById(R.id.query_Sno);
        query_Cno = findViewById(R.id.query_Cno);
        query_btn_confirm = findViewById(R.id.query_btn_confirm);

        query_btn_confirm.setOnClickListener(this);

    }

    public void db_que() {
        int sno = Integer.parseInt(query_Sno.getText().toString());
        int cno = Integer.parseInt(query_Cno.getText().toString());
        System.out.println("sno:"+sno+" cno:"+cno);
        StuGrade_DBHelper dbhelper = new StuGrade_DBHelper(this, "text_msg", null, 2);
        //得到可读的SQLiteDatabase对象
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //参数1：表名
        //参数2：要想显示的列
        //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
        Cursor cursor = db.query("SC", new String[]{"Cno","Grade"}, "sno=? and cno=?", new String[]{query_Sno.getText().toString(),query_Cno.getText().toString()}, null, null, null);
        System.out.println("查到的数据为：");
        while(cursor.moveToNext()){
            int Cno = cursor.getInt(cursor.getColumnIndex("Cno"));
            int Grade = cursor.getInt(cursor.getColumnIndex("Grade"));
            System.out.println("-->"+Cno+"::::::::::"+Grade);
        }
    }

    @Override
    public void onClick(View v) {
        this.db_que();
    }
}
