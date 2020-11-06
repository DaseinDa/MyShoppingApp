package com.my.shopping.app.activitys.user;


import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.my.shopping.app.R;
import com.my.shopping.app.activitys.admin.adapter.GoodsAdapter;
import com.my.shopping.app.activitys.user.adapter.MyOrderGoodsAdapter;
import com.my.shopping.app.beans.GoodsInfo;
import com.my.shopping.app.beans.OrderDetailInfo;
import com.my.shopping.app.core.BaseActivity;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class OrderGoodsListActivity extends BaseActivity  {


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
    String fkId="";
    @Override
    protected void init() {
        initTitleData();

    }
    String type="";
    String phone="";
    @Override
    protected int getContentResourseId() {
        return R.layout.activity_admin_goods_list;
    }
    private void initTitleData(){
        SharedPreferences sp = getSharedPreferences("user",0);
          phone=sp.getString("phone","");
          type=sp.getString("type","");

        rightTxt.setText("");
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTxt.setText("商品列表");
          Intent intent=getIntent();
        fkId  =intent.getStringExtra("id");
        addImg.setVisibility(View.GONE);
        initData();
    }

    private void initData(){
          final List<OrderDetailInfo > list = LitePal.where("orderId = ?", fkId).find(OrderDetailInfo.class);
        //final List<OrderDetailInfo > list = LitePal.findAll(OrderDetailInfo.class);
        MyOrderGoodsAdapter adapter=new MyOrderGoodsAdapter(OrderGoodsListActivity.this,list);
        listview.setAdapter(adapter);
        final List<OrderDetailInfo > list11 = LitePal.findAll(OrderDetailInfo.class);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initData();
    }
}
