package com.example.sqltest;

import android.content.Intent;
import android.content.SyncStats;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.teacher.Addgrades;
import com.example.sqltest.teacher.Querygrades;
import com.example.sqltest.teacher.Updategrades;

public class Teacher extends AppCompatActivity implements View.OnClickListener {

    private Button button3;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    String Tno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher);

        Tno = getIntent().getStringExtra("Tno");

        button3 = (Button)findViewById(R.id.button3);//添加学生成绩
        button8 = (Button)findViewById(R.id.button8);//修改学生成绩
        button9 = (Button)findViewById(R.id.button9);//查询学生成绩
        button10 = (Button)findViewById(R.id.button10);//查询个人信息
        button11 = (Button)findViewById(R.id.button11);//修改密码

        button3.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button3){
            // 添加成绩
            Intent intent = new Intent(this, Addgrades.class);
            intent.putExtra("Tno", Tno);
            startActivity(intent);
        }
        else if (v.getId()==R.id.button8){
            // 更改成绩
            Intent intent = new Intent(this, Updategrades.class);
            intent.putExtra("Tno", Tno);
            startActivity(intent);
        }
        else if(v.getId() == R.id.button9){
            // 查询成绩
            Intent intent = new Intent(this, Querygrades.class);
            intent.putExtra("Tno", Tno);
            startActivity(intent);
        }
        else if(v.getId() == R.id.button10){
//            Intent intent = new Intent(Teacher.this, Querygrades_stu.class);
//            intent.putExtra("Tno", Tno);
//            startActivity(intent);
        }
        else if(v.getId()==R.id.button11){

        }

    }
}
