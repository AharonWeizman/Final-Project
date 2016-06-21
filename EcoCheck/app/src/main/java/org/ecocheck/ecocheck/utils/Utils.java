package org.ecocheck.ecocheck.utils;

/**
 * Created by Ron on 26/05/2016.
 */


import android.app.Activity;
import android.widget.Toast;


public class Utils {

    public static void showToast(Activity activity, String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

}

