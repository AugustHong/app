package com.example.githubtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Menu menu1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.text);
        text.setText("this is index activity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //把選單畫面建出來
        menu1 = menu;   //取得選單的東西（讓一個全堿的變數取得，要用的）
        getMenuInflater().inflate(R.menu.menu1, menu);
        getMenuInflater().inflate(R.menu.menu2, menu);
        menu_hind(1); //讓第2個按鈕看不見
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //選單中的功能
        if(item.getItemId() == R.id.next){go_to_second();}
        if(item.getItemId() == R.id.back){go_to_index();}

        return super.onOptionsItemSelected(item);
    }

    public void go_to_second(){
        setContentView(R.layout.activity_second);  //換成別的layout
        text = (TextView)findViewById(R.id.text2);
        text.setText("this is second activity");
        menu_all_show();   //不知為何一定要先讓他全部先顯示出來才能夠再隱藏
        menu_hind(0);
    }

    public void go_to_index(){
        setContentView(R.layout.activity_main);  //換成別的layout
        text = (TextView)findViewById(R.id.text);
        text.setText("this is index activity");
        menu_all_show();
        menu_hind(1);
    }

    public void menu_hind(int i){
        for(int j = 0; j < menu1.size();j++){   //讓我所想要得選單看不見（其他的要看得見）
            //menu1.getItem(j).setVisible(true);
            if(i == j){menu1.getItem(j).setVisible(false); return;}
        }
    }

    public void menu_all_show(){
        for(int i = 0; i < menu1.size(); i++){menu1.getItem(i).setVisible(true);}
    }
}
