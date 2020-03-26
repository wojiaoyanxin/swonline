package com.sunlands.intl.yingshi.ui.activity.home.apply;

import java.util.List;

/**
 * 文件名: UniversityListResponse
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/18
 */
public class UniversityListResponse {

    /**
     * totalNum : 9
     * universityList : [{"id":"6","name":"澳大利亚管理学院","name_en":"Australian Institute of Management","name_sm":"AIM","back_image":"https://education-1254383113.file.myqcloud.com/15974500122494.png","label":"中美双认证|免联考|双师教学"},{"id":"34","name":"布里斯托大学","name_en":"Bristol University","name_sm":"布尼","back_image":"https://education-1254383113.file.myqcloud.com/61055820713995.jpg","label":"英国大学"},{"id":"35","name":"美国贝翰文大学","name_en":"Belhaven University","name_sm":"BU","back_image":"https://education-1254383113.file.myqcloud.com/98439088339632.jpg","label":""},{"id":"36","name":"澳大利亚托伦斯大学","name_en":"Torrens University Australia","name_sm":"TUA","back_image":"https://education-1254383113.file.myqcloud.com/99538603882962.png","label":""},{"id":"38","name":"北京大学","name_en":"Peking University","name_sm":"PKU","back_image":"https://education-1254383113.file.myqcloud.com/87993853893705.jpg","label":"sssadd"},{"id":"5","name":"亚洲E大学","name_en":"Asia E University","name_sm":"AEU","back_image":"https://education-1254383113.file.myqcloud.com/113831596014762.jpg","label":"中美双认证|免联考|双师教学"},{"id":"1","name":"美国南方大学","name_en":"South University","name_sm":"SU","back_image":"","label":"中美双认证|免联考|双师教学"},{"id":"4","name":"美国国家路易斯大学","name_en":"National Louis University","name_sm":"NLU","back_image":"https://education-1254383113.file.myqcloud.com/139119806539611.jpg","label":"中美双认证|免联考|双师教学"},{"id":"2","name":"美国贝翰文大学","name_en":"Belhaven University","name_sm":"BU","back_image":"https://education-1254383113.file.myqcloud.com/139119898598695.jpg","label":"中美双认证|免联考|双师教学"}]
     */

    private String totalNum;
    private List<UniversityBean> universityList;

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public List<UniversityBean> getUniversityList() {
        return universityList;
    }

    public void setUniversityList(List<UniversityBean> universityList) {
        this.universityList = universityList;
    }

    public static class UniversityBean {
        /**
         * id : 6
         * name : 澳大利亚管理学院
         * name_en : Australian Institute of Management
         * name_sm : AIM
         * back_image : https://education-1254383113.file.myqcloud.com/15974500122494.png
         * label : 中美双认证|免联考|双师教学
         */

        private String id;
        private String name;
        private String name_en;
        private String name_sm;
        private String back_image;
        private String label;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameEn() {
            return name_en;
        }

        public void setNameEn(String name_en) {
            this.name_en = name_en;
        }

        public String getNameSm() {
            return name_sm;
        }

        public void setNameSm(String name_sm) {
            this.name_sm = name_sm;
        }

        public String getBackImage() {
            return back_image;
        }

        public void setBackImage(String back_image) {
            this.back_image = back_image;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
