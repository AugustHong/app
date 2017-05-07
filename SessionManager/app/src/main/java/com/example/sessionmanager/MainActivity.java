package com.example.sessionmanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.sessionmanager.MyDBContract;
import com.example.sessionmanager.MyAdapter;
import com.example.sessionmanager.MyDBHelper;



public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;
    private RecyclerView MyRecyclerView;
    private Cursor cursor;
    private MyDBHelper dbHelper;
    TextView edit_name;
    TextView edit_age;
    RadioButton male;
    RadioButton female;
    Button add;
    MyAdapter myAdapter;
    String s = "M";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyRecyclerView = (RecyclerView) findViewById(R.id.all_list_view);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_age = (EditText) findViewById(R.id.edit_age);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new Button.OnClickListener() { //按鈕按下去的功能
            @Override
            public void onClick(View v) {addTo();}
        });

        male.setOnClickListener(new RadioButton.OnClickListener() {  //設定radiobutton
            @Override
            public void onClick(View v) {male.setChecked(true); female.setChecked(false); s = "M";}
        });

        female.setOnClickListener(new RadioButton.OnClickListener() { //設定radiobutton
            @Override
            public void onClick(View v) {female.setChecked(true); male.setChecked(false); s = "F";}
        });


        dbHelper = new MyDBHelper(this);
        mDb = dbHelper.getWritableDatabase();
        addNew("abc", 20, "M");
        addNew("def", 80, "M");
        cursor = getAll();

        myAdapter = new MyAdapter(this, cursor);
        MyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerView.setAdapter(myAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
                //move先不用做什麼動作
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {  //把它滑走（要移除）
                long id = (long) viewHolder.itemView.getTag();
                remove(id);
                myAdapter.swapCursor(getAll());
            }
        }).attachToRecyclerView(MyRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //把選單畫面建出來
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //選單中的功能
        Cursor cur = mDb.query(MyDBContract.MyDBEntry.TABLE_NAME, null, null, null, null, null, MyDBContract.MyDBEntry._ID); ;
        int which_clicked = item.getItemId();
        switch(which_clicked){
            case R.id.sort_by_id:
                cur = mDb.query(MyDBContract.MyDBEntry.TABLE_NAME, null, null, null, null, null, MyDBContract.MyDBEntry._ID);
                break;

            case R.id.sort_by_name:
                cur = mDb.query(MyDBContract.MyDBEntry.TABLE_NAME, null, null, null, null, null, MyDBContract.MyDBEntry.COLUMN_NAME);
                break;

            case R.id.sort_by_age:
                cur = mDb.query(MyDBContract.MyDBEntry.TABLE_NAME, null, null, null, null, null, MyDBContract.MyDBEntry.COLUMN_AGE);
                break;

            case R.id.sort_by_gender:
                cur = mDb.query(MyDBContract.MyDBEntry.TABLE_NAME, null, null, null, null, null, MyDBContract.MyDBEntry.COLUMN_GENDER);
                break;
            default:
                break;
        }
        myAdapter.swapCursor(cur);
        return super.onOptionsItemSelected(item);
    }

    public void addTo() {  //新增資料
        //要有資料（長度不能為0）
        if (edit_name.getText().length() == 0 || edit_age.getText().length() == 0) {return;}

        addNew(edit_name.getText().toString(), Integer.parseInt(edit_age.getText().toString()), s);
        myAdapter.swapCursor(getAll());  //重新顯示出來

        //清空輸入值
        edit_name.setText("");
        edit_age.setText("");

    }

    private Cursor getAll() {  //顯示
        return mDb.query(MyDBContract.MyDBEntry.TABLE_NAME, null, null, null, null, null, MyDBContract.MyDBEntry._ID);
    }

    private void addNew(String name, int age, String gender) {  //把新的資料put進去
        ContentValues cv = new ContentValues();
        cv.put(MyDBContract.MyDBEntry.COLUMN_NAME, name);
        cv.put(MyDBContract.MyDBEntry.COLUMN_AGE, age);
        cv.put(MyDBContract.MyDBEntry.COLUMN_GENDER, gender);
        mDb.insert(MyDBContract.MyDBEntry.TABLE_NAME, null, cv);
    }

    private boolean remove(long id) {  //刪除資料
        return mDb.delete(MyDBContract.MyDBEntry.TABLE_NAME, MyDBContract.MyDBEntry._ID + "=" + id, null) > 0;
    }
}
