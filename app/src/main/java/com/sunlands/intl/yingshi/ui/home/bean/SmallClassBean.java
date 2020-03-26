package com.sunlands.intl.yingshi.ui.home.bean;

import java.util.List;

/**
 * @author yxin
 * @date 2019-11-29 - 16:48
 * @des
 */
public class SmallClassBean {


    /**
     * catList : [{"catId":"1","name":"健康美丽"}]
     * hasMore : 0
     * paginationList : [{"courseId":"1","title":"【0基础投资】教你从\u201c没钱理财\u201d到\u201c年收益10万\u201d","subtitle":"【0基础投资】教你从\u201c没钱理财\u201d到\u201c年收益10万\u201d","teacher":"高子泠","catName":"健康美丽","showPrice":199,"price":9.9,"thumb":"https://education-1254383113.file.myqcloud.com/small/12.jpg","poster":"https://education-1254383113.file.myqcloud.com/small/12.jpg","canView":0}]
     */

    private int hasMore;
    private List<CatListBean> catList;
    private List<PaginationListBean> paginationList;

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public List<CatListBean> getCatList() {
        return catList;
    }

    public void setCatList(List<CatListBean> catList) {
        this.catList = catList;
    }

    public List<PaginationListBean> getPaginationList() {
        return paginationList;
    }

    public void setPaginationList(List<PaginationListBean> paginationList) {
        this.paginationList = paginationList;
    }

    public static class CatListBean {
        /**
         * catId : 1
         * name : 健康美丽
         */

        private String catId;
        private String name;

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class PaginationListBean {
        /**
         * courseId : 1
         * title : 【0基础投资】教你从“没钱理财”到“年收益10万”
         * subtitle : 【0基础投资】教你从“没钱理财”到“年收益10万”
         * teacher : 高子泠
         * catName : 健康美丽
         * showPrice : 199
         * price : 9.9
         * thumb : https://education-1254383113.file.myqcloud.com/small/12.jpg
         * poster : https://education-1254383113.file.myqcloud.com/small/12.jpg
         * canView : 0
         */

        private String courseId;
        private String title;
        private String subtitle;
        private String teacher;
        private String catName;
        private String showPrice;
        private String price;
        private String thumb;
        private String poster;
        private int canView;

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getShowPrice() {
            return showPrice;
        }

        public void setShowPrice(String showPrice) {
            this.showPrice = showPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public int getCanView() {
            return canView;
        }

        public void setCanView(int canView) {
            this.canView = canView;
        }
    }
}
