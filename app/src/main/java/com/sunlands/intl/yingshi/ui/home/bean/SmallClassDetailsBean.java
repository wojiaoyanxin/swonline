package com.sunlands.intl.yingshi.ui.home.bean;

import java.util.List;

/**
 * @author yxin
 * @date 2019-11-30 - 15:14
 * @des
 */
public class SmallClassDetailsBean {


    /**
     * courseId : 1
     * title : 【0基础投资】教你从“没钱理财”到
     * subtitle : 【0基础投资】教你从“没钱理财”到
     * teacher : 高子泠
     * showPrice : 199
     * price : 9.9
     * thumb : https://education-1254383113.file.myqcloud.com/small/12.jpg
     * poster : https://education-1254383113.file.myqcloud.com/small/12.jpg
     * introImg : ["https://education-1254383113.file.myqcloud.com/small/12.jpg","https://education-1254383113.file.myqcloud.com/small/12.jpg","https://education-1254383113.file.myqcloud.com/small/12.jpg","https://education-1254383113.file.myqcloud.com/small/12.jpg","https://education-1254383113.file.myqcloud.com/small/12.jpg"]
     * canView : 0
     */

    private int courseId;
    private String title;
    private String subtitle;
    private String teacher;
    private String showPrice;
    private String price;
    private String thumb;
    private String poster;
    private int canView;
    private List<String> introImg;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
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

    public List<String> getIntroImg() {
        return introImg;
    }

    public void setIntroImg(List<String> introImg) {
        this.introImg = introImg;
    }
}
