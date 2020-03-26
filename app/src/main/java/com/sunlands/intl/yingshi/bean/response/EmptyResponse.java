package com.sunlands.intl.yingshi.bean.response;

import com.sunlands.intl.yingshi.bean.EmptyBean;

/**
 * 文件名: EmptyResponse
 * 描    述: [空数据模型]
 * 创建人: duzzi
 * 创建时间: 2018/8/20
 */
public class EmptyResponse extends BaseResponse {

    /**
     * data : {}
     */

    private EmptyBean data;

    public EmptyBean getData() {
        return data;
    }

    public void setData(EmptyBean data) {
        this.data = data;
    }

}
