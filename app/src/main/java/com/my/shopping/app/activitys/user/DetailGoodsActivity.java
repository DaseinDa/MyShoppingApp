package com.my.shopping.app.activitys.user;


import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.my.shopping.app.R;
import com.my.shopping.app.activitys.user.adapter.CommentAdapter;
import com.my.shopping.app.beans.CarInfo;
import com.my.shopping.app.beans.CommentBeans;
import com.my.shopping.app.beans.GoodsInfo;
import com.my.shopping.app.beans.UserBean;
import com.my.shopping.app.core.BaseActivity;
import com.my.shopping.app.core.MyApplication;
import com.my.shopping.app.utils.CreateString;
import com.my.shopping.app.utils.SnackbarUtils;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class DetailGoodsActivity extends BaseActivity  {
    public static  GoodsInfo mGoodsInfo;


    @BindView(R.id.backImg)
    ImageView backImg;

    @BindView(R.id.rightTxt)
    TextView rightTxt;

    @BindView(R.id.titleTxt)
    TextView titleTxt;


    @BindView(R.id.goodsImg)
    ImageView goodsImg;

    @BindView(R.id.goodsNameTxt)
    TextView goodsNameTxt;

    @BindView(R.id.goodsPriTxt)
    TextView goodsPriTxt;

    @BindView(R.id.conEdt)
    TextView conEdt;


    @BindView(R.id.yesTxt)
    TextView yesTxt;

    @BindView(R.id.comEdt)
    TextView comEdt;

    @BindView(R.id.listview)
    ListView listview;
    String flId="";


    @Override
    protected void init() {
        initTitleData();
        Intent intent=getIntent();
        flId=intent.getStringExtra("id");
        initDataView();
        initListview();

    }
    String img="";
    private void initDataView(){
        img= mGoodsInfo.getImg();
        Glide.with(this)
                .load(img)
                .into(goodsImg);
        goodsNameTxt.setText(mGoodsInfo.getGoodsName());
        goodsPriTxt.setText(mGoodsInfo.getMoneySize()+"");
        conEdt.setText(mGoodsInfo.getGoodsCon());
    }
    private void initListview(){
        List<CommentBeans> list = LitePal.where("goodsId = ? ",mGoodsInfo.getId()+"").find(CommentBeans.class);
        CommentAdapter adapter=new CommentAdapter(DetailGoodsActivity.this,list);
        listview.setAdapter(adapter);

    }

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_admin_goods_detail;
    }
    private void initTitleData(){
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTxt.setText("商品详情");
        rightTxt.setText("加入购物车");
        rightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("user",0);
                String phone=sp.getString("phone","");
                CarInfo mCarInfo=new CarInfo();
                mCarInfo.setId(CreateString.currentTimeLong());
                mCarInfo.setSize(1);
                mCarInfo.setUserId(phone);
                mCarInfo.setGoodsCon(mGoodsInfo.getGoodsCon());
                mCarInfo.setImg(mGoodsInfo.getImg());
                mCarInfo.setGoodsName(mGoodsInfo.getGoodsName());
                mCarInfo.setMoneySize(mGoodsInfo.getMoneySize());
                mCarInfo.setGoodsId(mGoodsInfo.getId()+"");
                mCarInfo.setIsYes("0");
                mGoodsInfo.setPurchase(mGoodsInfo.getPurchase()+1);
                mGoodsInfo.save();
                final List<CarInfo > list = LitePal.where("goodsId = ?", mGoodsInfo.getId()+"").find(CarInfo.class);
                if (list.size()>0){

                }else {
                    mCarInfo.save();
                }

                SnackbarUtils.show(DetailGoodsActivity.this,"加入购物车成功");
                finish();
            }
        });
        yesTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String com=comEdt.getText().toString();
                if (com==null||"".equals(com)){
                    SnackbarUtils.show(DetailGoodsActivity.this,"请输入评论的内容");
                    return;
                }
                final   SharedPreferences sp = MyApplication.getContext().getSharedPreferences("user",0);
                String phone=sp.getString("phone","");
                List<UserBean> list = LitePal.where("userName = ? ",phone).find(UserBean.class);
                UserBean  mUserBean=list.get(0);
                CommentBeans commentBeans=new CommentBeans();
                commentBeans.setId(CreateString.currentTimeLong());
                commentBeans.setGoodsId(mGoodsInfo.getId());
                commentBeans.setHeadImg(mUserBean.getHead());
                commentBeans.setUserId(mUserBean.getUserName()); 
                commentBeans.setCom(com);
                commentBeans.save();
                comEdt.setText("");
                initListview();
            }
        });
    }


}
