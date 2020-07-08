package com.example.sqltest.manager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AlertDialog;


import com.example.sqltest.db.DBHelper;
import com.example.sqltest.tool.Grade.Querygrade_res;
import com.example.sqltest.tool.Student.Querystudent_res;

import java.util.ArrayList;

public class Querystudent {

    public static void Querystudent (String sno, String sname, String ssex, String sage, String sphone, Context context) {
        boolean flag = false;

        String selection;

        DBHelper dbhelper = new DBHelper(context);

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        //参数1：表名
        //参数2：要想显示的列
        //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
        sno = sno.equals("") ? "sno":"\""+sno+"\"";
        sname = sname.equals("") ? "sname":"\""+sname+"\"";
        ssex = ssex.equals("") ? "ssex":"\""+ssex+"\"";
        sage = sage.equals("") ? "sage":"\""+sage+"\"";
        sphone = sphone.equals("") ? "sphone":"\""+sphone+"\"";

        selection = "sno="+sno+" and sname="+sname+" and ssex="+ssex+" and sage="+sage+" and sphone="+sphone;
        System.out.println(selection);

        Cursor cursor = db.query("Student", new String[]{"sno", "sname", "ssex", "sage", "sphone"}, selection, null, null, null, null);
        ArrayList<String> L_sno = new ArrayList<>();
        ArrayList<String> L_sname = new ArrayList<>();
        ArrayList<String> L_ssex = new ArrayList<>();
        ArrayList<String> L_sage = new ArrayList<>();
        ArrayList<String> L_sphone = new ArrayList<>();
        while(cursor.moveToNext()) {
            L_sno.add(cursor.getString(cursor.getColumnIndex("sno")));
            L_sname.add(cursor.getString(cursor.getColumnIndex("sname")));
            L_ssex.add(cursor.getString(cursor.getColumnIndex("ssex")));
            L_sage.add(cursor.getString(cursor.getColumnIndex("sage")));
            L_sphone.add(cursor.getString(cursor.getColumnIndex("sphone")));
            flag = true;
        }
        if (flag) {
            Intent intent = new Intent(context, Querystudent_res.class);
            intent.putStringArrayListExtra("sno", L_sno);
            intent.putStringArrayListExtra("sname", L_sname);
            intent.putStringArrayListExtra("ssex", L_ssex);
            intent.putStringArrayListExtra("sage", L_sage);
            intent.putStringArrayListExtra("sphone", L_sphone);
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
