package com.sunlands.intl.yingshi.ui.home.bean;

import java.util.List;

/**
 * @author yxin
 * @date 2019-12-06 - 13:18
 * @des
 */
public class AskAnswerBean {

    /**
     * hasMore : 0
     * list : [{"answerId":"1","date":"2018-11-26","title":"报考问答测试","content":"报考问答测试"},{"answerId":"5","date":"2018-11-28","title":"5555555555555555555555555","content":"报考问答测试"}]
     */

    private int hasMore;
    private List<ListBean> list;

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
         * answerId : 1
         * date : 2018-11-26
         * title : 报考问答测试
         * content : 报考问答测试
         */

        private String answerId;
        private String date;
        private String title;
        private String content;

        public String getAnswerId() {
            return answerId;
        }

        public void setAnswerId(String answerId) {
            this.answerId = answerId;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
