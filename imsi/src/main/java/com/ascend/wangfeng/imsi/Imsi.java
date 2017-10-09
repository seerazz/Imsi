package com.ascend.wangfeng.imsi;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.List;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by fengye on 2017/8/29.
 * email 1040441325@qq.com
 */

public class Imsi {
    public static boolean isMultiSim(Context context) {
        boolean result = false;
        if (Build.VERSION.SDK_INT < 23) return false;
        TelecomManager telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
            List<PhoneAccountHandle> phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();
            result = phoneAccountHandleList.size() >= 2;
        //华为双卡手机,只有一张卡时无法使用反射读数据
        //if (getSubscriberId(context,1)==null)result = false;
        return result;
    }
    public static int getSubId(int slotId, Context context) {
        Uri uri = Uri.parse("content://telephony/siminfo");
        Cursor cursor = null;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            cursor = contentResolver.query(uri, new String[] {"_id", "sim_id"}, "sim_id = ?", new String[] {String.valueOf(slotId)}, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    return cursor.getInt(cursor.getColumnIndex("_id"));
                }
            }
        } catch (Exception e) {
            Log.e("getSubId", e.toString());
        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
        if (Build.VERSION.SDK_INT >=21){
            return SubscriptionManager.getDefaultSubscriptionId();
        }
        return -1;
    }
    public static Class[] getMethodParamTypes(String methodName) {
        Class[] params = null;
        try {
            Method[] methods = TelephonyManager.class.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methodName.equals(methods[i].getName())) {
                    params = methods[i].getParameterTypes();
                    if (params.length >= 1) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            Log.e("getSubId", e.toString());
        }
        return params;
    }
    public static Object getPhoneInfo(int subId, String methodName, Context context) {
        Object value = null;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= 21) {
                Method method = tm.getClass().getMethod(methodName, getMethodParamTypes(methodName));
                if (subId >= 0) {
                    value = method.invoke(tm, subId);
                }
            }
        } catch (Exception e) {
            Log.e("getSubId", e.toString());
        }
        return value;
    }
    public static String getDeviced(Context context, int soltId) {
        return (String) getPhoneInfo(soltId,"getDeviceId", context);
    }
    // imsi
    public static String getSubscriberId( Context context,int subId) {
        String imsi = (String) getPhoneInfo(subId, "getSubscriberId", context);
        return imsi;
    }
    public static String getDefaultDeviced(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        return manager.getDeviceId();
    }
    public static String getDefaultSubscriberId(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        return manager.getSubscriberId();
    }
}