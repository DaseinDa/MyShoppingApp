package com.my.shopping.app.activitys;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.my.shopping.app.R;
import com.my.shopping.app.activitys.user.adapter.ProvinceAdapter;
import com.my.shopping.app.beans.UserBean;
import com.my.shopping.app.utils.CreateString;
import com.my.shopping.app.utils.SnackbarUtils;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class RegActivity extends AppCompatActivity {
    EditText edit_phone_num,edit_password;
    Button btn_login;
    private View parent;
    /**
     * Adapter相关
     **/
    private ProvinceAdapter mProvinceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
    }





    private void initView(){


        edit_phone_num=(EditText)findViewById(R.id.edit_phone_num);
        edit_password=(EditText)findViewById(R.id.edit_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String n=edit_phone_num.getText().toString();

                if (n==null||"".equals(n)){
                    SnackbarUtils.show(RegActivity.this, "请输入账号");
                    return;
                }
                final   String p=edit_password.getText().toString();
                if (p==null||"".equals(p)){
                    SnackbarUtils.show(RegActivity.this, "请输入密码");
                    return;
                }

                onReg(n,p,"");
            }
        });


    }

  
    private void onReg(String n,String p,String s){
        Intent intent=getIntent();
        String type=intent.getStringExtra("type");
        UserBean userBean=new UserBean();
        userBean.setId(CreateString.currentTimeLong());
        userBean.setPassword(p);
        userBean.setHead("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3993609309,1903481132&fm=27&gp=0.jpg");
        userBean.setUserName(n);
        userBean.setType(type);


        List<UserBean> list = LitePal.where("userName = ?", n).find(UserBean.class);
        if (list.size()>0){
            SnackbarUtils.show(RegActivity.this, "已存在账号，请重新输入");
            return;
        }

        userBean.save();
        finish();
        //DataSupport.deleteAll(LitePalBean.class, "name =?", "张三");
        //List<LitePalBean> all = DataSupport.findAll(LitePalBean.class);
        //News news = DataSupport.find(News.class, 1);//news表中id为1的这条记录
        //List<News> newsList = DataSupport.where("commentcount > ?", "0").find(News.class);
    }

}
