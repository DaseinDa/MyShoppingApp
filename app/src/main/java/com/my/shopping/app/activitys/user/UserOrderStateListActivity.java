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
import com.my.shopping.app.activitys.admin.adapter.OrderStateAdapter;
import com.my.shopping.app.activitys.user.adapter.MyOrderAdapter;
import com.my.shopping.app.activitys.user.adapter.UserOrderStateAdapter;
import com.my.shopping.app.beans.OrderInfo;
import com.my.shopping.app.core.BaseActivity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class UserOrderStateListActivity extends BaseActivity  {


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
    String type1="";
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
        Intent intent=getIntent();
          type1=intent.getStringExtra("type");
        initData();
    }

    private void initData(){
        SharedPreferences sp = getSharedPreferences("user",0);
        String phone=sp.getString("phone","");
        String type=sp.getString("type","");
        if (type.equals("1")){

            list = LitePal.where("type = ?", type1).find(OrderInfo.class);
            for (int i=0;i<list.size();i++){
            }
        }else {
            list = LitePal.where("userId = ? and  type = ?", phone,type1).find(OrderInfo.class);
        }

        UserOrderStateAdapter adapter=new UserOrderStateAdapter(UserOrderStateListActivity.this,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(UserOrderStateListActivity.this,UserOrderStateGoodsListActivity.class);
                intent.putExtra("id",list.get(position).getFkId()+"");
                intent.putExtra("type",list.get(position).getType());
                intent.putExtra("orderid",list.get(position).getId()+"");
                startActivityForResult(intent,1);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int j, long l) {
                new AlertDialog.Builder(UserOrderStateListActivity.this)
                        .setTitle("确定删除订单吗?")
                        .setNegativeButton("删除订单", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                LitePal.delete(OrderInfo.class,list.get(j).getId());
                                Toast.makeText(UserOrderStateListActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
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
