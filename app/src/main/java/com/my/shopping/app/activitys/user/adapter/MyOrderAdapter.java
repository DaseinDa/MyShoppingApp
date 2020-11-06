package com.my.shopping.app.activitys.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.my.shopping.app.R;
import com.my.shopping.app.activitys.user.OrderGoodsListActivity;
import com.my.shopping.app.beans.GoodsInfo;
import com.my.shopping.app.beans.OrderInfo;

import java.util.ArrayList;
import java.util.List;


public class MyOrderAdapter extends BaseAdapter {
     //数据源
    private List<OrderInfo> mList = new ArrayList<>();

    private Context mContext;
    public MyOrderAdapter(Context context, List<OrderInfo> m) {
        super();
        this.mContext = context;
        mList=m;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public OrderInfo getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder  holder = new  ViewHolder( );

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);


        holder.orderTxt = (TextView) convertView.findViewById(R.id.orderTxt);
        holder.moreTxt = (TextView) convertView.findViewById(R.id.moreTxt);
        holder.addressTxt = (TextView) convertView.findViewById(R.id.addressTxt);

        holder.orderTxt.setText("单号:"+mList.get(position).getOrderNO());
        holder.addressTxt.setText("收货地址:"+mList.get(position).getAddressName());
        holder.moreTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,OrderGoodsListActivity.class);
                intent.putExtra("id",mList.get(position).getFkId()+"");
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView orderTxt,addressTxt;
        TextView moreTxt;

    }

}
