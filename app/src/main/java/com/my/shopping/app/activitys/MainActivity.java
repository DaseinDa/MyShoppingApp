package com.my.shopping.app.activitys;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.my.shopping.app.R;
import com.my.shopping.app.beans.Tab;
import com.my.shopping.app.core.BaseActivity;
import com.my.shopping.app.core.MessageEvent;
import com.my.shopping.app.core.tabInit;
import com.my.shopping.app.fragment.BusManagerFragment;
import com.my.shopping.app.fragment.CarFragment;
import com.my.shopping.app.fragment.FragmentTabHost;
import com.my.shopping.app.fragment.HomeFragment;
import com.my.shopping.app.fragment.ManagerFragment;
import com.my.shopping.app.fragment.MianFragment;
import com.my.shopping.app.service.MyService;
import com.my.shopping.app.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements tabInit {


    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<>();


    @Override
    protected void init() {
        initTab();
    }

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_main;
    }

    private void initTab() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

        Intent intent_ = new Intent();
        intent_.setAction("login");
       /// intent_.putExtra("money","123131"+"");
        sendBroadcast(intent_);
        Tab tab_home = new Tab(HomeFragment.class, R.string.tab_item_home, R.drawable.tab_item_home_selector);
        Tab tab_hot = new Tab(ManagerFragment.class, R.string.tab_item_category, R.drawable.tab_item_category_selector);
        Tab tab_category = new Tab(CarFragment.class, R.string.tab_item_shopcart, R.drawable
                .tab_item_shopcart_selector);
        Tab tab_mine = new Tab(MianFragment.class, R.string.tab_item_mine, R.drawable.tab_item_mine_selector);

        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_mine);

        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);
        }


        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);           //默认选中第0个

    }

    @Override
    public void onPostion(int i) {
        mTabhost.setCurrentTab(i);
    }



    private View buildIndicator(Tab tab) {

        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setImageResource(tab.getIcon());
        text.setText(tab.getTitle());

        return view;
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getType() == 0) {
            mTabhost.setCurrentTab(1);
        }
    }


    //控制物理返回键
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtils.showSafeToast(MainActivity.this, "再点一次退出");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
