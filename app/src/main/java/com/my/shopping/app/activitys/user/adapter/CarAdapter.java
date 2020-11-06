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
import com.my.shopping.app.beans.BusinessInfo;
import com.my.shopping.app.beans.CarInfo;

import java.util.ArrayList;
import java.util.List;


public class CarAdapter extends BaseAdapter {
     //数据源
    private List<CarInfo> mList = new ArrayList<>();
    SizeChend msizeChend;
    private Context mContext;
    public CarAdapter(Context context, List<CarInfo> m,SizeChend msizeChend) {
        super();
        this.mContext = context;
        mList=m;
        this.msizeChend = msizeChend;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CarInfo getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder  holder = new  ViewHolder( );

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_car, parent, false);


        holder.priTxt = (TextView) convertView.findViewById(R.id.priTxt);
        holder.siexTxt = (TextView) convertView.findViewById(R.id.siexTxt);
        holder.image = (ImageView) convertView.findViewById(R.id.image);
        holder.jianImg = (ImageView) convertView.findViewById(R.id.jianImg);
        holder.jiaImg = (ImageView) convertView.findViewById(R.id.jiaImg);
        holder.conTxt = (TextView) convertView.findViewById(R.id.conTxt);
        Glide.with(mContext)
                .load(mList.get(position).getImg())
                .into(holder.image);
        holder.priTxt.setText("价格:"+mList.get(position).getMoneySize());
        holder.conTxt.setText( mList.get(position).getGoodsName());
        holder.siexTxt.setText( mList.get(position).getSize()+"");
        holder.jianImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mList.get(position).getSize()>1){
                    mList.get(position).setSize(mList.get(position).getSize()-1);
                    mList.get(position).update(mList.get(position).getId());
                    holder.siexTxt.setText(mList.get(position).getSize()+"");
                    msizeChend.sizeChendCh();
                }
            }
        });
        holder.jiaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.get(position).setSize(mList.get(position).getSize()+1);
                mList.get(position).update(mList.get(position).getId());
                holder.siexTxt.setText(mList.get(position).getSize()+"");
                msizeChend.sizeChendCh();
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView priTxt;
        ImageView image,jianImg,jiaImg;
        TextView conTxt,siexTxt;

    }
    public   interface SizeChend{
        void sizeChendCh();
    }
}
