package com.example.guessnumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;  //亂數

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView;
    Button button1;
    EditText edittext1;
    int r; //r is random

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext1 = (EditText)findViewById(R.id.range);
        button1 = (Button)findViewById(R.id.start);
        button1.setOnClickListener(new Button.OnClickListener(){ //按鈕按下去的功能
            @Override
            public void onClick(View v) {start();}
        });
    }

    public void start(){
        Random ran = new Random();
        r = ran.nextInt(Integer.parseInt(edittext1.getText().toString()))+1;  //產生1到使用者輸入的範圍之亂數
        ArrayList<String> myDataset = new ArrayList<>();  //產生資料陣列
        ArrayList<Boolean> state = new ArrayList<>();   //儲是否有要變灰
        for(int i = 1; i <= Integer.parseInt(edittext1.getText().toString()); i++){myDataset.add(Integer.toString(i)); state.add(false);}

        start_adapter(myDataset, state);  //開始

        Toast.makeText(MainActivity.this, "new game is start", Toast.LENGTH_SHORT).show();
    }

    public void start_adapter(List<String> myDatasetat, List<Boolean> state){
        mAdapter = new MyAdapter(myDatasetat, state);  //加入MyAdapter
        mRecyclerView = (RecyclerView) findViewById(R.id.list_view);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 4);  //有三種layout自行選擇
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);  //選水平 或 垂直
        mRecyclerView.setLayoutManager(layoutManager);  //套用
        mRecyclerView.setAdapter(mAdapter);  //設置adapter

        //加入分隔線
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mData;
        private List<Boolean> m_state;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ViewHolder(View v) {   //顯示元件出來
                super(v);
                mTextView = (TextView) v.findViewById(R.id.show_text);
            }
        }

        public MyAdapter(List<String> data, List<Boolean> state) {mData = data; m_state = state;}

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {   //建立樣子出來
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {   //設定資料
            holder.mTextView.setText(mData.get(position));  //把每一筆的資料輸進去
            switch(String.valueOf(m_state.get(position))){   //我自行設定的，要看他的state    （String.valueOf是把資料轉成字串，因為switch不讓我判斷boolean）
                case "false" :
                    holder.mTextView.setBackgroundColor(0xffffffff);
                    break;
                case "true":
                    if(position == r-1){holder.mTextView.setBackgroundColor(0xfff00000);break;}
                    else{
                        holder.mTextView.setBackgroundColor(0xFF888888);
                        break;
                    }
            }

            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position < r-1){
                        Toast.makeText(MainActivity.this, "too low", Toast.LENGTH_SHORT).show();
                        for(int i = 0; i <= position;i++){m_state.set(i, true);}  //調整他的狀態
                        start_adapter(mData, m_state);   //因為一直無法去控制到別的holder（只能自已），所以解決方法就是重新再把他建一個出來
                        //notifyDataSetChanged(); 這個好像是可以直接重置不用再呼叫了
                    }
                    if(position > r-1){
                        Toast.makeText(MainActivity.this, "too high", Toast.LENGTH_SHORT).show();
                        for(int i = position;i < Integer.parseInt(edittext1.getText().toString());i++){m_state.set(i, true); }
                        start_adapter(mData, m_state);
                    }
                    if(position == r-1){
                        Toast.makeText(MainActivity.this, position+"you're right", Toast.LENGTH_SHORT).show();
                        holder.mTextView.setBackgroundColor(0xfff00000);  //只有他一個，所以直接設定
                        m_state.set(position+1, true);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
