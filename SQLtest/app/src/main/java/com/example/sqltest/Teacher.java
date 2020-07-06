package com.example.sqltest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Teacher extends AppCompatActivity implements View.OnClickListener {

    private Button button3;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher);
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
            //实例化弹窗
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //定义内容
            AlertDialog alertDialog = builder.create();
            View view = View.inflate(getApplicationContext(),R.layout.addgrade,null);
            alertDialog.setView(view);
            //显示
            alertDialog.show();
        }
        else if (v.getId()==R.id.button8){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //定义内容
            AlertDialog alertDialog = builder.create();
            View view = View.inflate(getApplicationContext(),R.layout.addgrade,null);
            alertDialog.setView(view);
            //显示
            alertDialog.show();
        }
        else if(v.getId() == R.id.button9){
            Intent intent = new Intent(Teacher.this, Findgrades.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.button10){
            Intent intent = new Intent(Teacher.this, Findgrades.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.button11){

        }

    }
}