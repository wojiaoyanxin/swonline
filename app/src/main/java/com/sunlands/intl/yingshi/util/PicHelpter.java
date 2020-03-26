package com.sunlands.intl.yingshi.util;

import com.blankj.utilcode.util.ActivityUtils;
import com.sunlands.intl.yingshi.R;
import com.xueh.picselect.lib.PicSelector;
import com.xueh.picselect.lib.engine.GlideEngine;
import com.xueh.picselect.lib.entity.LocalMedia;

import java.util.List;

/**
 * 创 建 人: xueh
 * 创建日期: 2019/12/16 16:11
 * 备注：
 */
public class PicHelpter {
    public static void  openExternalPreview(int position, List<LocalMedia> selectList){
        PicSelector.create(ActivityUtils.getTopActivity()).themeStyle(R.style.pic_default_style).loadImageEngine(GlideEngine.createGlideEngine()).openExternalPreview(position, selectList);
    }
}
