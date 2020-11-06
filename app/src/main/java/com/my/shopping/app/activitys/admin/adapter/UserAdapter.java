package com.my.shopping.app.activitys.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.my.shopping.app.R;
import com.my.shopping.app.beans.UserBean;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends BaseAdapter {
     //数据源
    private List<UserBean> mList = new ArrayList<>();

    private Context mContext;
    public UserAdapter(Context context, List<UserBean> m) {
        super();
        this.mContext = context;
        mList=m;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public UserBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder  holder = new  ViewHolder( );

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_user, parent, false);


        holder.moneyTxt = (TextView) convertView.findViewById(R.id.moneyTxt);
        holder.nameTxt = (TextView) convertView.findViewById(R.id.nameTxt);

        holder.nameTxt.setText("用户:"+mList.get(position).getUserName());
        return convertView;
    }

    class ViewHolder {
        TextView moneyTxt;
        TextView nameTxt;

    }

}
