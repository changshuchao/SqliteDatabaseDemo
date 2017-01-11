package com.example.changshuchao.sqlitequerydemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.changshuchao.bean.Person;
import com.example.changshuchao.utils.Constant;
import com.example.changshuchao.utils.DbManager;
import com.example.changshuchao.utils.MySqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MySqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //得到一个数据库 对象
        helper = DbManager.getInstance(this);

        //创建数据库
        Button createDb = (Button) findViewById(R.id.createDb);
        createDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();
                db.beginTransaction();//开启事务
               try{
                   for(int i = 1 ; i <= 100 ; i++){
                       String sql = "insert into "+ Constant.TABLE_NAME
                               +" values("+i+",'张三"+i+"',20)";
                       db.execSQL(sql);
                   }
               }catch (Exception e){
                   e.printStackTrace();
               }
                db.setTransactionSuccessful();//提交事务
                db.endTransaction();//关闭事务
                db.close(); 
            }
        });

        //使用API查询数据库
        Button apiQuery = (Button) findViewById(R.id.apiQuery);
        apiQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();
                //select _id,name,age from person where _id>10 group by x having XXX order by XXX
                /*
                * query(String table , String[] columns , String selection ,
                 String[] selectionArgs , String groupBy , String having , String orderBy)
                 String table表示查询的表名
                 String[] columns表示查询表中的字段的名称
                 String selection表示查询条件where语句
                 String[] selectionArgs表示查询条件占位符的取值
                 String groupBy表示分组条件 group by子句
                 String having表示筛选条件 having 子句
                 String orderBy表示排序条件
                * */
                Cursor cursor = db.query(Constant.TABLE_NAME , null , Constant._ID+">10"
                        , null ,null , null ,Constant._ID+" desc");
                List<Person> list = DbManager.CursorToList(cursor);
                for(Person p : list){
                    //遍历所有的实体
                    Log.i("tag", p.toString());
                }
                db.close();
            }
        });

        //使用sql语句查询数据库
        Button sqlQuery = (Button) findViewById(R.id.sqlQuery);
        sqlQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();
                String sql = "select * from "+Constant.TABLE_NAME;
                //结果集
                Cursor cursor = DbManager.selectDataBySql(db , sql , null);
                List<Person> list = DbManager.CursorToList(cursor);
                for(Person p : list){
                    //遍历所有的实体
                    Log.i("tag", p.toString());
                }
                db.close();
            }
        });


        Button showList = (Button)findViewById(R.id.showList);
        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , ShowDataListActivity.class));
            }
        });
    }
}
