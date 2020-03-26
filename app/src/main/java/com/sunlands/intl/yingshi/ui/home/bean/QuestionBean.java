package com.sunlands.intl.yingshi.ui.home.bean;

import java.util.List;

/**
 * @author yxin
 * @date 2019-12-05 - 15:43
 * @des
 */
public class QuestionBean {


    private List<ListBean> list;
    private int hasMore;

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 3
         * name : aaa
         * file_url :
         * num : 0
         */

        private int id;
        private String name;
        private String file_url;
        private int num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
