package com.example.sqltest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Student_DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static String name = "SQLTest";
    private static SQLiteDatabase.CursorFactory factory = null;
    private static int version = 1;

    private static final String CREATE_USER = "create table Student (" +
            "sno char(10) primary key, " +
            "sname char(10) not null, " +
            "ssex char(5) check(ssex='男' or ssex='女'), " +
            "sage smallint check(sage>0), " +
            "sphone char(20), " +
            "sps text)";

    public Student_DBHelper(Context context) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    //数据库第一次创建时被调用
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 软件版本号发生改变时调用
        db.execSQL("drop table if exists Student");
        onCreate(db);
    }
}
