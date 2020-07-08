package com.example.sqltest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static String name = "SQLTest";
    private static SQLiteDatabase.CursorFactory factory = null;
    private static int version = 1;

    private static final String CREATE_STUDENT = "create table Student (" +
            "sno char(10) primary key," +
            "sname char(10) not null," +
            "ssex char(5) check(ssex='男' or ssex='女')," +
            "sage smallint check(sage>0) ," +
            "sphone char(20)," +
            "sps char(20)" +
            ");";
    private static final String CREATE_TEACHER= "create table Teacher (" +
            "tno char(10) primary key," +
            "tname char(10) not null," +
            "tsex char(5) check(tsex='男' or tsex='女')," +
            "tage smallint check(tage>0)," +
            "title char(10)," +
            "tphone char(20)," +
            "tps char(20)" +
            ");";
    private static final String CREATE_COURSE = "create table Course (" +
            "cno char(10) primary key," +
            "cname char(10) not null," +
            "ctype char(2)," +
            "ctime smallint check (ctime>0)" +
            ");";
    private static final String CREATE_TEACH = "create table Teach (" +
            "tno char(10)," +
            "cno char(10)," +
            "classroom char(10)," +
            "classtime char(10)," +
            "foreign key(tno) references Teacher(tno)," +
            "foreign key(cno) references Course(cno)" +
            ");";
    private static final String CREATE_SC = "create table SC (" +
            "sno char(10)," +
            "cno char(10)," +
            "tno char(10)," +
            "grade smallint check(grade>=0 and grade <=100)," +
            "foreign key(sno) references Student(sno)," +
            "foreign key(cno) references Course(cno)," +
            "foreign key(tno) references Teacher(tno)" +
            ");";

    public DBHelper(Context context) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    //数据库第一次创建时被调用
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT);
        db.execSQL(CREATE_TEACHER);
        db.execSQL(CREATE_COURSE);
        db.execSQL(CREATE_TEACH);
        db.execSQL(CREATE_SC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 软件版本号发生改变时调用
        db.execSQL("drop table if exists SC");
        onCreate(db);
    }
}