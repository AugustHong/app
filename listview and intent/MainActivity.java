package com.example.listview_and_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    String[] str={"second", "third", "fourth(no function)", "fifth(no function)"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView)findViewById(R.id.listview1);  //建出listview

        ArrayList<String> values = new ArrayList<String>();  //作一個array陣列
        for(int i=0;i<4;i++) {values.add(str[i]);}   //加入array中（即要顯示的文字）

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values); //新建adapter（將xml要轉成圖形介面）android.R.layout.simple_list_item_1這個是內建的，也可以自已客製化
        listview.setAdapter(adapter);   //adapter也是可以自已寫的（覆寫），但比較麻煩

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {  //按listview的其中一個元件時
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position; //被按元件位置
                String itemValue = (String) listview.getItemAtPosition(position); //被按元件顯示文字

                switch(itemPosition){
                    case 0:
                        Intent intent = new Intent();   //跳到第2頁
                        intent.setClass(MainActivity.this, secondActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(); //跳到第3頁
                        intent1.setClass(MainActivity.this, thirdActivity.class);
                        startActivity(intent1);
                        break;
                }
            }
        });
    }
}
