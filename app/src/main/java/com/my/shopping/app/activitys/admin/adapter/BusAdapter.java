package com.my.shopping.app.activitys.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.my.shopping.app.R;
import com.my.shopping.app.beans.BusinessInfo;
import com.my.shopping.app.beans.GoodsInfo;

import java.util.ArrayList;
import java.util.List;


public class BusAdapter extends BaseAdapter {
     //数据源
    private List<BusinessInfo> mList = new ArrayList<>();

    private Context mContext;
    public BusAdapter(Context context, List<BusinessInfo> m) {
        super();
        this.mContext = context;
        mList=m;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public BusinessInfo getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder  holder = new  ViewHolder( );

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bus, parent, false);


        holder.goodsNameTxt = (TextView) convertView.findViewById(R.id.goodsNameTxt);
        holder.headImg = (ImageView) convertView.findViewById(R.id.headImg);
        holder.conTxt = (TextView) convertView.findViewById(R.id.conTxt);
        holder.goodsImg = (ImageView) convertView.findViewById(R.id.goodsImg);
        Glide.with(mContext)
                .load(mList.get(position).getImg())
                .into(holder.goodsImg);
        holder.goodsNameTxt.setText(mList.get(position).getName());
        holder.conTxt.setText("描述:"+mList.get(position).getCon());
        Glide.with(mContext).load(mList.get(position).getHeadImg()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.headImg);
        return convertView;
    }

    class ViewHolder {
        TextView goodsNameTxt;
        ImageView goodsImg,headImg;
        TextView conTxt;

    }

}
