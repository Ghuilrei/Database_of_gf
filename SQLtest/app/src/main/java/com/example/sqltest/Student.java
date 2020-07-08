package com.example.sqltest;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.student.Querygrades_stu;
import com.example.sqltest.student.Updatepd_stu;


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

        // 查询成绩
        if(v.getId()==R.id.button4) {
           //转换布局
            Querygrades_stu.Querygrades_stu(this, Sno);
        }
        // 查询课表
        else if(v.getId() == R.id.button5){
            Intent intent = new Intent(Student.this, Querygrades_stu.class);
            intent.putExtra("Sno", Sno);
            startActivity(intent);
        }
        // 修改密码
        else  if(v.getId() == R.id.button6){
            ShowaDialog();
        }
        // 查询个人信息
        else if(v.getId() == R.id.button7){
            Intent intent = new Intent(Student.this, Updatepd_stu.class);
            intent.putExtra("Sno", Sno);
            startActivityForResult(intent, 1);
        }
    }

    private void ShowaDialog(){
        final EditText editText = new EditText(this);
        new AlertDialog.Builder(this)
            .setTitle("输入旧密码：")
            .setView(editText)
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 如果旧密码正确
                    if (Updatepd_stu.comfirm_pd(Student.this, Sno, editText.getText().toString())){
                        final EditText editText1 = new EditText(Student.this);
                        new AlertDialog.Builder(Student.this)
                                .setTitle("请输入新密码：")
                                .setView(editText1)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // 确认密码
                                        final EditText editText2 = new EditText(Student.this);
                                        new AlertDialog.Builder(Student.this)
                                                .setTitle("确认密码：")
                                                .setView(editText2)
                                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        // TODO Auto-generated method stub
                                                        if (editText1.getText().toString().equals(editText2.getText().toString())) {
                                                            Updatepd_stu.updata_pd(Student.this, Sno, editText1.getText().toString());
                                                            Toast.makeText(Student.this, "修改密码成功。", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            AlertDialog.Builder dialog1 = new AlertDialog.Builder (Student.this);//通过AlertDialog.Builder创建出一个AlertDialog的实例
                                                            dialog1.setTitle("错误！");//设置对话框的标题
                                                            dialog1.setMessage("两次输入密码不一致。");//设置对话框的内容
                                                            dialog1.setCancelable(false);//设置对话框是否可以取消
                                                            dialog1.setPositiveButton("Confirm", null);
                                                            dialog1.show();
                                                        }
                                                    }
                                                })
                                                .setNegativeButton("取消", null).show();

                                    }
                                })
                                .setNegativeButton("取消", null).show();
                    }
                    else {
                        AlertDialog.Builder dialog1 = new AlertDialog.Builder (Student.this);//通过AlertDialog.Builder创建出一个AlertDialog的实例
                        dialog1.setTitle("错误！");//设置对话框的标题
                        dialog1.setMessage("密码错误。");//设置对话框的内容
                        dialog1.setCancelable(false);//设置对话框是否可以取消
                        dialog1.setPositiveButton("Confirm", null);
                        dialog1.show();
                    }
                }
            })
            .setNegativeButton("取消", null).show();
    }
}
