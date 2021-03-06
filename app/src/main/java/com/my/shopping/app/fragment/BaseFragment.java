package com.my.shopping.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private View mView;
    protected Bundle savedInstanceState;
    public Context mContext;
    protected LayoutInflater mInflater;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
            Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mInflater = inflater;
        this.savedInstanceState=savedInstanceState;
        mView=mInflater.inflate(getContentResourseId(), null);
        unbinder= ButterKnife.bind(this,mView);
        init();
        return mView;
    }

    protected abstract void init();

    protected abstract int getContentResourseId();

    public void startActivity(Intent intent, boolean isNeedLogin){

        if (isNeedLogin) {
            //User user = EnjoyshopApplication.getInstance().getUser();
            //if (user != null) {
            super.startActivity(intent);    //需要登录,切已经登录.直接跳到目标activity中
            //} else {
            //EnjoyshopApplication.getInstance().putIntent(intent);
            //  Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
            // super.startActivity(loginIntent);
            //}
        } else {
            super.startActivity(intent);
        }
    }

}
