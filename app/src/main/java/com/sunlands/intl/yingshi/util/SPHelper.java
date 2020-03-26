package com.sunlands.intl.yingshi.util;


import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sunlands.intl.yingshi.bean.PageBean;
import com.sunlands.intl.yingshi.constant.Api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xuehao@duia.com create time: 2019/2/14 16:31
 * tag: class//
 * description:
 */
public class SPHelper {

    public static void setUserPhone(String mPhone) {
        SPUtils.getInstance().put("userPhone", mPhone);
    }

    public static String getUserPhone() {
        return SPUtils.getInstance().getString("userPhone", "").trim();
    }

//    public static void setUserMajor(String major) {
//        if (CommonUtils.checkString(major)) {
//            SPHelp.getInstance().put("UserMajor", major);
//        }
//    }
//
//    public static String getUserMajor() {
//        return SPHelp.getInstance().getString("UserMajor", "国际硕士").replaceAll("-","").trim();
//    }


    public static void setLocalTime(Long time) {
        SPUtils.getInstance().put(Api.KEY_LOCAL_TIME, time);
    }
    public static long getLocalTime() {
        return SPUtils.getInstance().getLong(Api.KEY_LOCAL_TIME);
    }

    public static void isFirstLoad(Boolean b) {
        SPUtils.getInstance().put("first", b);
    }

    public static boolean getFirstLoad() {
        return SPUtils.getInstance().getBoolean("first", true);
    }

    public static void setAPPStartCount(int count) {
        SPUtils.getInstance().put("APPStartCount", count);
    }

    public static int getAPPStartCount() {
        return SPUtils.getInstance().getInt("APPStartCount", 0);
    }
    public static void setFindComm(String values) {
        if (!getFindComm().contains(values)) {
            values = values + "#" + getFindComm();
            SPUtils.getInstance().put("FindComm", values.trim());
        }
    }

    public static void deleteFindComm(String values) {
        if (getFindComm().contains(values)) {
            SPUtils.getInstance().put("FindComm", getFindComm().replaceAll(values + "#", "").trim());
        }
    }

    public static String getFindComm() {
        return SPUtils.getInstance().getString("FindComm", "").trim();
    }


    public static void isSelectAllShow(Boolean b) {
        SPUtils.getInstance().put("select", b);
    }


    public static boolean getSelectAllShow() {
        return SPUtils.getInstance().getBoolean("select", false);
    }

    public static void isSelectAllDownLoad(Boolean b) {
        SPUtils.getInstance().put("select_download", b);
    }


    public static boolean getSelectAllDownLoad() {
        return SPUtils.getInstance().getBoolean("select_download", false);
    }

    public static void setPageList(String pageList) {
        SPUtils.getInstance().put("list", pageList);
    }

    public static String getPagelist() {
        return SPUtils.getInstance().getString("list");
    }

    /**
     * 保存List
     *
     * @param datalist
     */
    public static void setDataList(List<PageBean> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        setPageList(strJson);
    }


    /**
     * 获取List
     *
     * @return
     */
    public static List<PageBean> getDataList() {

        List<PageBean> datalist = new ArrayList<>();

        String strJson = getPagelist();
        if (TextUtils.isEmpty(strJson)) {
            return datalist;
        }

        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<PageBean>>() {
        }.getType());
        return datalist;

    }

    public static int getCurrentPage(List<PageBean> datalist, String mFilePath) {

        if (datalist == null) {
            return 0;
        }
        for (int i = 0; i < datalist.size(); i++) {
            PageBean pageBean = datalist.get(i);
            if (pageBean.getFilePath().equals(mFilePath)) {
                return pageBean.getPage();
            }
        }
        return 0;
    }

    public static String getCurrentProgress(List<PageBean> datalist, String mFilePath) {

        if (datalist == null) {
            return "0";
        }
        for (int i = 0; i < datalist.size(); i++) {
            PageBean pageBean = datalist.get(i);
            if (pageBean.getFilePath().equals(mFilePath)) {
                return pageBean.getProgress();
            }
        }
        return "0";
    }


    public static void setSubjectList(String listist) {
        SPUtils.getInstance().put("SBlist", listist);
    }

    public static String getSubjectList() {
        return SPUtils.getInstance().getString("SBlist");
    }


}
