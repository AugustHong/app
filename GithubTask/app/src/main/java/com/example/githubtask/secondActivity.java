package com.example.githubtask;

/**
 * Created by 偉德 on 2017/3/22.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

public class secondActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        text = (TextView) findViewById(R.id.text2);
        text.setText("this is third activity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //把選單畫面建出來
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //選單中的功能
        if (item.getItemId() == R.id.back) {
            Intent intent = new Intent();
            intent.setClass(secondActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}