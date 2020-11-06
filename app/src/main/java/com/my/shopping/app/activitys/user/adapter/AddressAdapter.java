package com.my.shopping.app.activitys.user.adapter;

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
import com.my.shopping.app.beans.Address;
import com.my.shopping.app.beans.CommentBeans;

import java.util.ArrayList;
import java.util.List;


public class AddressAdapter extends BaseAdapter {
     //数据源
    private List<Address> mList = new ArrayList<>();
    private Context mContext;
    public AddressAdapter(Context context, List<Address> mList) {
        super();
        this.mContext = context;
        this.mList = mList;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Address getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder  holder = new  ViewHolder( );

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_address, parent, false);

        holder.addressTxt = (TextView) convertView.findViewById(R.id.addressTxt);
        holder.nameTxt = (TextView) convertView.findViewById(R.id.nameTxt);
        holder.phoneTxt = (TextView) convertView.findViewById(R.id.phoneTxt);
        holder.img = (ImageView) convertView.findViewById(R.id.img);
        holder.addressTxt.setText("地址:"+mList.get(position).getAddressName());
        holder.nameTxt.setText("收货人:"+mList.get(position).getUserName());
        holder.phoneTxt.setText("电话:"+mList.get(position).getPhone());
        if (mList.get(position).isSelect()){
            holder.img.setImageResource(R.drawable.ic_checked);
        }else {
            holder.img.setImageResource(R.drawable.ic_uncheck);
        }
        return convertView;
    }

    class ViewHolder {
        TextView nameTxt;
        ImageView img;
        TextView phoneTxt;
        TextView addressTxt;

    }
    public   interface SizeChend{
        void sizeChendCh();
    }
}
