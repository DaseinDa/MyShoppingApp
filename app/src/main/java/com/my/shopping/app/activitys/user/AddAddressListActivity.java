package com.my.shopping.app.activitys.user;


import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.my.shopping.app.R;
import com.my.shopping.app.activitys.LoginActivity;
import com.my.shopping.app.beans.Address;
import com.my.shopping.app.beans.AddressBeans;
import com.my.shopping.app.beans.UserBean;
import com.my.shopping.app.core.BaseActivity;
import com.my.shopping.app.core.MyApplication;
import com.my.shopping.app.utils.CreateString;
import com.my.shopping.app.utils.SnackbarUtils;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class AddAddressListActivity extends BaseActivity  {

    @BindView(R.id.img1)
    ImageView img1;

    @BindView(R.id.img2)
    ImageView img2;

    String type="1";

    @BindView(R.id.mapLayout)
    LinearLayout mapLayout;

    @BindView(R.id.inputLayout)
    LinearLayout inputLayout;


    @BindView(R.id.backImg)
    ImageView backImg;

    @BindView(R.id.edit_phone_num)
    EditText edit_phone_num;

    @BindView(R.id.edit_name)
    EditText edit_name;

    @BindView(R.id.edit_address)
    EditText edit_address;

    @BindView(R.id.titleTxt)
    TextView titleTxt;

    @BindView(R.id.btn_login)
    Button btn_login;
    String lon="0";
    String lat="0";
    String phone;
    @Override
    protected void init() {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("user",0);
        phone=sp.getString("phone","");
        initTitleData();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pho=edit_phone_num.getText().toString();
                String name=edit_name.getText().toString();
                String add=edit_address.getText().toString();
                if (pho==null||"".equals(pho)){
                    SnackbarUtils.show(AddAddressListActivity.this, "请输入账号电话");
                    return;
                }
                if (name==null||"".equals(name)){
                    SnackbarUtils.show(AddAddressListActivity.this, "请输入收货人姓名");
                    return;
                }
                if (add==null||"".equals(add)){
                    SnackbarUtils.show(AddAddressListActivity.this, "请输入收货人地址");
                    return;
                }
                final List<Address> list = LitePal.where("userId = ?", phone).find(Address.class);
                Address mAddress=new Address();
                mAddress.setId(CreateString.currentTimeLong());
                mAddress.setAddressName(add);
                mAddress.setUserId(phone);
                mAddress.setUserName(name);
                mAddress.setType(type);
                mAddress.setLon(Double.parseDouble(lon));
                mAddress.setLat(Double.parseDouble(lat));
                Log.e("tag","lat==1========1====="+mAddress.getLat());
                Log.e("tag","lon===1======1======"+mAddress.getLon());
                mAddress.setPhone(pho);
                if (list.size()>0){
                    mAddress.setSelect(false);
                }else {
                    mAddress.setSelect(true);
                }
                mAddress.save();
                SnackbarUtils.show(AddAddressListActivity.this, "添加收货地址成功");
                finish();
            }
        });
        mapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="2";
                img2.setImageResource(R.drawable.xz);
                img1.setImageResource(R.drawable.wxz);
                Intent intent=new Intent(AddAddressListActivity.this,LocationActivity.class);
                startActivityForResult(intent,1);
            }
        });
        inputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="2";
                img1.setImageResource(R.drawable.xz);
                img2.setImageResource(R.drawable.wxz);

            }
        });
    }

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_address_add;
    }
    private void initTitleData(){
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("user",0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("address","") ;
                editor.putString("lon","") ;
                editor.putString("lat", "") ;
                editor.commit();
                finish();
            }
        });
        titleTxt.setText("收货地址");

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences sp = getSharedPreferences("user",0);
        String address=sp.getString("address","");
          lon=sp.getString("lon","");
          lat=sp.getString("lat","");
        edit_address.setText(address);
    }
}
