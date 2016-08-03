package com.example.administrator.searchpicturetool.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.searchpicturetool.config.MySql;
import com.jude.utils.JUtils;

/**
 * Created by wenhuaijun on 2016/1/31 0031.
 */
public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context) {
        super(context, MySql.DATABASE_NAME, null, MySql.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        JUtils.Log("table onCreate");
        db.execSQL(MySql.creatDownloadTable);
        db.execSQL(MySql.createCollectTable);
        db.execSQL(MySql.createRecommendTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        JUtils.Log("onUpgrade");
        db.execSQL(MySql.createRecommendTable);
    }
}
