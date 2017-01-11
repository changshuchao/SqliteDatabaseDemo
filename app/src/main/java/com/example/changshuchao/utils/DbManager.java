package com.example.changshuchao.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.changshuchao.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by changshuchao on 2017/1/9.
 * 因为防止代码直接操纵数据库，所以写了一个DbManager业务类
 */
public class DbManager {
    private static MySqliteHelper helper;

    public static MySqliteHelper getInstance(Context context){
        if(helper == null){
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    /*
    * 根据sql语句查询获得cursor对象
    * @param db 数据库对象
    * @param sql语句
    * @param selectionArgs查询条件的占位符
    * */
    public static Cursor selectDataBySql(SQLiteDatabase db , String sql , String[] selectionArgs){
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(sql , selectionArgs);
        }
        return  cursor;
    }

    /**
     *将查询的Cursor对象转换成list集合
     * @param cursor 游标对象
     * @return 集合对象
     */
    public static List<Person> CursorToList(Cursor cursor){
        List<Person> list = new ArrayList<>();
        //返回时true，表示吓一跳记录依然存在；false，已经读取到最后一行
        while(cursor.moveToNext()){
            //getColumnIndex(String columnName)根据参数中指定的字段名称获取字段下标
            int columnIndex = cursor.getColumnIndex(Constant._ID);
            //getInt(int columnIndex) 根据参数中指定的字段下标，获取对应的int类型的value
            int _id = cursor.getInt(columnIndex);

            String name = cursor.getString(cursor.getColumnIndex(Constant.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(Constant.AGE));

            Person person = new Person(_id , name , age);
            list.add(person);
        }
        return list;
    }
}
