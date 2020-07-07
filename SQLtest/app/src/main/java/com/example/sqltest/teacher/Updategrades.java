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
import com.example.sqltest.db.StuGrade_DBHelper;

public class Updategrades extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_Sno;
    private EditText editText_Cno;
    private EditText editText_Grade;
    private Button btn_confirm;
    private Button btn_delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updategrade);

        editText_Sno = findViewById(R.id.update_sno);
        editText_Cno = findViewById(R.id.update_cno);
        editText_Grade = findViewById(R.id.update_grade);
        btn_confirm = findViewById(R.id.updata_btn_confirm);
        btn_delete = findViewById(R.id.updata_btn_delete);

        btn_confirm.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
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
        StuGrade_DBHelper dbhelper = new StuGrade_DBHelper(this);
        //得到可写的SQLiteDatabase对象
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        //调用insert方法，将数据插入数据库
        //参数3：where 子句 "?"是占位符号，对应后面的"1",这和web开发时的语法是一样的
        db.update("SC", values, "sno=? and cno=?", new String[]{Integer.toString(sno),Integer.toString(cno)});
    }

    public void db_delete() {

        AlertDialog.Builder dialog = new AlertDialog.Builder (this);//通过AlertDialog.Builder创建出一个AlertDialog的实例

        dialog.setTitle("提示！");//设置对话框的标题
        dialog.setMessage("确认删除"+editText_Sno.getText().toString()+"的"+editText_Cno.getText().toString()+"的成绩吗");//设置对话框的内容
        dialog.setCancelable(false);//设置对话框是否可以取消
        dialog.setPositiveButton("Confirm", new DialogInterface. OnClickListener() {//确定按钮的点击事件

            @Override
            public void onClick(DialogInterface dialog, int which) {
                StuGrade_DBHelper dbhelper = new StuGrade_DBHelper(Updategrades.this);
                //得到可写的SQLiteDatabase对象
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                //调用delete方法，删除数据
                db.delete("SC", "sno=? and cno=?", new String[]{editText_Sno.getText().toString(),editText_Cno.getText().toString()});
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface. OnClickListener() {//取消按钮的点击事件
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.updata_btn_confirm) {
            this.db_updata();
        } else if (v.getId() == R.id.updata_btn_delete)  {
            this.db_delete();
        }
    }
}
