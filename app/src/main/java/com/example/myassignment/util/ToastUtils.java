package com.example.myassignment.util;

import android.content.Context;
import android.widget.Toast;


public class ToastUtils {

    private ToastUtils() {
        throw new Error("U will not able to instantiate it");
    }

    /**
     * context The context
     * message The message
     */
    public static void showToastShort(Context context, String message) {
        if(message!=null)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * context The context
     * message The message
     */
    public static void showToastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
