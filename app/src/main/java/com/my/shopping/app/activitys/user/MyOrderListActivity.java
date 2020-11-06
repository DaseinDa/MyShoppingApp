package com.my.shopping.app.activitys.user;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.my.shopping.app.R;
import com.my.shopping.app.activitys.admin.GoodsListActivity;
import com.my.shopping.app.activitys.admin.adapter.GoodsAdapter;
import com.my.shopping.app.activitys.user.adapter.MyOrderAdapter;
import com.my.shopping.app.beans.GoodsInfo;
import com.my.shopping.app.beans.OrderInfo;
import com.my.shopping.app.core.BaseActivity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MyOrderListActivity extends BaseActivity  {


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
          List<OrderInfo> list=new ArrayList<>();
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
        titleTxt.setText("我的订单");
        addImg.setVisibility(View.GONE);
        initData();
    }

    private void initData(){
        SharedPreferences sp = getSharedPreferences("user",0);
        String phone=sp.getString("phone","");
        String type=sp.getString("type","");
        if (type.equals("1")){
            list = LitePal.findAll(OrderInfo.class);
        }else {
            list = LitePal.where("userId = ?", phone).find(OrderInfo.class);
        }

        MyOrderAdapter adapter=new MyOrderAdapter(MyOrderListActivity.this,list);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int j, long l) {
                new AlertDialog.Builder(MyOrderListActivity.this)
                        .setTitle("确定删除订单吗?")
                        .setNegativeButton("删除订单", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                LitePal.delete(OrderInfo.class,list.get(j).getId());
                                Toast.makeText(MyOrderListActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                                initData();
                            }
                        })
                        .show();
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initData();
    }
}
