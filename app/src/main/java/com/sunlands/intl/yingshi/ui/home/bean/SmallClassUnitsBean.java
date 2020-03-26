package com.sunlands.intl.yingshi.ui.home.bean;

import java.util.List;

/**
 * @author yxin
 * @date 2019-12-02 - 15:24
 * @des
 */
public class SmallClassUnitsBean {


    /**
     * list : [{"id":76,"teach_unit_id":930002,"small_course_id":1,"teach_unit_name":"尚德英语课Level-3 写作1","teacher":"高子泠","live_id":774819,"replay_id":1822102,"start_time":"2018-11-08 19:30:00","end_time":"2018-11-08 21:30:00","thumb":"https://education-1254383113.file.myqcloud.com/small/12.jpg"},{"id":77,"teach_unit_id":930003,"small_course_id":1,"teach_unit_name":"尚德英语课Level-3 写作2","teacher":"高子泠","live_id":774819,"replay_id":1823668,"start_time":"2018-11-10 19:30:00","end_time":"2018-11-10 21:30:00","thumb":"https://education-1254383113.file.myqcloud.com/small/12.jpg"},{"id":78,"teach_unit_id":930004,"small_course_id":1,"teach_unit_name":"尚德英语课Level-3 写作3","teacher":"高子泠","live_id":774819,"replay_id":1825927,"start_time":"2018-11-13 19:30:00","end_time":"2018-11-13 21:30:00","thumb":"https://education-1254383113.file.myqcloud.com/small/12.jpg"},{"id":79,"teach_unit_id":930005,"small_course_id":1,"teach_unit_name":"尚德英语课Level-3 写作4","teacher":"高子泠","live_id":774819,"replay_id":1827783,"start_time":"2018-11-15 19:30:00","end_time":"2018-11-15 21:30:00","thumb":"https://education-1254383113.file.myqcloud.com/small/12.jpg"},{"id":80,"teach_unit_id":930006,"small_course_id":1,"teach_unit_name":"尚德英语课Level-3 写作5","teacher":"高子泠","live_id":774819,"replay_id":1829257,"start_time":"2018-11-17 19:30:00","end_time":"2018-11-17 21:30:00","thumb":"https://education-1254383113.file.myqcloud.com/small/12.jpg"}]
     * totalNum : 5
     */

    private int totalNum;
    private List<ListBean> list;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 76
         * teach_unit_id : 930002
         * small_course_id : 1
         * teach_unit_name : 尚德英语课Level-3 写作1
         * teacher : 高子泠
         * live_id : 774819
         * replay_id : 1822102
         * start_time : 2018-11-08 19:30:00
         * end_time : 2018-11-08 21:30:00
         * thumb : https://education-1254383113.file.myqcloud.com/small/12.jpg
         */

        private int id;
        private int teach_unit_id;
        private int small_course_id;
        private String teach_unit_name;
        private String teacher;
        private int live_id;
        private int replay_id;
        private String start_time;
        private String end_time;
        private String thumb;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTeach_unit_id() {
            return teach_unit_id;
        }

        public void setTeach_unit_id(int teach_unit_id) {
            this.teach_unit_id = teach_unit_id;
        }

        public int getSmall_course_id() {
            return small_course_id;
        }

        public void setSmall_course_id(int small_course_id) {
            this.small_course_id = small_course_id;
        }

        public String getTeach_unit_name() {
            return teach_unit_name;
        }

        public void setTeach_unit_name(String teach_unit_name) {
            this.teach_unit_name = teach_unit_name;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public int getLive_id() {
            return live_id;
        }

        public void setLive_id(int live_id) {
            this.live_id = live_id;
        }

        public int getReplay_id() {
            return replay_id;
        }

        public void setReplay_id(int replay_id) {
            this.replay_id = replay_id;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }
}
