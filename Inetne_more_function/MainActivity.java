package com.example.intent_more_function;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //繼得函式中一定要有(View v)這個東西
    public void onClickOpenWebButton(View v) { //開新網頁
        try {
            Uri web_url = Uri.parse("http://www.google.com.tw");
            Intent intent = new Intent(Intent.ACTION_VIEW, web_url); //Intent.ACTION_VIEW是開網頁，後面的參數網址（uri型態）
            // 其他功能詳見https://developer.android.com/reference/android/content/Intent.html）
            startActivity(intent);
            //if (intent.resolveActivity(getPackageManager()) != null) {startActivity(intent);} //檢查內部有否可以開啟的東西，如果有則執行
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //google其他功能列表 https://developer.android.com/guide/appendix/g-appintents.html
    public void onClickOpenIndexAddressButton(View v) { //這是用地名來跑出地圖的
        String addressString = "國立台灣科技大學";
        Uri addressUri = Uri.parse("geo:0,0?q=" + addressString);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri); //改成這樣
        startActivity(intent);
    }

    public void onClickOpenSecondAddressButton(View v) { //這是用經緯度來跑出地圖的
        Uri addressUri = Uri.parse("geo:47.6, -123.3");
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        startActivity(intent);
    }

    public void shareText(View v) { //分享資料（//分想的type（例如"text/plain" 、text/html; charset=utf-8、 text/rdf 、 image/png））
        ShareCompat.IntentBuilder.from(this).setType("text/plain").setChooserTitle("選擇要開啟的資源").setText("text to show").startChooser();
    }
}
