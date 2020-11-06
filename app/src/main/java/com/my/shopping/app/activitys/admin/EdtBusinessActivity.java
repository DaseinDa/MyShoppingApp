package com.my.shopping.app.activitys.admin;


import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.my.shopping.app.R;
import com.my.shopping.app.beans.BusinessInfo;
import com.my.shopping.app.core.BaseActivity;
import com.my.shopping.app.utils.CreateString;
import com.my.shopping.app.utils.SnackbarUtils;

import butterknife.BindView;


public class EdtBusinessActivity extends BaseActivity  {
    public  static  BusinessInfo mBusinessInfo;


    @BindView(R.id.backImg)
    ImageView backImg;

    @BindView(R.id.rightTxt)
    TextView rightTxt;

    @BindView(R.id.titleTxt)
    TextView titleTxt;


    @BindView(R.id.goodsImg)
    ImageView goodsImg;

    @BindView(R.id.goodsNameTxt)
    EditText goodsNameTxt;

    @BindView(R.id.headImg)
    ImageView headImg;

    @BindView(R.id.conEdt)
    EditText conEdt;
    String type="1";

    @Override
    protected void init() {
        initTitleData();

        goodsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="1";
                openAlbum();
            }
        });
        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="2";
                openAlbum();
            }
        });
        initData();

    }

    private void initData(){
        img= mBusinessInfo.getImg();
        Glide.with(this)
                .load(img)
                .into(goodsImg);
        head= mBusinessInfo.getHeadImg();
        Glide.with(this).load(head).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(headImg);
        goodsNameTxt.setText(mBusinessInfo.getName());
        conEdt.setText(mBusinessInfo.getCon());
    }

    @Override
    protected int getContentResourseId() {
        return R.layout.activity_admin_bus_add;
    }
    private void initTitleData(){
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTxt.setText("编辑商家");
        rightTxt.setText("保存");
        rightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img==null||img.equals("")){
                    SnackbarUtils.show(EdtBusinessActivity.this,"请上传背景图片");
                    return;
                }
                if (head==null||head.equals("")){
                    SnackbarUtils.show(EdtBusinessActivity.this,"请上传商家头像");
                    return;
                }
                String gn=goodsNameTxt.getText().toString();

                String gc=conEdt.getText().toString();
                if (gn==null||gn.equals("")){
                    SnackbarUtils.show(EdtBusinessActivity.this,"请输入商家名字");
                    return;
                }

                if (gc==null||gc.equals("")){
                    SnackbarUtils.show(EdtBusinessActivity.this,"请输入商家描述");
                    return;
                }
                mBusinessInfo.setHeadImg(head);
                mBusinessInfo.setCon(gc);
                mBusinessInfo.setImg(img);
                mBusinessInfo.setName(gn);
                mBusinessInfo.setHeadImg(head);
                mBusinessInfo.update(mBusinessInfo.getId());
                finish();
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
    String img="";
    String head="";
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_GALLERY:
                if (resultCode == RESULT_OK && data != null) {

                    if (type.equals("1")){
                        img= getPath(this, data.getData());
                        Glide.with(this)
                                .load(img)
                                .into(goodsImg);
                    }else {
                        head= getPath(this, data.getData());
                        Glide.with(this).load(head).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(headImg);

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
