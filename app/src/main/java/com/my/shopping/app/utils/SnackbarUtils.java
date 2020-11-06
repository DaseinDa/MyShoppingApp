package com.my.shopping.app.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class SnackbarUtils {


    public static void show(Context context, String msg) {
        Toast.makeText(context,msg ,Toast.LENGTH_SHORT).show();
    }

    public static void show(Activity activity, String msg) {
        Toast.makeText(activity,msg ,Toast.LENGTH_SHORT).show();
    }

}
