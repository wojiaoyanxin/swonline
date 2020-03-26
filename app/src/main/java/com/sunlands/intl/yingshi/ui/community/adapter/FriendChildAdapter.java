package com.sunlands.intl.yingshi.ui.community.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.MsgListBean;
import com.sunlands.intl.yingshi.ui.community.view.MyFriendActivityActivity;
import com.sunlands.intl.yingshi.util.GlideUtils;

import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.adapter
 * 创 建 人: xueh
 * 创建日期: 2019/2/21 12:35
 * 备注：
 */
public class FriendChildAdapter extends CommonAdapter<MsgListBean.ListBean.TeacherBean.FriendsBean> {
    private boolean mIsTeacher;

    public FriendChildAdapter(Context context, List<MsgListBean.ListBean.TeacherBean.FriendsBean> datas, boolean isTeacher) {
        super(context, datas, R.layout.item_msg_child_layout);
        this.mIsTeacher = isTeacher;
    }

    @Override
    public void convert(ViewHolder holder, MsgListBean.ListBean.TeacherBean.FriendsBean mFriendsBean) {
        holder.setText(R.id.tv_user_name, mFriendsBean.getName());
        holder.setText(R.id.tv_teacher_name, "");
        if (mIsTeacher) {
            holder.setText(R.id.tv_user_name, "");
            holder.setText(R.id.tv_user_introduction, "");
            holder.setText(R.id.tv_teacher_name, mFriendsBean.getName());
        } else {
            holder.setText(R.id.tv_user_introduction, mFriendsBean.getUniversity());
        }
        GlideUtils.loadImage(ApplicationsHelper.context(), mFriendsBean.getAvatar(), holder.getView(R.id.iv_user_head));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsTeacher) {
                    return;
                }
                Intent mIntent = new Intent(mContext, MyFriendActivityActivity.class);
                mIntent.putExtra(MyFriendActivityActivity.key, mFriendsBean);
                ActivityUtils.startActivity(mIntent);
            }
        });
    }
}
