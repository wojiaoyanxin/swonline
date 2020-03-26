package com.sunlands.intl.yingshi.bean;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.comm_core.rvadapter.mul.IMulTypeHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.community.adapter.FriendChildAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.bean
 * 创 建 人: xueh
 * 创建日期: 2019/2/19 17:13
 * 备注：
 */
public class MsgListBean {
    public interface IMsgBaseBean {

    }

    public ListBean list;
    public int message_number;
    public boolean isTeacher;


    public ListBean getList() {
        return list;
    }

    public void setList(ListBean mList) {
        list = mList;
    }

    public int getMessage_number() {
        return message_number;
    }

    public void setMessage_number(int mMessage_number) {
        message_number = mMessage_number;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean mTeacher) {
        isTeacher = mTeacher;
    }

    public static class ListBean {
        public List<TeacherBean> teacher;
        public List<StudentBean> student;

        @Override
        public String toString() {
            return "ListBean{" +
                    "teacher=" + teacher +
                    ", student=" + student +
                    '}';
        }

        public List<TeacherBean> getTeacher() {
            if (teacher == null) {
                return new ArrayList<>();
            }
            return teacher;
        }

        public void setTeacher(List<TeacherBean> mTeacher) {
            teacher = mTeacher;
        }

        public List<StudentBean> getStudent() {
            if (student == null) {
                return new ArrayList<>();
            }
            return student;
        }

        public void setStudent(List<StudentBean> mStudent) {
            student = mStudent;
        }

        public static class TeacherBean implements IMsgBaseBean, IMulTypeHelper {
            public String package_id;
            public String package_name;

            public String getPackage_id() {
                return package_id == null ? "" : package_id;
            }

            public void setPackage_id(String mPackage_id) {
                package_id = mPackage_id;
            }

            public String getPackage_name() {
                return package_name == null ? "" : package_name;
            }

            public void setPackage_name(String mPackage_name) {
                package_name = mPackage_name;
            }

            public String getGroup_id() {
                return group_id == null ? "" : group_id;
            }

            public void setGroup_id(String mGroup_id) {
                group_id = mGroup_id;
            }

            public List<FriendsBean> getFriends() {
                if (friends == null) {
                    return new ArrayList<>();
                }
                return friends;
            }

            public void setFriends(List<FriendsBean> mFriends) {
                friends = mFriends;
            }

            public String group_id;
            public List<FriendsBean> friends;

            @Override
            public String toString() {
                return "TeacherBean{" +
                        "package_id='" + package_id + '\'' +
                        ", package_name='" + package_name + '\'' +
                        ", group_id='" + group_id + '\'' +
                        ", friends=" + friends +
                        '}';
            }

            @Override
            public int getItemLayoutId() {
                return R.layout.item_msg_group_layout;
            }

            @Override
            public void onBind(ViewHolder holder) {
                holder.setText(R.id.tv_group_name, getPackage_name());
                RecyclerView rv_child_content = holder.getView(R.id.rv_child_content);
                rv_child_content.setLayoutManager(new LinearLayoutManager(ApplicationsHelper.context()));
                rv_child_content.setAdapter(new FriendChildAdapter(ApplicationsHelper.context(),getFriends(),true));
            }

            public static class FriendsBean implements Serializable {
                public String user_id;
                public String group_id;
                public String avatar;
                public String name;
                public String initials;
                public boolean isTeacher;

                public String getUniversity() {
                    return university == null ? "" : university;
                }

                public void setUniversity(String mUniversity) {
                    university = mUniversity;
                }

                public String university;

                public String getUser_id() {
                    return user_id == null ? "" : user_id;
                }

                public void setUser_id(String mUser_id) {
                    user_id = mUser_id;
                }

                public String getGroup_id() {
                    return group_id == null ? "" : group_id;
                }

                public void setGroup_id(String mGroup_id) {
                    group_id = mGroup_id;
                }

                public String getAvatar() {
                    return avatar == null ? "" : avatar;
                }

                @Override
                public String toString() {
                    return "FriendsBean{" +
                            "user_id='" + user_id + '\'' +
                            ", group_id='" + group_id + '\'' +
                            ", avatar='" + avatar + '\'' +
                            ", name='" + name + '\'' +
                            ", initials='" + initials + '\'' +
                            ", isTeacher=" + isTeacher +
                            ", university='" + university + '\'' +
                            '}';
                }

                public void setAvatar(String mAvatar) {
                    avatar = mAvatar;
                }

                public String getName() {
                    return name == null ? "" : name;
                }

                public void setName(String mName) {
                    name = mName;
                }

                public String getInitials() {
                    return initials == null ? "" : initials;
                }

                public void setInitials(String mInitials) {
                    initials = mInitials;
                }

            }
        }

        public static class StudentBean implements IMsgBaseBean, IMulTypeHelper {
            public String package_id;
            public String package_name;
            public String group_id;

            public List<TeacherBean.FriendsBean> getFriends() {
                if (friends == null) {
                    return new ArrayList<>();
                }
                return friends;
            }

            public void setFriends(List<TeacherBean.FriendsBean> mFriends) {
                friends = mFriends;
            }

            public List<TeacherBean.FriendsBean> friends;

            public String getPackage_id() {
                return package_id == null ? "" : package_id;
            }

            public void setPackage_id(String mPackage_id) {
                package_id = mPackage_id;
            }

            public String getPackage_name() {
                return package_name == null ? "" : package_name;
            }

            public void setPackage_name(String mPackage_name) {
                package_name = mPackage_name;
            }

            public String getGroup_id() {
                return group_id == null ? "" : group_id;
            }

            public void setGroup_id(String mGroup_id) {
                group_id = mGroup_id;
            }

            @Override
            public String toString() {
                return "StudentBean{" +
                        "package_id='" + package_id + '\'' +
                        ", package_name='" + package_name + '\'' +
                        ", group_id='" + group_id + '\'' +
                        ", friends=" + friends +
                        '}';
            }


            @Override
            public int getItemLayoutId() {
                return R.layout.item_msg_group_layout;
            }

            @Override
            public void onBind(ViewHolder holder) {
                holder.setText(R.id.tv_group_name, getPackage_name());
                RecyclerView rv_child_content = holder.getView(R.id.rv_child_content);
                rv_child_content.setLayoutManager(new LinearLayoutManager(ApplicationsHelper.context()));
                rv_child_content.setAdapter(new FriendChildAdapter(ApplicationsHelper.context(),getFriends(),false));
            }
        }
    }
    public static class titleBean implements IMulTypeHelper {
        public titleBean(String title) {
            this.title = title;
        }

        String title;
        @Override
        public int getItemLayoutId() {
            return R.layout.item_friend_title_layout;
        }

        @Override
        public void onBind(ViewHolder holder) {
            holder.setText(R.id.tv_rv_head_name,title);
        }
    }
}
