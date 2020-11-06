package com.my.shopping.app.activitys.admin;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.my.shopping.app.R;
import com.my.shopping.app.beans.UserBean;
import com.my.shopping.app.core.BaseActivity;
import com.my.shopping.app.utils.SnackbarUtils;
import com.my.shopping.app.view.MoreImageView;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class UserIDetailActivity extends BaseActivity  {



    @BindView(R.id.backImg)
    ImageView backImg;

    @BindView(R.id.rightTxt)
    TextView rightTxt;

    @BindView(R.id.titleTxt)
    TextView titleTxt;

    @BindView(R.id.head_mimgv)
    MoreImageView head_mimgv;

    @BindView(R.id.nickname_txtv)
    TextView nickname_txtv;

    @BindView(R.id.level_name_txtv)
    TextView level_name_txtv;

    @BindView(R.id.loginOut)
    TextView loginOut;

    @BindView(R.id.school)
    TextView school;

    @BindView(R.id.moneyEdt)
    EditText moneyEdt;
    @Override
    protected void init() {
        initTitleData();

    }

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_detai_user_info;
    }
    private void initTitleData(){
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTxt.setText("用户信息");
        initData();
    }
    UserBean mUserBean;
    private void initData(){
        Intent  intent=getIntent();
        String id=intent.getStringExtra("id");

        List<UserBean> list = LitePal.where("userName = ? ",id).find(UserBean.class);
          mUserBean=list.get(0);
        nickname_txtv.setText(mUserBean.getUserName());
        Glide.with(this).load(mUserBean.getHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(head_mimgv);
        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m=moneyEdt.getText().toString();
                if (m.equals("")||m==null){
                    SnackbarUtils.show(UserIDetailActivity.this, "请输入金额");
                    return;
                }

                mUserBean.save();
                finish();
                SnackbarUtils.show(UserIDetailActivity.this, "修改成功");
            }
        });

    }


}
