package com.xzjc.damont.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2015/12/3.
 */
public class NetConnCheck {
    private static final int WIFI = 0;
    private static final int CMNET = 1;
    private static final int CMWAP = 2;
    private static int netType = -1;

    /**
     * 返回當前手機連網狀態，0 - WIFI，1 - CMNET，2 - CMWAP；
     * @param context
     * @return
     */
    public static int getNetConnType(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null){
            return netType;
        }
        int nType = networkInfo.getType();
        if(nType == ConnectivityManager.TYPE_MOBILE){
            if(networkInfo.getExtraInfo().toLowerCase().equals("cmnet")){
                netType = CMNET;
            }else {
                netType = CMWAP;
            }
        }else if(nType == ConnectivityManager.TYPE_WIFI){
            netType = WIFI;
        }
        return netType;
    }
}
