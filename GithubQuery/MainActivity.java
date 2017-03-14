package com.example.githubquery;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    EditText edit_url;
    TextView search_url_text;
    TextView show_data_text;
    URL url = null;     //建一個url的物件
    String str_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_url = (EditText)findViewById(R.id.edit_url); //把物件建出來
        search_url_text = (TextView)findViewById(R.id.search_url_text);
        show_data_text = (TextView)findViewById(R.id.show_data_text);

        search_url_text.setTextSize(20.0f);
        show_data_text.setTextSize(20.0f);
        search_url_text.setAutoLinkMask(Linkify.WEB_URLS); //設定超聯結（)內中的是自動連結
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //把選單畫面建出來
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //選單中的功能
        int which_clicked = item.getItemId();
        switch(which_clicked){
            case R.id.search:
                str_url =  "https://api.github.com/search/repositories?q=" + edit_url.getText().toString() + "&sort=stars"; //我們要的網址
                search_url_text.setText(str_url); //顯示文字

                try {     //讓url連到網址
                    url = new URL(str_url);

                    new GithubQueryTask().execute(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.fontsize_14:
                search_url_text.setTextSize(14.0f); //調字型大小
                show_data_text.setTextSize(14.0f);
                Toast.makeText(MainActivity.this, "字型已調成14pt", Toast.LENGTH_SHORT).show(); //小文字框（中間是自已要顯示的文字，其餘2個預設成這樣就好了）
                break;

            case R.id.fontsize_30:
                search_url_text.setTextSize(30.0f);
                show_data_text.setTextSize(30.0f);
                Toast.makeText(MainActivity.this, "字型已調成30pt", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException { //爬網的程式（含下面的也是）
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection(); //建立連線
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");  //找到斷行
            boolean hasInput = scanner.hasNext(); //看是否有下一行
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


    public class GithubQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... params) { //...是把傳進來的全變成陣列的意思
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                show_data_text.setText(githubSearchResults);
            }
        }
    }
}
