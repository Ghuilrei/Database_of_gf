package com.example.sqltest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Userdao {

    private  final MyOpenHelper myOpenHelper;
    private  static Userdao userdao = null;

    private Userdao(Context context){
        myOpenHelper = new MyOpenHelper(context);
    }

    public static Userdao getInstance(Context context){
        if (userdao == null){
            userdao = new Userdao(context);
        }
        return userdao;
    }

    public  void  insert(User user){
        SQLiteDatabase sqLiteDatabase = myOpenHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("sno",user.getSname());
        values.put("sname",user.getSname());
        sqLiteDatabase.insert("user",null,values);
        sqLiteDatabase.close();
    }

    //获取数据
    public List<User> findall(){
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("user",new String[]{"sno","sname"},null,null,null,null,null);
        List<User>  userList= new ArrayList<>();
        while(cursor.moveToNext()){
            User uesr =   new User();
            uesr.setSno(cursor.getInt(0));
            uesr.setSname(cursor.getString(1));
            userList.add(uesr);
        }
            db.close();
        return userList;
    }
}
