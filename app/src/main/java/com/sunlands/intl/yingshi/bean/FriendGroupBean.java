package com.sunlands.intl.yingshi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.bean
 * 创 建 人: xueh
 * 创建日期: 2019/4/10 14:48
 * 备注：
 */
public class FriendGroupBean {

    public List<ListBean> getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<ListBean> list;

        public static class ListBean {
            /**
             * id : 1
             * group_name : 产品包1
             */
            public boolean isSelcect;
            public int id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGroup_name() {
                return group_name == null ? "" : group_name;
            }

            public void setGroup_name(String group_name) {
                this.group_name = group_name;
            }

            public String group_name;
        }

}
