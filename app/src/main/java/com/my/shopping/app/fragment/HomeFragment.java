package com.my.shopping.app.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.my.shopping.app.R;
import com.my.shopping.app.activitys.AdminHomeMainActivity;
import com.my.shopping.app.activitys.admin.GoodsListActivity;
import com.my.shopping.app.activitys.user.MyOrderListActivity;
import com.my.shopping.app.activitys.user.UserGoodsListActivity;
import com.my.shopping.app.beans.HomeBean;
import com.my.shopping.app.core.tabInit;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment   {

    @BindView(R.id.home_menu_categroy_layout)
    LinearLayout home_menu_categroy_layout;

    @BindView(R.id.home_menu_shopcart_layout)
    LinearLayout home_menu_shopcart_layout;

    @BindView(R.id.home_menu_order_layout)
    LinearLayout home_menu_order_layout;

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
        home_menu_categroy_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity()instanceof tabInit){
                    ((tabInit)getActivity()).onPostion(3);
                }
            }
        });
        home_menu_shopcart_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity()instanceof tabInit){
                    ((tabInit)getActivity()).onPostion(2);
                }
            }
        });

        home_menu_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MyOrderListActivity.class);
                getActivity().startActivity(intent);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),UserGoodsListActivity.class);
                intent.putExtra("id","1");
                startActivity(intent);

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),UserGoodsListActivity.class);
                intent.putExtra("id","2");
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),UserGoodsListActivity.class);
                intent.putExtra("id","3");
                startActivity(intent);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),UserGoodsListActivity.class);
                intent.putExtra("id","4");
                startActivity(intent);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),UserGoodsListActivity.class);
                intent.putExtra("id","5");
                startActivity(intent);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),UserGoodsListActivity.class);
                intent.putExtra("id","6");
                startActivity(intent);
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),UserGoodsListActivity.class);
                intent.putExtra("id","7");
                startActivity(intent);
            }
        });

        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),UserGoodsListActivity.class);
                intent.putExtra("id","8");
                startActivity(intent);
            }
        });

        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),UserGoodsListActivity.class);
                intent.putExtra("id","9");
                startActivity(intent);
            }
        });


        initImgView();

    }

    @Override
    protected int getContentResourseId() {
        return R.layout.home_fragment;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    private void initImgView(){
        List<HomeBean> list = LitePal.findAll(HomeBean.class);
        if (list.size()>0){
            HomeBean mHomeBean=list.get(0);
            Glide.with(getContext())
                    .load(mHomeBean.getImg1())
                    .into(img1);
            Glide.with(getContext())
                    .load(mHomeBean.getImg2())
                    .into(img2);
            Glide.with(getContext())
                    .load(mHomeBean.getImg3())
                    .into(img3);
            Glide.with(getContext())
                    .load(mHomeBean.getImg4())
                    .into(img4);
            Glide.with(getContext())
                    .load(mHomeBean.getImg5())
                    .into(img5);
            Glide.with(getContext())
                    .load(mHomeBean.getImg6())
                    .into(img6);
            Glide.with(getContext())
                    .load(mHomeBean.getImg7())
                    .into(img7);
            Glide.with(getContext())
                    .load(mHomeBean.getImg8())
                    .into(img8);
            Glide.with(getContext())
                    .load(mHomeBean.getImg9())
                    .into(img9);

        }

    }



}


