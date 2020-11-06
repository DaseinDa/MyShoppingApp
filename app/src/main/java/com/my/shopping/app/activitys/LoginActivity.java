package com.my.shopping.app.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.my.shopping.app.R;
import com.my.shopping.app.beans.UserBean;
import com.my.shopping.app.utils.CreateString;
import com.my.shopping.app.utils.SnackbarUtils;

import org.litepal.LitePal;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText edit_phone_num,edit_password;
    Button btn_login;
    TextView txt_register,txt_register_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initView(){
        edit_phone_num=(EditText)findViewById(R.id.edit_phone_num);
        edit_password=(EditText)findViewById(R.id.edit_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        txt_register=(TextView)findViewById(R.id.txt_register);
        txt_register_admin=(TextView)findViewById(R.id.txt_register_admin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String n=edit_phone_num.getText().toString();
                if (n==null||"".equals(n)){
                    SnackbarUtils.show(LoginActivity.this, "请输入账号");
                    return;
                }
                final   String p=edit_password.getText().toString();
                if (p==null||"".equals(p)){
                    SnackbarUtils.show(LoginActivity.this, "请输入密码");
                    return;
                }
                onLogin(n,p);

            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);

            }
        });
        txt_register_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
            }
        });



    }
    private void onLogin(String n,String p){
        UserBean userBean=new UserBean();
        userBean.setId(CreateString.currentTimeLong());
        userBean.setPassword(p);
        userBean.setUserName(n);
        userBean.setType("2");

        List<UserBean> list = LitePal.where("userName = ? and password = ?", n,p).find(UserBean.class);
        if (list.size()>0){
            UserBean mUserBean1=list.get(0);

            SharedPreferences sp = getSharedPreferences("user",0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("phone", n) ;
            editor.putString("pwd", p) ;
            editor.putString("type", mUserBean1.getType()) ;
            editor.putString("id", mUserBean1.getId()+"") ;
            editor.commit();
            if (mUserBean1.getType().equals("2")){
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }else {
                Intent intent=new Intent(LoginActivity.this,AdminMainActivity.class);
                startActivity(intent);
            }
            finish();

        }else {
            SnackbarUtils.show(LoginActivity.this, "账号或者密码错，请重新输入");
            return;
        }


        //DataSupport.deleteAll(LitePalBean.class, "name =?", "张三");
        //List<LitePalBean> all = DataSupport.findAll(LitePalBean.class);
        //News news = DataSupport.find(News.class, 1);//news表中id为1的这条记录
        //List<News> newsList = DataSupport.where("commentcount > ?", "0").find(News.class);
    }

}
