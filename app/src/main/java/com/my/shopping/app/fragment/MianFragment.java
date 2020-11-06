package com.my.shopping.app.fragment;
 

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.my.shopping.app.R;
import com.my.shopping.app.activitys.LoginActivity;
import com.my.shopping.app.activitys.admin.OrderStateListActivity;
import com.my.shopping.app.activitys.admin.adapter.GoodsAdapter;
import com.my.shopping.app.activitys.user.AddressListActivity;
import com.my.shopping.app.activitys.user.DetailGoodsActivity;
import com.my.shopping.app.activitys.user.MyOrderListActivity;
import com.my.shopping.app.activitys.user.ShareListActivity;
import com.my.shopping.app.activitys.user.UserGoodsListActivity;
import com.my.shopping.app.activitys.user.UserOrderStateGoodsListActivity;
import com.my.shopping.app.activitys.user.UserOrderStateListActivity;
import com.my.shopping.app.beans.CommentBeans;
import com.my.shopping.app.beans.GoodsInfo;
import com.my.shopping.app.beans.UserBean;
import com.my.shopping.app.core.MyApplication;
import com.my.shopping.app.view.MoreImageView;
import com.my.shopping.app.view.SPArrowRowView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;


public class MianFragment extends BaseFragment   {
    @BindView(R.id.loginOut)
    TextView loginOut;
    @BindView(R.id.level_name_txtv)
    TextView level_name_txtv;
    @BindView(R.id.nickname_txtv)
    TextView nickname_txtv;
    @BindView(R.id.head_mimgv)
    MoreImageView head_mimgv;
    @BindView(R.id.person_balance_txtv)
    TextView person_balance_txtv;
    @BindView(R.id.orderTxt)
    TextView orderTxt;

    @BindView(R.id.person_receive_address_aview)
    SPArrowRowView person_receive_address_aview;

    @BindView(R.id.person_coupon_aview)
    SPArrowRowView person_coupon_aview;

    @BindView(R.id.txt1)
    TextView txt1;

    @BindView(R.id.txt2)
    TextView txt2;

    @BindView(R.id.product_gdv)
    GridView product_gdv;

    @BindView(R.id.txt3)
    TextView txt3;

    @BindView(R.id.img4)
    ImageView img4;

    @BindView(R.id.txt4)
    TextView txt4;
    UserBean mUserBean;
    String phone;
    List<GoodsInfo> tList=new ArrayList<>();
    @Override
    protected void init() {

        final     List<GoodsInfo> list1 = LitePal.order("purchase desc").find(GoodsInfo.class);
        if (list1.size()<3){
            tList.addAll(list1);
        }else {
            tList.add(list1.get(0));
            tList.add(list1.get(1));
        }

        if (tList.size()>0){
            GoodsAdapter adapter=new GoodsAdapter(getContext(),tList);
            product_gdv.setAdapter(adapter);
            product_gdv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(getContext(),DetailGoodsActivity.class);
                    DetailGoodsActivity.mGoodsInfo=tList.get(position);
                    startActivityForResult(intent,1);
                }
            });
        }

        for (int i=0;i<list1.size();i++){
        }
        txt1.setText("已付款");
        txt2.setText("待收货");
        txt3.setText("已完成");
        txt4.setVisibility(View.GONE);
        img4.setVisibility(View.GONE);
        person_coupon_aview.setText("动态");
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),UserOrderStateListActivity.class);
                intent.putExtra("type","已付款");
                getActivity().startActivity(intent);
            }
        });
        person_coupon_aview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ShareListActivity.class);
                getActivity().startActivity(intent);
            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),UserOrderStateListActivity.class);
                intent.putExtra("type","待收货");
                getActivity().startActivity(intent);
            }
        });
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),UserOrderStateListActivity.class);
                intent.putExtra("type","已完成");
                getActivity().startActivity(intent);
            }
        });
        final   SharedPreferences sp = MyApplication.getContext().getSharedPreferences("user",0);

        orderTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MyOrderListActivity.class);
                getActivity().startActivity(intent);
            }
        });
        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("phone", "") ;
                editor.putString("pwd","") ;
                editor.putString("type", "") ;
                editor.putString("id", "") ;
                editor.commit();
                getActivity().finish();
                Intent intent=new Intent(getContext(),LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();

            }
        });
          phone=sp.getString("phone","");
        level_name_txtv.setText("砖石会员");
        nickname_txtv.setText(phone);
        head_mimgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });

        List<UserBean> list = LitePal.where("userName = ? ",phone).find(UserBean.class);
        mUserBean=list.get(0);
        Glide.with(this).load(mUserBean.getHead()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(head_mimgv);
        person_balance_txtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<UserBean> list = LitePal.where("userName = ? ",phone).find(UserBean.class);
                mUserBean=list.get(0);
            }
        });
        person_receive_address_aview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getContext(),AddressListActivity.class);
               startActivity(intent);
            }
        });
    }
    public static final int PHOTO_REQUEST_GALLERY = 2;
    String img="";
    // 启动相册
    private void openAlbum() {

        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);

    }
    @Override
    protected int getContentResourseId() {
        return R.layout.user_mian_fragment;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_GALLERY:
                if (resultCode == RESULT_OK && data != null) {
                    img= getPath(getContext(), data.getData());
                    Glide.with(this).load(img).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(head_mimgv);
                    mUserBean.setHead(img);
                    mUserBean.update(mUserBean.getId());


                    List<CommentBeans> list = LitePal.where("userId = ? ",mUserBean.getUserName()).find(CommentBeans.class);


                    for (int i=0;i<list.size();i++){
                        CommentBeans mCommentBeans=list.get(i);
                        mCommentBeans.setHeadImg(mUserBean.getHead());
                        mCommentBeans.update(mCommentBeans.getId());
                    }
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] { split[1] };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = { column };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


}


