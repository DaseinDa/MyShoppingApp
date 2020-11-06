package com.my.shopping.app.fragment;



import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.my.shopping.app.R;
import com.my.shopping.app.activitys.user.AddAddressListActivity;
import com.my.shopping.app.activitys.user.adapter.CarAdapter;
import com.my.shopping.app.beans.Address;
import com.my.shopping.app.beans.CarInfo;
import com.my.shopping.app.beans.OrderDetailInfo;
import com.my.shopping.app.beans.OrderInfo;
import com.my.shopping.app.beans.UserBean;
import com.my.shopping.app.service.MyService;
import com.my.shopping.app.utils.CreateString;
import com.my.shopping.app.utils.SnackbarUtils;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class CarFragment extends BaseFragment implements CarAdapter.SizeChend {


    @BindView(R.id.listview)
    ListView listview;

    @BindView(R.id.totalfee_txtv)
    TextView totalfee_txtv;

    @BindView(R.id.checkall_btn)
    Button checkall_btn;


    @BindView(R.id.buy_btn)
    TextView buy_btn;
    int  money=0;
    @Override
    protected void init() {

        initViewData();


    }

    private void initViewData(){
        SharedPreferences sp = getContext().getSharedPreferences("user",0);
        final String phone=sp.getString("phone","");

        final List<CarInfo > list = LitePal.where("userId = ?", phone).find(CarInfo.class);

        CarAdapter adapter=new CarAdapter(getContext(),list,this);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int j, long l) {
                new AlertDialog.Builder(getContext())
                        .setTitle("确定删除购物车商品吗?")
                        .setNegativeButton("删除商品", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                LitePal.delete(CarInfo.class,list.get(j).getId());
                                Toast.makeText(getContext(),"删除成功",Toast.LENGTH_SHORT).show();
                                initViewData();
                            }
                        })
                        .show();
                return true;
            }
        });

        if (list.size()>0){
            int  money=0;
            for (int i=0;i<list.size();i++){
                money+=list.get(i).getSize()*list.get(i).getMoneySize();



            }
            totalfee_txtv.setText("共计:"+money);
        }else {
            totalfee_txtv.setText("共计:"+"0");
        }
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final List<Address> list1 = LitePal.where("userId = ?  ", phone).find(Address.class);
                if (list1.size()<1){
                    SnackbarUtils.show(getContext(), "请添加收货地址");
                    return;
                }
                boolean isSelect=false;
                  Address mAddress=new Address();
                for (int i=0;i<list1.size();i++){
                    if (list1.get(i).isSelect()){
                        mAddress=list1.get(i);
                        isSelect=true;
                        break;
                    }
                }

                if (!isSelect){
                    SnackbarUtils.show(getContext(), "请选择一个收货地址");
                    return;
                }
                if (list.size()>0){
                    final long id=+CreateString.currentTimeLong();

                    OrderInfo mOrderInfo1=new OrderInfo();
                    mOrderInfo1.setId(id);

                    for (int i=0;i<list.size();i++){
                        money+=list.get(i).getSize()*list.get(i).getMoneySize();

                    }


                    if (money>0){

                        List<UserBean> list2 = LitePal.where("userName = ? ",phone).find(UserBean.class);
                        UserBean mUserBean=list2.get(0);




                        new AlertDialog.Builder(getContext())
                                .setTitle("你即将支付"+money+"元")
                                .setNegativeButton("确定支付?", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getContext(),"支付成功",Toast.LENGTH_SHORT).show();


                                        for (int n=0;n<list.size();n++){

                                            CarInfo mCarInfo=list.get(n);
                                            OrderDetailInfo mOrderDetailInfo=new OrderDetailInfo();
                                            mOrderDetailInfo.setOrderId(id+"");
                                            mOrderDetailInfo.setGoodsCon(mCarInfo.getGoodsCon());
                                            mOrderDetailInfo.setId(mCarInfo.getId());
                                            mOrderDetailInfo.setGoodsId(mCarInfo.getGoodsId());
                                            mOrderDetailInfo.setImg(mCarInfo.getImg());
                                            mOrderDetailInfo.setGoodsName(mCarInfo.getGoodsName());
                                            mOrderDetailInfo.setIsYes(mCarInfo.getIsYes());
                                            mOrderDetailInfo.setUserId(mCarInfo.getUserId());
                                            mOrderDetailInfo.setMoneySize(mCarInfo.getMoneySize());
                                            mOrderDetailInfo.setSize(mCarInfo.getSize());
                                            mOrderDetailInfo.save();
                                            mCarInfo.delete();
                                        }
                                        saveOrder(id,money,phone);
                                        money=0;
                                        initViewData();
                                    }
                                })
                                .show();
                    }
                }
            }
        });
        checkall_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViewData();
            }
        });
    }

    private void saveOrder(long id,int money,String phone){

        final List<Address> list1 = LitePal.where("userId = ?  ", phone).find(Address.class);
        if (list1.size()<1){
            SnackbarUtils.show(getContext(), "请添加收货地址");
            return;
        }
        Address mAddress=new Address();
        for (int i=0;i<list1.size();i++){
            if (list1.get(i).isSelect()){
                mAddress=list1.get(i);

                break;
            }
        }
        List<UserBean> list2 = LitePal.where("userName = ? ",phone).find(UserBean.class);
        UserBean mUserBean=list2.get(0);
        mUserBean.update(mUserBean.getId());
        OrderInfo mOrderInfo=new OrderInfo();
        mOrderInfo.setId(id);
        mOrderInfo.setSizeMoney(money+"");
        mOrderInfo.setUserId(phone);
        mOrderInfo.setFkId(id+"");
        mOrderInfo.setType("已付款");
        mOrderInfo.setOrderNO(CreateString.currentTimeLong()+"");
        mOrderInfo.setUserName(mAddress.getUserId());
        mOrderInfo.setAddressName(mAddress.getAddressName());
        mOrderInfo.setPhone(mAddress.getPhone());
      //  mOrderInfo.save();

        Intent intent_ = new Intent();
        intent_.setAction("pay");
        intent_.putExtra("info", mOrderInfo);
        intent_.putExtra("money",money+"");
        getContext().sendBroadcast(intent_);
        Log.e("tag","lao======11========"+money);


    }
    @Override
    protected int getContentResourseId() {
        return R.layout.car_fragment;
    }


    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void sizeChendCh() {
        initViewData();
    }
}


