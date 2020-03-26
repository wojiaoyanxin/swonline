package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.sunlands_live_sdk.websocket.packet.im.PullVideoMsgRecord;

/**
 * Created by yanxin on 2019/3/8.
 */

public class SmallClassChatAdapter extends BaseQuickAdapter<PullVideoMsgRecord.MessageRecord, BaseViewHolder> {

    public SmallClassChatAdapter() {
        super(R.layout.item_small_class_chat);
    }

    @Override
    protected void convert(BaseViewHolder helper, PullVideoMsgRecord.MessageRecord item) {
        ImageView lessonCover = helper.getView(R.id.iv_img);
        //   GlideUtils.loadRoundImage(mContext, item.getUser_portrait(), lessonCover);
        TextView content = helper.getView(R.id.tv_content);
        //  content.setText(item.getMessage_content());
        TextView tv_name = helper.getView(R.id.tv_name);
        String htmlText = "<font color='#55A4FF'>" + item.getUser_name() + ":  " + "</font>" + "<font color='#f5f5f5'>" + item.getMessage_content() + "</font>";
        tv_name.setText(Html.fromHtml(htmlText));
    }
}
