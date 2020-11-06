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
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.my.shopping.app.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;


/**
 * 定位信息
 */


public class ShowLocationActivity extends Activity {


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
        initMap();
        setMark();
    }
    private void setMark(){
        Intent intent=getIntent();
        String lat=intent.getStringExtra("lat");
        String lon=intent.getStringExtra("lon");
        Log.e("tag","lat==============="+lat);
        Log.e("tag","lon==============="+lon);
      //  lat=Double.parseDouble();
       // lon=Double.parseDouble(intent.getStringExtra("lon"));
        //LatLng mlist=new LatLng(lat,lon);
       // aMap.moveCamera(CameraUpdateFactory.changeLatLng(mlist));
    }
    private void initMap(){
        if (aMap==null)
        {
            aMap=mapView.getMap();
        }
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.getUiSettings().setTiltGesturesEnabled(false);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17)) ;
        aMap.setMyLocationEnabled(true);
    }



}
