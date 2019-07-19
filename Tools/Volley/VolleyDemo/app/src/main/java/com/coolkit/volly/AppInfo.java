package com.coolkit.volly;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AppInfo {
    // 是否是测试环境
    public static final boolean isTestServer = false;

    public static String getAppID(){
        if (AppInfo.isTestServer)
        {
            return "N43vkP0FIKXZ2WedvrDJZF777a0Y7FNp";
        }else {
            return "8SEqJvSnfRDroXjPgXfiiOY5cjPLHWZ1";
        }
    }

    public static String getAppSecret(){
        if (isTestServer)
        {
            return "wtBv211qWBqBu9vqVBu6780f0n1zT8fe";
        }else {
            return "IEUJ0adXmBxipqdUM9xd9DtDKbQn7DuB";
        }
    }

    public static String getAppName(Context context) {
        String mVersionName = null;
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            mVersionName = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return  context.getResources().getString(R.string.app_name) + "V_" + mVersionName;
    }

    public static String getAppVersion(Context context) {
        String mVersionName = null;
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            mVersionName = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return  mVersionName;
    }

    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
