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
import com.my.shopping.app.activitys.user.adapter.AddressAdapter;
import com.my.shopping.app.activitys.user.adapter.MyOrderGoodsAdapter;
import com.my.shopping.app.beans.Address;
import com.my.shopping.app.beans.AddressBeans;
import com.my.shopping.app.beans.GoodsInfo;
import com.my.shopping.app.beans.OrderDetailInfo;
import com.my.shopping.app.core.BaseActivity;
import com.my.shopping.app.core.MyApplication;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class AddressListActivity extends BaseActivity  {


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

    String phone;
    @Override
    protected void init() {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("user",0);
        phone=sp.getString("phone","");
        initTitleData();

    }

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_address_listview_add;
    }
    private void initTitleData(){
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTxt.setText("收货地址");
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddressListActivity.this,AddAddressListActivity.class);
                startActivityForResult(intent,1);
            }
        });
        initData();
    }

    private void initData(){
           final List<Address> list = LitePal.where("userId = ?", phone).find(Address.class);

        AddressAdapter adapter=new AddressAdapter(AddressListActivity.this,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i=0;i<list.size();i++){
                    list.get(i).setSelect(false);
                    list.get(i).save();
                    if (i==list.size()-1){
                       list.get(position).setSelect(true);
                        list.get(position).save();
                        initData();
                    }
                }
                initData();

            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int j, long l) {
                new AlertDialog.Builder(AddressListActivity.this)
                        .setTitle("确定删除地址吗?")
                        .setNegativeButton("删除地址", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                LitePal.delete(Address.class,list.get(j).getId());
                                Toast.makeText(AddressListActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                                initData();
                            }
                        })
                        .show();
                return true;
            }
        });

        for (int i=0;i<list.size();i++){
            if ( list.get(i).isSelect()&&list.get(i).getType().equals("2")){
                rightTxt.setText("查看地图");
               final Address mAddress=list.get(i);
                rightTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(AddressListActivity.this,ShowLocationActivity.class);
                        intent.putExtra("lat",mAddress.getLat());
                        intent.putExtra("lon",mAddress.getLon());
                        Log.e("tag","lat==1============="+mAddress.getLat());
                        Log.e("tag","lon===1============"+mAddress.getLon());
                        startActivity(intent);
                    }
                });
                break;
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initData();
    }
}
