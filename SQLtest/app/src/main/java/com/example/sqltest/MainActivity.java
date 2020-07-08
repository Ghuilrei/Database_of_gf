package com.example.sqltest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqltest.db.DBHelper;
import com.facebook.stetho.Stetho;

import static com.example.androiddemo.tool.StaticTool.GetMD5;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_userid;
    private EditText editText_password;
    private Button button_login;
    String userid;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 数据库连接初始化
        Stetho.initializeWithDefaults(this);

        //初始化信息
        editText_userid = (EditText)findViewById(R.id.main_userid);
        editText_password = (EditText)findViewById(R.id.main_password);
        button_login = (Button)findViewById(R.id.main_btn_login);

        //设置按钮监听器
        button_login.setOnClickListener(this);

    }


    @Override
    public void onClick(View view){

        //获取账号信息
        userid = editText_userid.getText().toString();
        password = editText_password.getText().toString();

        if (check_Null(userid, password)) {
            return;
        }

        // 管理员登陆
        if (userid.equals("admin") && password.equals("root"))  {
            Intent intent = new Intent(this,Manager.class);
            startActivity(intent);
        }

        // 学生登陆
        else if(Integer.parseInt(userid) < 5000) {
            if (Stu_Login(userid, password)) {
                Intent intent1 = new Intent(this, Student.class);
                intent1.putExtra("Sno", userid);
                startActivity(intent1);
            } else {
                AlertDialog.Builder dialog = new AlertDialog.Builder (this);//通过AlertDialog.Builder创建出一个AlertDialog的实例
                dialog.setTitle("登陆失败！");//设置对话框的标题
                dialog.setMessage("账号或密码错误");//设置对话框的内容
                dialog.setCancelable(false);//设置对话框是否可以取消
                dialog.setPositiveButton("Confirm", null);
                dialog.show();
            }
        }

        // 教师登陆
        else if(Integer.parseInt(userid) >= 5000)
        {

            if (Tea_Login(userid, password)) {
                Intent intent2 = new Intent(this, Teacher.class);
                intent2.putExtra("Tno", userid);
                startActivity(intent2);
            } else {
                AlertDialog.Builder dialog = new AlertDialog.Builder (this);//通过AlertDialog.Builder创建出一个AlertDialog的实例
                dialog.setTitle("登陆失败！");//设置对话框的标题
                dialog.setMessage("账号或密码错误");//设置对话框的内容
                dialog.setCancelable(false);//设置对话框是否可以取消
                dialog.setPositiveButton("Confirm", null);
                dialog.show();
            }
        }
    }

    public boolean Stu_Login (String userid, String password) {
        DBHelper dbhelper = new DBHelper(this);
        //得到可读的SQLiteDatabase对象
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //参数1：表名
        //参数2：要想显示的列
        //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
        Cursor cursor = db.query("Student", new String[]{"*"}, "sno=? and sps=?", new String[]{userid, GetMD5(password)}, null, null, null);
        return cursor.moveToFirst();
    }

    public boolean Tea_Login (String userid,  String  password) {
        return userid.equals("6666");
    }

    public boolean check_Null(String userid, String password) {
        if (userid.isEmpty()) {
            Toast.makeText(MainActivity.this, "账号不可为空！", Toast.LENGTH_SHORT).show();
            return true;
        } else if (password.isEmpty()) {
            Toast.makeText(MainActivity.this, "密码不可为空！", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }
}


