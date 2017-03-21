package com.example.listview_and_intent;

/**
 * Created by 偉德 on 2017/3/21.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

public class thirdActivity extends AppCompatActivity {

    Button button1;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button1 = (Button)findViewById(R.id.back);
        text = (TextView)findViewById(R.id.text);
        text.setText("this is third page");

        button1.setOnClickListener(new Button.OnClickListener(){ //按鈕按下去的功能
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(thirdActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
