package com.my.shopping.app.activitys.user;


import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.my.shopping.app.R;
import com.my.shopping.app.activitys.user.adapter.CommentAdapter;
import com.my.shopping.app.activitys.user.adapter.ImgAdapter;
import com.my.shopping.app.activitys.user.adapter.ShareAdapter;
import com.my.shopping.app.beans.CommentBeans;
import com.my.shopping.app.beans.ShareBean;
import com.my.shopping.app.beans.UserBean;
import com.my.shopping.app.core.BaseActivity;
import com.my.shopping.app.core.MyApplication;
import com.my.shopping.app.utils.CreateString;
import com.my.shopping.app.utils.SnackbarUtils;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ShareDetailListActivity extends BaseActivity  {
    public static  ShareBean mShareBean;


    @BindView(R.id.userTxt)
    TextView userTxt;

    @BindView(R.id.conTxt)
    TextView conTxt;

    @BindView(R.id.timeTxt)
    TextView timeTxt;

    @BindView(R.id.headImg)
    ImageView headImg;


    @BindView(R.id.gridview)
    GridView gridview;


    @BindView(R.id.backImg)
    ImageView backImg;

    @BindView(R.id.rightTxt)
    TextView rightTxt;

    @BindView(R.id.titleTxt)
    TextView titleTxt;

    @BindView(R.id.yesTxt)
    TextView yesTxt;

    @BindView(R.id.comEdt)
    TextView comEdt;

    @BindView(R.id.listview)
    ListView listview;

    @Override
    protected void init() {
        initTitleData();
        initData();
        initListview();

    }
    private void initData(){



       userTxt.setText(mShareBean.getUserId());
     conTxt.setText(mShareBean.getCon());
       timeTxt.setText(mShareBean.getTime());
        String str=mShareBean.getImg();
        Log.e("tag","str=============="+str);
        String[] strArray = str.split(",");
        List<String> list=new ArrayList<>();
        for (int i=0;i<strArray.length;i++){
            list.add(strArray[i]);
        }
        ImgAdapter adapter=new ImgAdapter(ShareDetailListActivity.this,list);
        gridview.setAdapter(adapter);
        Glide.with(this).load(mShareBean.getHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(  headImg);
    }
    @Override
    protected int getContentResourseId() {
        return R.layout.activity_detail_share;
    }
    private void initTitleData(){
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTxt.setText("分享");

        yesTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String com=comEdt.getText().toString();
                if (com==null||"".equals(com)){
                    SnackbarUtils.show(ShareDetailListActivity.this,"请输入评论的内容");
                    return;
                }
                final SharedPreferences sp = MyApplication.getContext().getSharedPreferences("user",0);
                String phone=sp.getString("phone","");
                List<UserBean> list = LitePal.where("userName = ? ",phone).find(UserBean.class);
                UserBean  mUserBean=list.get(0);
                CommentBeans commentBeans=new CommentBeans();
                commentBeans.setId(CreateString.currentTimeLong());
                Log.e("tag","-----fk-------"+mShareBean.getFkId());
                commentBeans.setGoodsId(mShareBean.getFkId());
                commentBeans.setHeadImg(mUserBean.getHead());
                commentBeans.setUserId(mUserBean.getUserName());
                commentBeans.setCom(com);
                commentBeans.save();
                comEdt.setText("");
                initListview();
            }
        });

    }

    private void initListview(){
        List<CommentBeans> list = LitePal.where("goodsId = ? ",mShareBean.getFkId()+"").find(CommentBeans.class);
        CommentAdapter adapter=new CommentAdapter(ShareDetailListActivity.this,list);
        listview.setAdapter(adapter);

    }

}
