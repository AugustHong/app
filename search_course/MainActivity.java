package com.example.search_course;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View; //把要的物件import進來
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    TextView textview1; //宣告物件出來
    TextView content_text;
    Button ok_button;
    EditText edittext1;

    String[] course = {"chinese", "math", "english", "history", "geography" };
    String[] course_time = {"m1 m2", "f1 f2", "w1 w2", "t1 t2", "r1 r2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview1 = (TextView)findViewById(R.id.textView1); //讓物件有自己的值出來
        content_text = (TextView)findViewById(R.id.content_text);
        ok_button = (Button)findViewById(R.id.ok_button);
        edittext1 =  (EditText) findViewById(R.id.edittext1);

        ok_button.setOnClickListener(new Button.OnClickListener(){ //按鈕按下去的功能
            @Override
            public void onClick(View v) {
                content_text.setText(""); //清除全部的內容
                filter();
            }
        });

        edittext1.setOnClickListener(new View.OnClickListener() { //輸入方塊按下去的功能，按了讓它復原為全部資料顯示
            @Override
            public void onClick(View v) {
                content_text.setText(""); //清除全部的內容
                create_data();
            }

        });

        create_data();
    }

    public void create_data(){
        for(int i = 0; i < course.length ; i++){content_text.append(course[i] + "\n" + course_time[i] + "\n\n");}
    };

    public void filter(){
        for(int i = 0 ; i < course.length ; i++){
            //course[i].equals(edittext1.getText().toString())是比對兩字串
            if(course[i].equals(edittext1.getText().toString())){content_text.append(course[i] + "\n" + course_time[i] + "\n\n");}
        }
    }
}
