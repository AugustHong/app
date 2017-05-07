package com.example.sessionmanager;

/**
 * Created by 偉德 on 2017/4/29.
 */
import android.provider.BaseColumns;

public class MyDBContract {
    public static final class MyDBEntry implements BaseColumns {
        public static final String TABLE_NAME = "manager";  //檔名
        public static final String COLUMN_NAME = "Name"; //姓名
        public static final String COLUMN_AGE = "Age";  //年齡
        public static final String COLUMN_GENDER = "Gender";  //性別
    }
}
