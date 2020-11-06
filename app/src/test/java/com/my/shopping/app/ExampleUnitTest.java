package com.my.shopping.app;

import android.util.Log;

import com.my.shopping.app.utils.CreateString;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        UserBean userBean=new UserBean();
        userBean.setId(CreateString.currentTimeLong());
        userBean.setPassword("1");
        userBean.setUserName("1");
        userBean.setType("2");


    }

    @Test
    public void queryUser() {
        UserBean userBean=new UserBean();
        userBean.setPassword("2");//账号
        userBean.setUserName("1");//密码
        userBean.setType("2");//用户类型    1  管理员  2  用户
        boolean result=UtilUserInfoTest.queryUser(userBean);
       System.out.print("查询用户结果="+result);


    }


    @Test
    public void queryOrder() {
        String orderNumber="20190322000001";
        String result=UtilOrderTest.queryOrder(orderNumber);
        System.out.print("查询订单="+result);


    }
}