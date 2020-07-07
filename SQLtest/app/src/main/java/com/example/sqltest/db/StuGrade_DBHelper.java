package com.example.sqltest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class StuGrade_DBHelper extends SQLiteOpenHelper {

    Context context;

    private static final String CREATE_USER = "create table SC (" +
            "sno integer primary key autoincrement," +
            "cno integer unique," +
            "grade smallint)";

    public StuGrade_DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
        db.execSQL("drop table if exists user");
        onCreate(db);
    }
}