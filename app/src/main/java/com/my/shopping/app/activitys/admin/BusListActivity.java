package com.my.shopping.app.activitys.admin;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.my.shopping.app.R;
import com.my.shopping.app.activitys.admin.adapter.BusAdapter;
import com.my.shopping.app.activitys.admin.adapter.GoodsAdapter;
import com.my.shopping.app.beans.BusinessInfo;
import com.my.shopping.app.beans.GoodsInfo;
import com.my.shopping.app.core.BaseActivity;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class BusListActivity extends BaseActivity  {


    @BindView(R.id.listview)
    ListView listview;

    @BindView(R.id.backImg)
    ImageView backImg;

    @BindView(R.id.rightTxt)
    TextView rightTxt;

    @BindView(R.id.titleTxt)
    TextView titleTxt;

    @BindView(R.id.addImg)
    ImageView addImg;
    @Override
    protected void init() {
        initTitleData();

    }

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_admin_goods_list;
    }
    private void initTitleData(){
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTxt.setText("商家列表");
        addImg.setVisibility(View.GONE);
        initData();
    }

    private void initData(){
        final List<BusinessInfo> list = LitePal.findAll(BusinessInfo.class);
        BusAdapter adapter=new BusAdapter(BusListActivity.this,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(BusListActivity.this,GoodsListActivity.class);
                i.putExtra("id",list.get(position).getFkId()+"");
                startActivityForResult(i,1);
            }
        });
    }


}
