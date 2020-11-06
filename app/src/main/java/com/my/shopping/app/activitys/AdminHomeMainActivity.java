package com.my.shopping.app.activitys;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.my.shopping.app.R;
import com.my.shopping.app.activitys.admin.GoodsListActivity;
import com.my.shopping.app.beans.HomeBean;
import com.my.shopping.app.core.BaseActivity;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class AdminHomeMainActivity extends BaseActivity  {

    @BindView(R.id.img1)
    ImageView img1;


    @BindView(R.id.img2)
    ImageView img2;

    @BindView(R.id.img3)
    ImageView img3;

    @BindView(R.id.img4)
    ImageView img4;

    @BindView(R.id.img5)
    ImageView img5;

    @BindView(R.id.img6)
    ImageView img6;

    @BindView(R.id.img7)
    ImageView img7;

    @BindView(R.id.img8)
    ImageView img8;

    @BindView(R.id.img9)
    ImageView img9;

    @Override
    protected void init() {
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHomeMainActivity.this,GoodsListActivity.class);
                intent.putExtra("id","1");
                startActivity(intent);

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHomeMainActivity.this,GoodsListActivity.class);
                intent.putExtra("id","2");
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHomeMainActivity.this,GoodsListActivity.class);
                intent.putExtra("id","3");
                startActivity(intent);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHomeMainActivity.this,GoodsListActivity.class);
                intent.putExtra("id","4");
                startActivity(intent);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHomeMainActivity.this,GoodsListActivity.class);
                intent.putExtra("id","5");
                startActivity(intent);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHomeMainActivity.this,GoodsListActivity.class);
                intent.putExtra("id","6");
                startActivity(intent);
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHomeMainActivity.this,GoodsListActivity.class);
                intent.putExtra("id","7");
                startActivity(intent);
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHomeMainActivity.this,GoodsListActivity.class);
                intent.putExtra("id","8");
                startActivity(intent);
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHomeMainActivity.this,GoodsListActivity.class);
                intent.putExtra("id","9");
                startActivity(intent);
            }
        });

        initImgView();
    }

    @Override
    protected int getContentResourseId() {
        return R.layout.home_fragment_admin_main;
    }

    private void initImgView(){

        List<HomeBean> list = LitePal.findAll(HomeBean.class);
        if (list.size()>0){
            HomeBean mHomeBean=list.get(0);
            Glide.with(this)
                    .load(mHomeBean.getImg1())
                    .into(img1);
            Glide.with(this)
                    .load(mHomeBean.getImg2())
                    .into(img2);
            Glide.with(this)
                    .load(mHomeBean.getImg3())
                    .into(img3);
            Glide.with(this)
                    .load(mHomeBean.getImg4())
                    .into(img4);
            Glide.with(this)
                    .load(mHomeBean.getImg5())
                    .into(img5);
            Glide.with(this)
                    .load(mHomeBean.getImg6())
                    .into(img6);
            Glide.with(this)
                    .load(mHomeBean.getImg4())
                    .into(img7);
            Glide.with(this)
                    .load(mHomeBean.getImg5())
                    .into(img8);
            Glide.with(this)
                    .load(mHomeBean.getImg6())
                    .into(img9);

        }

    }


}
