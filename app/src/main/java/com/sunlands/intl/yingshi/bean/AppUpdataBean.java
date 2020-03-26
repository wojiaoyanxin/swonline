package com.sunlands.intl.yingshi.bean;

/**
 * 创 建 人: xueh
 * 创建日期: 2019/5/9 19:39
 * 备注：APP在线更新数据模型
 */
public class AppUpdataBean {

    /**
     * hasNew : 1
     * msg : 存在版本更新
     * info : {"id":"14","name":"1.3.1","summary":"1.3.1","isCompel":0,"version":131,"url":""}
     */

    public int hasNew;

    public int getHasNew() {
        return hasNew;
    }

    public void setHasNew(int hasNew) {
        this.hasNew = hasNew;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String msg;
    public InfoBean info;

    public static class InfoBean {
        /**
         * id : 14
         * name : 1.3.1
         * summary : 1.3.1
         * isCompel : 0
         * version : 131
         * url :
         */

        public String id;
        public String name;
        public String summary;

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSummary() {
            return summary == null ? "" : summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getIsCompel() {
            return isCompel;
        }

        public void setIsCompel(int isCompel) {
            this.isCompel = isCompel;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getUrl() {
            return url == null ? "" : url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int isCompel;
        public int version;
        public String url;
    }
}
