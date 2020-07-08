package com.example.sqltest.manager;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.R;
import com.example.sqltest.db.DBHelper;

import static com.example.androiddemo.tool.StaticTool.GetMD5;


public class Student extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private ScrollView scrollView_stu;

    private TextView textView_Password;
    private TextView textView_rPassword;

    private EditText editText_Sno;
    private EditText editText_Sname;
    private EditText editText_Sage;
    private EditText editText_Phone;
    private EditText editText_Password;
    private EditText editText_rPassword;

    private RadioGroup radioGroup_Ssex;
    private RadioButton radioButton_boy;
    private RadioButton radioButton_girl;

    private LinearLayout add_Linearlayout;
    private Button button_confirm;

    private LinearLayout updata_Linearlayout;
    private Button button_update_query;
    private Button button_update_confirm;

    private LinearLayout query_Linearlayout;
    private Button button_query;

    private String Ssex = "";

    private String Sno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_student);
        int src = getIntent().getIntExtra("src", 0);

        scrollView_stu = findViewById(R.id.scrollView_stu);

        textView_Password = findViewById(R.id.textView12);
        textView_rPassword = findViewById(R.id.textView21);

        editText_Sno = findViewById(R.id.addstu_Sno);
        editText_Sname = findViewById(R.id.addstu_Sname);
        editText_Sage = findViewById(R.id.addstu_Sage);
        editText_Phone = findViewById(R.id.addstu_Phone);
        editText_Password = findViewById(R.id.addstu_Password);
        editText_rPassword = findViewById(R.id.addstu_rPassword);

        radioGroup_Ssex = findViewById(R.id.addstu_Ssex);
        radioButton_boy = findViewById(R.id.addstu_boy);
        radioButton_girl = findViewById(R.id.addstu_girl);

        add_Linearlayout = findViewById(R.id.add_Linearlayout);
        button_confirm = findViewById(R.id.bt_infostu_add);

        updata_Linearlayout = findViewById(R.id.updata_Linearlayout);
        button_update_query = findViewById(R.id.bt_info_upque);
        button_update_confirm = findViewById(R.id.bt_info_updata);

        query_Linearlayout = findViewById(R.id.query_Linearlayout);
        button_query = findViewById(R.id.bt_info_query);

        radioGroup_Ssex.setOnCheckedChangeListener(this);
        button_confirm.setOnClickListener(this);
        button_update_query.setOnClickListener(this);
        button_update_confirm.setOnClickListener(this);
        button_query.setOnClickListener(this);

        switch (src) {
            case 1:
                // 添加学生数据
                scrollView_stu.getLayoutParams().height = 840;
                updata_Linearlayout.setVisibility(View.INVISIBLE);
                query_Linearlayout.setVisibility(View.INVISIBLE);
                break;
            case 2:
                // 修改学生数据
                scrollView_stu.getLayoutParams().height = 600;
                add_Linearlayout.setVisibility(View.INVISIBLE);
                query_Linearlayout.setVisibility(View.INVISIBLE);

                editText_Sname.setEnabled(false);
                editText_Sage.setEnabled(false);
                editText_Phone.setEnabled(false);
                radioGroup_Ssex.setEnabled(false);
                radioButton_boy.setEnabled(false);
                radioButton_girl.setEnabled(false);

                textView_Password.setVisibility(View.INVISIBLE);
                editText_Password.setVisibility(View.INVISIBLE);
                textView_rPassword.setVisibility(View.INVISIBLE);
                editText_rPassword.setVisibility(View.INVISIBLE);
                break;
            case 3:
                // 查询学生数据
                scrollView_stu.getLayoutParams().height = 600;
                updata_Linearlayout.setVisibility(View.INVISIBLE);
                add_Linearlayout.setVisibility(View.INVISIBLE);

                textView_Password.setVisibility(View.INVISIBLE);
                editText_Password.setVisibility(View.INVISIBLE);
                textView_rPassword.setVisibility(View.INVISIBLE);
                editText_rPassword.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_infostu_add:
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
                break;
            case R.id.bt_info_upque:
                Sno = editText_Sno.getText().toString();
                db_upque();
                break;
            case R.id.bt_info_updata:
                if (db_update()) {
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
                break;
            case R.id.bt_info_query:
                // TODO OHHHHHHH!
                Sno = editText_Sno.getText().toString();
                String sname = editText_Sname.getText().toString();
                String sage = editText_Sage.getText().toString();
                String sphone = editText_Phone.getText().toString();
                Querystudent.Querystudent(Sno, sname, Ssex, sage, sphone, this);
                break;
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
            values.put("sage", Integer.parseInt(editText_Sage.getText().toString()));
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

    public void db_upque() {
        DBHelper dbhelper = new DBHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //参数1：表名
        //参数2：要想显示的列
        //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
        Cursor cursor = db.query("Student", new String[]{"sname", "ssex", "sage", "sphone"}, "sno=?", new String[]{Sno}, null, null, null);
        if (cursor.moveToNext()) {
            editText_Sname.setText(cursor.getString(cursor.getColumnIndex("sname")));
            editText_Sage.setText(cursor.getString(cursor.getColumnIndex("sage")));
            editText_Phone.setText(cursor.getString(cursor.getColumnIndex("sphone")));
            Ssex = cursor.getString(cursor.getColumnIndex("ssex"));
            if (Ssex.equals("男")) {
                radioButton_boy.setChecked(true);
            } else {
                radioButton_girl.setChecked(true);
            }

            editText_Sno.setEnabled(false);
            editText_Sage.setEnabled(true);
            editText_Phone.setEnabled(true);
            radioGroup_Ssex.setEnabled(true);
            radioButton_boy.setEnabled(true);
            radioButton_girl.setEnabled(true);
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder (this);//通过AlertDialog.Builder创建出一个AlertDialog的实例
            dialog.setTitle("失败！");//设置对话框的标题
            dialog.setMessage("未查询到相关信息");//设置对话框的内容
            dialog.setCancelable(false);//设置对话框是否可以取消
            dialog.setPositiveButton("Confirm", null);
            dialog.show();
        }
    }

    public boolean db_update() {
        try {
            ContentValues values = new ContentValues();
            values.put("ssex", Ssex);
            values.put("sage", Integer.parseInt(editText_Sage.getText().toString()));
            values.put("sphone", editText_Phone.getText().toString());
            DBHelper dbhelper = new DBHelper(this);
            //得到可写的SQLiteDatabase对象
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            //调用insert方法，将数据插入数据库
            //参数1：表名
            //参数2：如果你想插入空值，那么你必须指定它的所在的列
            db.update("Student", values, "sno=?", new String[]{Sno});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
