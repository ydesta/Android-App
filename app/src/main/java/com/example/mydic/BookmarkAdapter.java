package com.example.mydic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class BookmarkAdapter extends BaseAdapter {

    private ListItemlistener itemlistener;
    private ListItemlistener listenerBtnDelete;
    Context mContext;
    ArrayList<String> mSource;

    public BookmarkAdapter(Context context, ArrayList<String> source) {
        this.mSource =source;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.bookmark_layout_item, parent, false);
            viewHolder.textView = convertView.findViewById(R.id.tvWord);
            viewHolder.btnDelete = convertView.findViewById(R.id.btnDelete);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(mSource.get(position));
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemlistener != null)
                    itemlistener.onItemClick(position);
            }
        });
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemlistener != null)
                    listenerBtnDelete.onItemClick(position);
            }
        });

        return convertView;
    }

    public void removeItem(int position) {
        mSource.remove(position);
    }

    class ViewHolder {
        TextView textView;
        ImageView btnDelete;
    }

    public void setOnItemClick(ListItemlistener itemlistener) {
        this.itemlistener = itemlistener;
    }

    public void setOnItemDeleteClick(ListItemlistener itemlistener) {
        this.listenerBtnDelete = itemlistener;
    }
}
