package com.my.shopping.app.activitys.user;


import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.my.shopping.app.R;
import com.my.shopping.app.activitys.admin.AddGoodsActivity;
import com.my.shopping.app.activitys.user.adapter.AddressAdapter;
import com.my.shopping.app.activitys.user.adapter.ImgAdapter;
import com.my.shopping.app.beans.Address;
import com.my.shopping.app.beans.ShareBean;
import com.my.shopping.app.beans.UserBean;
import com.my.shopping.app.core.BaseActivity;
import com.my.shopping.app.core.MyApplication;
import com.my.shopping.app.utils.CreateString;
import com.my.shopping.app.utils.SnackbarUtils;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;


public class AddShareActivity extends BaseActivity  {


    @BindView(R.id.gradview)
    GridView gradview;

    @BindView(R.id.backImg)
    ImageView backImg;

    @BindView(R.id.rightTxt)
    TextView rightTxt;

    @BindView(R.id.titleTxt)
    TextView titleTxt;

    @BindView(R.id.addImg)
    ImageView addImg;

    @BindView(R.id.conEdt)
    EditText conEdt;

    List<String>  list1=new ArrayList<>();

    String phone;
    @Override
    protected void init() {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("user",0);
        phone=sp.getString("phone","");
        rightTxt.setText("分享");

        initTitleData();
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });
        rightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list1.size()==0){
                    SnackbarUtils.show(AddShareActivity.this,"请添加图片");
                    return;
                }
                String con=conEdt.getText().toString();
                if (con.equals("")||con==null){
                    SnackbarUtils.show(AddShareActivity.this,"请输入描述");
                    return;
                }
                SharedPreferences sp = MyApplication.getContext().getSharedPreferences("user",0);
                phone=sp.getString("phone","");
                List<UserBean> list = LitePal.where("userName = ? ",phone).find(UserBean.class);
                UserBean mUserBean=list.get(0);
                ShareBean mShareBean=new ShareBean();
                mShareBean.setId(CreateString.currentTimeLong());
                mShareBean.setFkId(CreateString.currentTimeLong());
                mShareBean.setCon(con);
                String path="";
                for (int i=0;i<list1.size();i++){
                    path+=list1.get(i).toString()+",";
                }
                mShareBean.setImg(path);
                mShareBean.setHead(mUserBean.getHead());
                mShareBean.setUserId(mUserBean.getUserName());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                mShareBean.setTime(df.format(new Date()));
                mShareBean.save();
                SnackbarUtils.show(AddShareActivity.this,"分享成功");
                finish();
            }
        });
    }

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_add_share;
    }
    private void initTitleData(){
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTxt.setText("添加分享");

        initData();
    }

    private void initData(){

    }
private void initDataList(){
    ImgAdapter adapter=new ImgAdapter(AddShareActivity.this,list1);
    gradview.setAdapter(adapter);
    gradview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            list1.remove(position);
            initDataList();
            return false;
        }
    });
}

    // 启动相册
    private void openAlbum() {

        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);

    }
    public static final int PHOTO_REQUEST_GALLERY = 2;
    String imgPath="";
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_GALLERY:
                if (resultCode == RESULT_OK && data != null) {
                    imgPath= getPath(this, data.getData());
                    list1.add(imgPath);
                    initDataList();
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
