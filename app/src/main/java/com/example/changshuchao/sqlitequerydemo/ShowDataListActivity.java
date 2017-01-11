package com.example.changshuchao.sqlitequerydemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.changshuchao.utils.Constant;
import com.example.changshuchao.utils.DbManager;

public class ShowDataListActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_list);
        listView = (ListView)findViewById(R.id.lv);

        SQLiteDatabase db = DbManager.getInstance(this).getWritableDatabase();
        Cursor cursor = db.query(Constant.TABLE_NAME,null,Constant._ID+">10",null,null,null,null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.row_item,cursor,
                new String[]{Constant._ID,Constant.NAME,Constant.AGE},
                new int[]{R.id._id,R.id.name,R.id.age},SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        listView.setAdapter(adapter);
        db.close();
    }
}
