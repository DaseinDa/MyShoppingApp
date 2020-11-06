package com.my.shopping.app.activitys.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.my.shopping.app.activitys.admin.UserIDetailActivity;
import com.my.shopping.app.activitys.user.UserOrderStateGoodsListActivity;
import com.my.shopping.app.beans.OrderInfo;
import com.my.shopping.app.beans.ShareBean;

import java.util.ArrayList;
import java.util.List;


public class ShareAdapter extends BaseAdapter {
     //数据源
    private List<ShareBean> mList = new ArrayList<>();

    private Context mContext;
    public ShareAdapter(Context context, List<ShareBean> m) {
        super();
        this.mContext = context;
        mList=m;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ShareBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder  holder = new  ViewHolder( );

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_share, parent, false);


        holder.img = (ImageView) convertView.findViewById(R.id.img);
        holder.headImg = (ImageView) convertView.findViewById(R.id.headImg);
        holder.userTxt = (TextView) convertView.findViewById(R.id.userTxt);
        holder.conTxt = (TextView) convertView.findViewById(R.id.conTxt);
        holder.timeTxt = (TextView) convertView.findViewById(R.id.timeTxt);
        holder.userTxt.setText(mList.get(position).getUserId());
        holder.conTxt.setText(mList.get(position).getCon());
        holder.timeTxt.setText(mList.get(position).getTime());
        String str=mList.get(position).getImg();
        String[] strArray = str.split(",");
        Glide.with(mContext)
                .load(strArray[0])
                .into(holder.img);
        Glide.with(mContext).load(mList.get(position).getHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into( holder.headImg);

        return convertView;
    }

    class ViewHolder {
        TextView userTxt,conTxt,timeTxt;
        ImageView headImg,img;

    }

}
