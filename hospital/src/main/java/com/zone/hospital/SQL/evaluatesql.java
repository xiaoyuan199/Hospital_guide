package com.zone.hospital.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jack on 2017/6/14.
 */

public class evaluatesql extends SQLiteOpenHelper {

    public evaluatesql(Context context) {
        super(context,"evaluate.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table evaluate(content varchar(50),time varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
