package com.example.sqltest.teacher;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.R;
import com.example.sqltest.db.DBHelper;

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
        editText_Cno = findViewById(R.id.main_password);
        editText_Grade = findViewById(R.id.eidttext3);
        btn_confirm = findViewById(R.id.btn_confirm);

        btn_confirm.setOnClickListener(this);

    }

    public boolean db_add() {
        String sno = editText_Sno.getText().toString();
        String cno = editText_Cno.getText().toString();
        String grade =editText_Grade.getText().toString();
        System.out.println("sno:"+sno+" cno:"+cno+" grade:"+grade);
        ContentValues values = new ContentValues();
        values.put("sno", sno);
        values.put("cno", cno);
        values.put("grade", grade);
        DBHelper dbhelper = new DBHelper(this);
        //得到可写的SQLiteDatabase对象
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        //调用insert方法，将数据插入数据库
        //参数1：表名
        //参数2：如果你想插入空值，那么你必须指定它的所在的列
        if (db.insert("SC", null, values) == -1) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
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
    }
}
