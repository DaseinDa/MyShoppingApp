package com.my.shopping.app.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.my.shopping.app.R;
import com.my.shopping.app.activitys.admin.AddBusinessActivity;
import com.my.shopping.app.activitys.admin.BusListActivity;
import com.my.shopping.app.activitys.admin.EdtBusinessActivity;
import com.my.shopping.app.activitys.admin.GoodsListActivity;
import com.my.shopping.app.activitys.admin.adapter.BusAdapter;
import com.my.shopping.app.activitys.user.UserGoodsListActivity;
import com.my.shopping.app.beans.BusinessInfo;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;


public class ManagerFragment extends BaseFragment   {


    @BindView(R.id.listview)
    ListView listview;

    @BindView(R.id.imgAdd)
    ImageView imgAdd;

    @BindView(R.id.imgBdd)
    ImageView imgBdd;

    @BindView(R.id.searhEdt)
    EditText searhEdt;

    @Override
    protected void init() {
        imgAdd.setVisibility(View.GONE);
        imgBdd.setVisibility(View.GONE);
        initData();
        searhEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String key=s.toString();
                if (key!=null&&!"".equals(key)){
                    search(key);
                }else {
                    initData();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected int getContentResourseId() {
        return R.layout.bus_fragment_user;
    }


    @Override
    public void onStart() {
        super.onStart();

    }
    private void search(String key){
        final List<BusinessInfo > list = LitePal.where(" name like ?", "%" + key + "%").find(BusinessInfo.class);
        BusAdapter adapter=new BusAdapter(getContext(),list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(),UserGoodsListActivity.class);
                intent.putExtra("id",list.get(i).getFkId()+"");
                startActivityForResult(intent,1);
            }
        });

    }
    private void initData(){
        final List<BusinessInfo > list = LitePal.findAll(BusinessInfo.class);
        BusAdapter adapter=new BusAdapter(getContext(),list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(),UserGoodsListActivity.class);
                intent.putExtra("id",list.get(i).getFkId()+"");
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initData();
    }
}


