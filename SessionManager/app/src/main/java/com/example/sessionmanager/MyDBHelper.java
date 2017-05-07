package com.example.sessionmanager;

/**
 * Created by 偉德 on 2017/4/29.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.sessionmanager.MyDBContract.*;   //把我們的MyDBContract拿來

public class MyDBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "manager.db";  //資料庫名方
    private static final int DATABASE_VERSION = 1;  //版本都設為1
    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {   //建立資料表
        final String SQL_CREATE_MyDB_TABLE = "CREATE TABLE " +
                MyDBEntry.TABLE_NAME + " (" +
                MyDBEntry._ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MyDBEntry.COLUMN_NAME + " TEXT NOT NULL,  " +
                MyDBEntry.COLUMN_AGE + "  INTEGER NOT NULL, " +
                MyDBEntry.COLUMN_GENDER + " TEXT NOT NULL  " +
                ");  ";
        db.execSQL(SQL_CREATE_MyDB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  //升級（如使用者版本太舊時）
        db.execSQL("DROP TABLE IF EXISTS " + MyDBEntry.TABLE_NAME);
        onCreate(db);
    }
}