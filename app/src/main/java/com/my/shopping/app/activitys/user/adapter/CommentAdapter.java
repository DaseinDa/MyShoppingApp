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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.my.shopping.app.R;
import com.my.shopping.app.activitys.admin.UserIDetailActivity;
import com.my.shopping.app.beans.CarInfo;
import com.my.shopping.app.beans.CommentBeans;

import java.util.ArrayList;
import java.util.List;


public class CommentAdapter extends BaseAdapter {
     //数据源
    private List<CommentBeans> mList = new ArrayList<>();
    SizeChend msizeChend;
    private Context mContext;
    public CommentAdapter(Context context, List<CommentBeans> m) {
        super();
        this.mContext = context;
        mList=m;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CommentBeans getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder  holder = new  ViewHolder( );

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);


        holder.comTxt = (TextView) convertView.findViewById(R.id.comTxt);
        holder.userName = (TextView) convertView.findViewById(R.id.userName);
        holder.image = (ImageView) convertView.findViewById(R.id.image);
        Glide.with(mContext).load(mList.get(position).getHeadImg()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(  holder.image);
        holder.userName.setText(mList.get(position).getUserId());
        holder.comTxt.setText( mList.get(position).getCom());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,UserIDetailActivity.class);
                intent.putExtra("id",mList.get(position).getUserId());

                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView userName;
        ImageView image;
        TextView comTxt;

    }
    public   interface SizeChend{
        void sizeChendCh();
    }
}
