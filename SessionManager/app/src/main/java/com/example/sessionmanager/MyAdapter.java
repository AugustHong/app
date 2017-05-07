package com.example.sessionmanager;

/**
 * Created by 偉德 on 2017/4/29.
 */

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sessionmanager.MyDBContract.*;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView idTextView;
        TextView ageTextView;
        TextView genderTextView;


        public MyViewHolder(View itemView) {  //建構
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            idTextView = (TextView) itemView.findViewById(R.id.id_text_view);
            ageTextView = (TextView) itemView.findViewById(R.id.age_text_view);
            genderTextView = (TextView) itemView.findViewById(R.id.gender_text_view);
        }

    }

    public MyAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {  //建的時候拿資料庫的資料
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position)){return;}

        // Update the view holder with the information needed to display
        String name = mCursor.getString(mCursor.getColumnIndex(MyDBContract.MyDBEntry.COLUMN_NAME));
        int age = mCursor.getInt(mCursor.getColumnIndex(MyDBEntry.COLUMN_AGE));
        int id = mCursor.getInt(mCursor.getColumnIndex(MyDBEntry._ID));
        String gender = mCursor.getString(mCursor.getColumnIndex(MyDBContract.MyDBEntry.COLUMN_GENDER));

        //顯示資料
        holder.nameTextView.setText(name);
        holder.idTextView.setText(String.valueOf(id));
        holder.ageTextView.setText(String.valueOf(age));
        holder.genderTextView.setText(gender);

        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {  //算有幾筆
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {  //重新整理此資料並顯示
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }
}
