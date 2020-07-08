package com.example.sqltest.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqltest.db.DBHelper;

import static com.example.androiddemo.tool.StaticTool.GetMD5;

public class Updatepd_stu {

    public static void updata_pd (Context context, String sno, String pd) {
        ContentValues values = new ContentValues();
        pd = GetMD5(pd);
        values.put("sps", pd);
        DBHelper dbhelper = new DBHelper(context);
        //得到可写的SQLiteDatabase对象
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        //调用insert方法，将数据插入数据库
        //参数3：where 子句 "?"是占位符号，对应后面的"1",这和web开发时的语法是一样的
        db.update("Student", values, "sno=?", new String[]{sno});
    }

    public static boolean comfirm_pd (Context context, String sno, String password) {
        DBHelper dbhelper = new DBHelper(context);
        //得到可读的SQLiteDatabase对象
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //参数1：表名
        //参数2：要想显示的列
        //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
        Cursor cursor = db.query("Student", new String[]{"*"}, "sno=? and sps=?", new String[]{sno, GetMD5(password)}, null, null, null);
        return cursor.moveToFirst();
    }
}
