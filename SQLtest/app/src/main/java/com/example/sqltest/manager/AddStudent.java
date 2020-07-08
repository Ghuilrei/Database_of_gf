package com.example.sqltest.manager;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.R;
import com.example.sqltest.db.DBHelper;

import static com.example.androiddemo.tool.StaticTool.GetMD5;

import com.facebook.stetho.Stetho;


public class AddStudent extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private EditText editText_Sno;
    private EditText editText_Sname;
    private EditText editText_Sage;
    private EditText editText_Phone;
    private EditText editText_Password;
    private EditText editText_rPassword;
    private RadioGroup radioGroup_Ssex;
    private Button button_confirm;
    private String Ssex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudent);

        // 数据库连接初始化
        Stetho.initializeWithDefaults(this);

        editText_Sno = findViewById(R.id.addstu_Sno);
        editText_Sname = findViewById(R.id.addstu_Sname);
        editText_Sage = findViewById(R.id.addstu_Sage);
        editText_Phone = findViewById(R.id.addstu_Phone);
        editText_Password = findViewById(R.id.addstu_Password);
        editText_rPassword = findViewById(R.id.addstu_rPassword);
        radioGroup_Ssex = findViewById(R.id.addstu_Ssex);
        button_confirm = findViewById(R.id.addstu_confirm);

        radioGroup_Ssex.setOnCheckedChangeListener(this);
        button_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (Pd_check(editText_Password.getText().toString(), editText_rPassword.getText().toString())) {
            if (db_add()) {
                AlertDialog.Builder dialog = new AlertDialog.Builder (this);//通过AlertDialog.Builder创建出一个AlertDialog的实例
                dialog.setTitle("成功！");//设置对话框的标题
                dialog.setMessage("数据已成功导入");//设置对话框的内容
                dialog.setCancelable(false);//设置对话框是否可以取消
                dialog.setPositiveButton("Confirm", new DialogInterface. OnClickListener() {//确定按钮的点击事件

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.show();
            } else {
                AlertDialog.Builder dialog = new AlertDialog.Builder (this);//通过AlertDialog.Builder创建出一个AlertDialog的实例
                dialog.setTitle("失败！");//设置对话框的标题
                dialog.setMessage("数据未能成功导入");//设置对话框的内容
                dialog.setCancelable(false);//设置对话框是否可以取消
                dialog.setPositiveButton("Confirm", new DialogInterface. OnClickListener() {//确定按钮的点击事件

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.show();
            }
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder (this);//通过AlertDialog.Builder创建出一个AlertDialog的实例
            dialog.setTitle("错误！");//设置对话框的标题
            dialog.setMessage("两次密码输入不一致");//设置对话框的内容
            dialog.setCancelable(false);//设置对话框是否可以取消
            dialog.setPositiveButton("Confirm", null);
            dialog.show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Ssex = checkedId == R.id.addstu_boy ? "男" : "女";
    }

    public boolean Pd_check (String password, String rpassword) {
        return password.equals(rpassword);
    }

    public boolean db_add() {
        try {
            ContentValues values = new ContentValues();
            values.put("sno", editText_Sno.getText().toString());
            values.put("sname", editText_Sname.getText().toString());
            values.put("ssex", Ssex);
            values.put("sage", Integer.getInteger(editText_Sage.getText().toString()));
            values.put("sphone", editText_Phone.getText().toString());
            values.put("sps", GetMD5(editText_Password.getText().toString()));
            DBHelper dbhelper = new DBHelper(this);
            //得到可写的SQLiteDatabase对象
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            //调用insert方法，将数据插入数据库
            //参数1：表名
            //参数2：如果你想插入空值，那么你必须指定它的所在的列
            db.insert("Student", null, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
