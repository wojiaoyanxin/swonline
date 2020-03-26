package com.sunlands.intl.yingshi.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.statisti_lib.GetDeviceId;

import java.util.List;

/**
 * app工具类
 */
public class AppUtils {

    /**
     * 获取设备唯一id
     *
     * @return
     */
    public static String getUniquePsuedoID() {
        return GetDeviceId.getDeviceId(Utils.getContext());
    }

    public static void getAppList(Context context) {
        PackageManager pm = context.getPackageManager();
        // Return a List of all packages that are installed on the device.
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            // 判断系统/非系统应用
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) // 非系统应用
            {
                System.out.println("MainActivity.getAppList, packageInfo=" + packageInfo.packageName);
            } else {
                // 系统应用
            }
        }
    }
}