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
import com.my.shopping.app.activitys.user.adapter.ShareAdapter;
import com.my.shopping.app.activitys.user.adapter.UserOrderStateAdapter;
import com.my.shopping.app.beans.OrderInfo;
import com.my.shopping.app.beans.ShareBean;
import com.my.shopping.app.core.BaseActivity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ShareListActivity extends BaseActivity  {


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
          List<ShareBean> list=new ArrayList<>();
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
        titleTxt.setText("分享");
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShareListActivity.this,AddShareActivity.class);
                startActivityForResult(intent,1);
            }
        });
        initData();
    }

    private void initData(){
        list = LitePal.findAll(ShareBean.class);

        ShareAdapter adapter=new ShareAdapter(ShareListActivity.this,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(ShareListActivity.this,ShareDetailListActivity.class);
                ShareDetailListActivity.mShareBean=list.get(position);
            startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initData();
    }
}
