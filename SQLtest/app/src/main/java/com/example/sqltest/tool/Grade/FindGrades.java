package com.example.sqltest.tool.Grade;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AlertDialog;


import com.example.sqltest.db.DBHelper;

import java.util.ArrayList;

public class FindGrades {

    public static void FindGrades (String sno, String cno, Context context) {
        boolean flag = false;

        DBHelper dbhelper = new DBHelper(context);

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //参数1：表名
        //参数2：要想显示的列
        //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
        Cursor cursor;
        if (sno.equals("NULL") && cno.equals("NULL")) {
            cursor = db.query("SC", new String[]{"Sno", "Cno", "Tno", "Grade"}, "1", null, null, null, null);
        } else if (sno.equals("NULL")) {
            cursor = db.query("SC", new String[]{"Sno", "Cno", "Tno", "Grade"}, "cno=?", new String[]{cno}, null, null, null);
        } else if (cno.equals("NULL")) {
            cursor = db.query("SC", new String[]{"Sno", "Cno", "Tno", "Grade"}, "sno=?", new String[]{sno}, null, null, null);
        } else {
            cursor = db.query("SC", new String[]{"Sno", "Cno", "Tno", "Grade"}, "sno=? and cno=?", new String[]{sno, cno}, null, null, null);
        }
        ArrayList<String> L_sno = new ArrayList<>();
        ArrayList<String> L_cno = new ArrayList<>();
        ArrayList<String> L_tno = new ArrayList<>();
        ArrayList<String> L_grade = new ArrayList<>();
        while(cursor.moveToNext()) {
            L_sno.add(cursor.getString(cursor.getColumnIndex("sno")));
            L_cno.add(cursor.getString(cursor.getColumnIndex("cno")));
            L_tno.add(cursor.getString(cursor.getColumnIndex("tno")));
            L_grade.add(cursor.getString(cursor.getColumnIndex("grade")));
            flag = true;
        }
        if (flag) {
            Intent intent = new Intent(context, Querygrade_res.class);
            intent.putStringArrayListExtra("sno", L_sno);
            intent.putStringArrayListExtra("cno", L_cno);
            intent.putStringArrayListExtra("tno", L_tno);
            intent.putStringArrayListExtra("grade", L_grade);
            context.startActivity(intent);
        }  else {
            AlertDialog.Builder dialog = new AlertDialog.Builder (context);//通过AlertDialog.Builder创建出一个AlertDialog的实例
            dialog.setTitle("失败！");//设置对话框的标题
            dialog.setMessage("未查询到相关信息");//设置对话框的内容
            dialog.setCancelable(false);//设置对话框是否可以取消
            dialog.setPositiveButton("Confirm", null);
            dialog.show();
        }
    }
}
