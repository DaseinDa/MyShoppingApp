package com.my.shopping.app.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.my.shopping.app.R;
import com.my.shopping.app.activitys.admin.AddBusinessActivity;
import com.my.shopping.app.activitys.admin.BusListActivity;
import com.my.shopping.app.activitys.admin.EdtBusinessActivity;
import com.my.shopping.app.activitys.admin.EdtGoodsActivity;
import com.my.shopping.app.activitys.admin.GoodsListActivity;
import com.my.shopping.app.activitys.admin.adapter.BusAdapter;
import com.my.shopping.app.beans.BusinessInfo;
import com.my.shopping.app.beans.GoodsInfo;


import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;



public class BusManagerFragment extends BaseFragment   {


    @BindView(R.id.listview)
    ListView listview;

    @BindView(R.id.imgAdd)
    ImageView imgAdd;

    @BindView(R.id.imgBdd)
    ImageView imgBdd;



    @Override
    protected void init() {


        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),AddBusinessActivity.class);
                startActivityForResult(intent,2);
            }
        });

        imgBdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),BusListActivity.class);
                startActivityForResult(intent,2);
            }
        });
        initData();

    }

    @Override
    protected int getContentResourseId() {
        return R.layout.bus_fragment_admin;
    }


    @Override
    public void onStart() {
        super.onStart();

    }
    private void initData(){
        final List<BusinessInfo > list = LitePal.findAll(BusinessInfo.class);
        BusAdapter adapter=new BusAdapter(getContext(),list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(),EdtBusinessActivity.class);
                EdtBusinessActivity.mBusinessInfo=list.get(i);
                startActivityForResult(intent,1);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int j, long l) {
                new AlertDialog.Builder(getContext())
                        .setTitle("确定删除商家吗?")
                        .setNegativeButton("删除商家", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                LitePal.delete(BusinessInfo.class,list.get(j).getId());
                                Toast.makeText(getContext(),"删除成功",Toast.LENGTH_SHORT).show();
                                initData();
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initData();
    }
}


