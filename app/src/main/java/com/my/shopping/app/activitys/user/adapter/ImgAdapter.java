package com.my.shopping.app.activitys.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.my.shopping.app.R;
import com.my.shopping.app.beans.Address;

import java.util.ArrayList;
import java.util.List;


public class ImgAdapter extends BaseAdapter {
     //数据源
    private List<String> mList = new ArrayList<>();
    private Context mContext;
    public ImgAdapter(Context context, List<String> mList) {
        super();
        this.mContext = context;
        this.mList = mList;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder  holder = new  ViewHolder( );

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_img, parent, false);

        holder.img = (ImageView) convertView.findViewById(R.id.img);
        Glide.with(mContext)
                .load(mList.get(position).toString())
                .into(holder.img);

        return convertView;
    }

    class ViewHolder {
        ImageView img;

    }
    public   interface SizeChend{
        void sizeChendCh();
    }
}
