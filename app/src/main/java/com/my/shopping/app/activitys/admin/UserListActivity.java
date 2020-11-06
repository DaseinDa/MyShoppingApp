package com.my.shopping.app.activitys.admin;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.my.shopping.app.R;
import com.my.shopping.app.activitys.admin.adapter.BusAdapter;
import com.my.shopping.app.activitys.admin.adapter.UserAdapter;
import com.my.shopping.app.beans.BusinessInfo;
import com.my.shopping.app.beans.UserBean;
import com.my.shopping.app.core.BaseActivity;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class UserListActivity extends BaseActivity  {


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
        titleTxt.setText("用户列表");
        addImg.setVisibility(View.GONE);
        initData();
    }

    private void initData(){
        final List<UserBean> list = LitePal.findAll(UserBean.class);
        UserAdapter adapter=new UserAdapter(UserListActivity.this,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(UserListActivity.this,UserInfoActivity.class);
                i.putExtra("id",list.get(position).getId()+"");
                startActivityForResult(i,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initData();
    }
}
