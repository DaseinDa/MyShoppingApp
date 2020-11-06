package com.my.shopping.app.activitys.user;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.Text;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.my.shopping.app.R;
import com.my.shopping.app.core.BaseActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;


/**
 * 定位信息
 */


public class LocationActivity   extends Activity implements CompoundButton.OnCheckedChangeListener,LocationSource,AMapLocationListener,AMap.OnMapClickListener,
        AMap.OnMarkerClickListener, RouteSearch.OnRouteSearchListener{

    private LocationSource.OnLocationChangedListener mListener;

    private boolean isFirst=true;
    private AMapLocationClient locationClient;
    private AMapLocation mmAMapLocation;
    private AMapLocationClientOption clientOption;
    MapView mapView;
    private TextView rightTxt;
    private String address="";
    private  double lon=0;
    private double lat=0;

    private DriveRouteResult mDriveRouteResult;
    private AMap aMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loc);
        mapView=findViewById(R.id.trajectoryMap);
        mapView.onCreate(savedInstanceState);
        rightTxt=findViewById(R.id.rightTxt);
        rightTxt.setText("保存");
        rightTxt.setVisibility(View.VISIBLE);
        rightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("user",0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("address",address) ;
                editor.putString("lon",lon+"") ;
                editor.putString("lat", lat+"") ;
                editor.commit();
                finish();
            }
        });
        String key=sHA1(this);
        initMap();
    }
    private void initMap(){
        if (aMap==null)
        {
            aMap=mapView.getMap();
        }
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.getUiSettings().setTiltGesturesEnabled(false);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17)) ;
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length()-1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
        }
        else {
            aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        }
    }
    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener=listener;
        locationClient=new AMapLocationClient(this);
        clientOption=new AMapLocationClientOption();
        locationClient.setLocationListener(this);

        clientOption.setInterval(10000);
        clientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//高精度定位
        clientOption.setOnceLocationLatest(true);//设置单次精确定位
        locationClient.setLocationOption(clientOption);
        locationClient.startLocation();

    }
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null&&aMapLocation != null) {
            if (aMapLocation != null &&aMapLocation.getErrorCode() == 0) {
                mmAMapLocation=aMapLocation;
                if (isFirst){
                    isFirst=false;
                    address=aMapLocation.getAddress();
                    lat=aMapLocation.getLatitude();
                    lon=aMapLocation.getLongitude();
                    mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                }

            }
        }

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }



    @Override
    public void deactivate() {

    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int i) {
        aMap.clear();// 清理地图上的所有覆盖物
        if (result.getPaths().size() > 0) {
            mDriveRouteResult = result;
            final DrivePath drivePath = mDriveRouteResult.getPaths()
                    .get(0);
            if(drivePath == null) {
                return;
            }




        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }
}
