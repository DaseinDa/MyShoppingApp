package com.my.shopping.app.activitys.admin;


import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.my.shopping.app.R;
import com.my.shopping.app.activitys.user.adapter.MyOrderGoodsAdapter;
import com.my.shopping.app.beans.OrderDetailInfo;
import com.my.shopping.app.beans.OrderInfo;
import com.my.shopping.app.core.BaseActivity;
import com.my.shopping.app.core.MyApplication;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class OrderStateGoodsListActivity extends BaseActivity  {


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
    String orderid="";
    @Override
    protected int getContentResourseId() {
        return R.layout.activity_admin_goods_list;
    }
    private void initTitleData(){
        SharedPreferences sp = getSharedPreferences("user",0);
          phone=sp.getString("phone","");
          Intent intent=getIntent();
          type=intent.getStringExtra("type");
        orderid=intent.getStringExtra("orderid");
        if (type.equals("已付款")){
            rightTxt.setText("发货");
            rightTxt.setVisibility(View.VISIBLE);

        }else {
            rightTxt.setText("");
            rightTxt.setVisibility(View.GONE);
        }

        rightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             List<OrderInfo>   list = LitePal.where("id = ?", orderid).find(OrderInfo.class);
             OrderInfo morderid=list.get(0);
                morderid.setType("待收货");
                morderid.save();
                finish();
            }
        });
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTxt.setText("商品列表");
        fkId  =intent.getStringExtra("id");
        addImg.setVisibility(View.GONE);
        initData();
    }

    private void initData(){
          final List<OrderDetailInfo > list = LitePal.where("orderId = ?", fkId).find(OrderDetailInfo.class);
        //final List<OrderDetailInfo > list = LitePal.findAll(OrderDetailInfo.class);
        MyOrderGoodsAdapter adapter=new MyOrderGoodsAdapter(OrderStateGoodsListActivity.this,list);
        listview.setAdapter(adapter);
        final List<OrderDetailInfo > list11 = LitePal.findAll(OrderDetailInfo.class);
        for (int i=0;i<list11.size();i++){
            Log.e("tag","getOrderId========"+list11.get(i).getOrderId()+"");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initData();
    }
}
