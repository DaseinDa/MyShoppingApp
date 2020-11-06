package com.my.shopping.app.fragment;


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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.my.shopping.app.R;
import com.my.shopping.app.activitys.AdminHomeMainActivity;
import com.my.shopping.app.beans.HomeBean;
import com.my.shopping.app.beans.UserBean;
import com.my.shopping.app.core.tabInit;
import com.my.shopping.app.utils.CreateString;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;


public class AdminHomeFragment extends BaseFragment   {


    @BindView(R.id.img1)
    ImageView img1;

    @BindView(R.id.imgAdd)
    ImageView imgAdd;

    @BindView(R.id.img2)
    ImageView img2;

    @BindView(R.id.img3)
    ImageView img3;

    @BindView(R.id.img4)
    ImageView img4;

    @BindView(R.id.img5)
    ImageView img5;

    @BindView(R.id.img6)
    ImageView img6;

    @BindView(R.id.img7)
    ImageView img7;

    @BindView(R.id.img8)
    ImageView img8;

    @BindView(R.id.img9)
    ImageView img9;
    String type="";

    @Override
    protected void init() {
        /*
        home_menu_categroy_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity()instanceof tabInit){
                    ((tabInit)getActivity()).onPostion(3);
                }
            }
        });
        */
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="1";
                openAlbum();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="2";
                openAlbum();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="3";
                openAlbum();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="4";
                openAlbum();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="5";
                openAlbum();
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="6";
                openAlbum();
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="7";
                openAlbum();
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="8";
                openAlbum();
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="9";
                openAlbum();
            }
        });

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),AdminHomeMainActivity.class);
                startActivity(intent);
            }
        });
        initImgView();

    }

    @Override
    protected int getContentResourseId() {
        return R.layout.home_fragment_admin;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    private void initImgView(){
        List<HomeBean> list = LitePal.findAll(HomeBean.class);
        if (list.size()>0){
            HomeBean mHomeBean=list.get(0);
            Glide.with(getContext())
                    .load(mHomeBean.getImg1())
                    .into(img1);
            Glide.with(getContext())
                    .load(mHomeBean.getImg2())
                    .into(img2);
            Glide.with(getContext())
                    .load(mHomeBean.getImg3())
                    .into(img3);
            Glide.with(getContext())
                    .load(mHomeBean.getImg4())
                    .into(img4);
            Glide.with(getContext())
                    .load(mHomeBean.getImg5())
                    .into(img5);
            Glide.with(getContext())
                    .load(mHomeBean.getImg6())
                    .into(img6);
            Glide.with(getContext())
                    .load(mHomeBean.getImg7())
                    .into(img7);
            Glide.with(getContext())
                    .load(mHomeBean.getImg8())
                    .into(img8);
            Glide.with(getContext())
                    .load(mHomeBean.getImg9())
                    .into(img9);

        }

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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_GALLERY:
                if (resultCode == RESULT_OK && data != null) {
                    List<HomeBean> list = LitePal.findAll(HomeBean.class);
                    HomeBean mHomeBean=new HomeBean();
                    if (list.size()>0){
                          mHomeBean=list.get(0);
                    }else {
                        mHomeBean.setId(CreateString.currentTimeLong());
                    }
                    img= getPath(getContext(), data.getData());
                    if (type.equals("1")){
                        mHomeBean.setImg1(img);

                    }else if (type.equals("2")){

                        mHomeBean.setImg2(img);
                    }else if (type.equals("3")){

                        mHomeBean.setImg3(img);
                    }else if (type.equals("4")){

                        mHomeBean.setImg4(img);
                    }else if (type.equals("5")){

                        mHomeBean.setImg5(img);
                    }else if (type.equals("6")){

                        mHomeBean.setImg6(img);
                    }
                    else if (type.equals("7")){

                        mHomeBean.setImg7(img);
                    }
                    else if (type.equals("8")){

                        mHomeBean.setImg8(img);
                    }
                    else if (type.equals("9")){

                        mHomeBean.setImg9(img);
                    }

                    if (list.size()>0){
                        mHomeBean.update(mHomeBean.getId());
                    }else {
                        mHomeBean.save();
                    }

                    initImgView();
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


