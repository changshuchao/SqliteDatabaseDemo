package com.example.changshuchao.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by changshuchao on 2017/1/9.
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context){
        super(context , Constant.DATABASE_NAME , null , Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //数据库创建的时候回掉的
        String sql = "create table "+Constant.TABLE_NAME+
                "("+Constant._ID+" Integer primary key,"+Constant.NAME
                +" varchar(10),"+Constant.AGE+" Integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

