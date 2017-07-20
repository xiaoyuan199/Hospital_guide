package com.zone.hospital.SQL.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zone.hospital.SQL.evaluatesql;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 2017/4/21.
 */

public class evalutedao {
    private final evaluatesql myevaluate;
    private final Context contexxt;

    //单例模式

    //1构造私有化方法
    private evalutedao(Context context){
        //创建数据库已有的表结构
        myevaluate=new evaluatesql(context);
        this.contexxt=context;
    }
    //声明一个类的对象
     private  static evalutedao myevalutedao=null;
    //提供一个方法，如果当前类为空则创建一个
    public static evalutedao getInstance(Context context){

        if(myevalutedao==null){
            myevalutedao=new evalutedao(context);
        }
        return myevalutedao;
    }
    public void insert(String evaluate,String time){
        SQLiteDatabase db =  myevaluate.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("content",evaluate);
        values.put("time",time);
        db.insert("evaluate",null,values);
        db.close();
    }


    public List<String> findall(){
        SQLiteDatabase db =  myevaluate.getWritableDatabase();
        Cursor cursor = db.query("evaluate", new String[]{"content"}, null, null, null, null, null);
        ArrayList<String> commentlist=new ArrayList<>();
        while (cursor.moveToNext()){
            commentlist.add(cursor.getString(0)) ;
        }
        cursor.close();
        db.close();
         return   commentlist;
    }
    public List<String> findtime(){
        SQLiteDatabase db =  myevaluate.getWritableDatabase();
        Cursor cursor = db.query("evaluate", new String[]{"time"}, null, null, null, null, null);
        ArrayList<String> commentlist=new ArrayList<>();
        while (cursor.moveToNext()){
            commentlist.add(cursor.getString(0)) ;
        }
        cursor.close();
        db.close();
        return   commentlist;
    }
}

