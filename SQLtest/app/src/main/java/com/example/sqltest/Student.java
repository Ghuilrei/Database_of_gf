package com.example.sqltest;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.student.Querygrades_stu;


public class Student extends AppCompatActivity implements View.OnClickListener{

    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;

    String Sno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        Sno = getIntent().getStringExtra("Sno");

        button4 = (Button)findViewById(R.id.button4);//查询成绩
        button5 = (Button)findViewById(R.id.button5);//查询课表
        button6 = (Button)findViewById(R.id.button6);//修改密码
        button7 = (Button)findViewById(R.id.button7);//查看个人信息

        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button4) {
           //转换布局
            Querygrades_stu.Querygrades_stu(this, Sno);
        }
        else if(v.getId() == R.id.button5){
            Intent intent = new Intent(Student.this, Querygrades_stu.class);
            intent.putExtra("Sno", Sno);
            startActivity(intent);
        }
        else  if(v.getId() == R.id.button6){
            ShowaDialog();
        }
        else if(v.getId() == R.id.button7){
            Intent intent = new Intent(Student.this, Querygrades_stu.class);
            intent.putExtra("Sno", Sno);
            startActivity(intent);
        }
    }

    private void ShowaDialog(){
        //实例化弹框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
       //定义内容
        AlertDialog alertDialog = builder.create();
        View view = View.inflate(getApplicationContext(),R.layout.updatepassword,null);
        alertDialog.setView(view);
        //显示
        alertDialog.show();
    }
}
