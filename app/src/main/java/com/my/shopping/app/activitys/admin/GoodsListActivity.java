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
import com.my.shopping.app.activitys.admin.adapter.GoodsAdapter;
import com.my.shopping.app.beans.GoodsInfo;
import com.my.shopping.app.core.BaseActivity;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class GoodsListActivity extends BaseActivity  {


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
        titleTxt.setText("商品列表");
          Intent intent=getIntent();
        fkId  =intent.getStringExtra("id");
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(GoodsListActivity.this,AddGoodsActivity.class);
                i.putExtra("id",fkId);
                startActivityForResult(i,1);

            }
        });
        initData();
    }

    private void initData(){
            final     List<GoodsInfo > list = LitePal.where("fkId = ?", fkId).find(GoodsInfo.class);

        GoodsAdapter adapter=new GoodsAdapter(GoodsListActivity.this,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(GoodsListActivity.this,EdtGoodsActivity.class);
                EdtGoodsActivity.mGoodsInfo=list.get(i);
                startActivityForResult(intent,1);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int j, long l) {
                new AlertDialog.Builder(GoodsListActivity.this)
                        .setTitle("确定删除商品吗?")
                        .setNegativeButton("删除商品", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                LitePal.delete(GoodsInfo.class,list.get(j).getId());
                                Toast.makeText(GoodsListActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
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
