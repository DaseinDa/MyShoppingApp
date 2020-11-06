package com.my.shopping.app.activitys.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.my.shopping.app.R;
import com.my.shopping.app.beans.GoodsInfo;

import java.util.ArrayList;
import java.util.List;


public class GoodsAdapter extends BaseAdapter {
     //数据源
    private List<GoodsInfo> mList = new ArrayList<>();

    private Context mContext;
    public GoodsAdapter(Context context, List<GoodsInfo> m) {
        super();
        this.mContext = context;
        mList=m;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public GoodsInfo getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder  holder = new  ViewHolder( );

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_goods, parent, false);


        holder.goodsNameTxt = (TextView) convertView.findViewById(R.id.goodsNameTxt);
        holder.goodsPriTxt = (TextView) convertView.findViewById(R.id.goodsPriTxt);
        holder.conTxt = (TextView) convertView.findViewById(R.id.conTxt);
        holder.goodsImg = (ImageView) convertView.findViewById(R.id.goodsImg);
        Glide.with(mContext)
                .load(mList.get(position).getImg())
                .into(holder.goodsImg);
        holder.goodsNameTxt.setText("名称:"+mList.get(position).getGoodsName());
        holder.goodsPriTxt.setText("价格:"+mList.get(position).getMoneySize());
        holder.conTxt.setText("描述:"+mList.get(position).getGoodsCon());
        return convertView;
    }

    class ViewHolder {
        TextView goodsNameTxt;
        TextView goodsPriTxt;
        ImageView goodsImg;
        TextView conTxt;

    }

}
