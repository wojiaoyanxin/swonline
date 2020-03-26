package com.sunlands.intl.yingshi.ui.activity.home.scorequery;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.multi.ScoreQueryHeadItem;
import com.sunlands.intl.yingshi.bean.multi.ScoreQueryItem;

import java.util.List;

/**
 * 文件名: ScoreQueryAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/31
 */
public class ScoreQueryAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    public ScoreQueryAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.head_score_query);
        addItemType(TYPE_LEVEL_1, R.layout.item_socre_query);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MultiItemEntity item) {

        switch (item.getItemType()) {
            case TYPE_LEVEL_0:
                ScoreQueryHeadItem lv0 = (ScoreQueryHeadItem) item;
                helper.setText(R.id.title, ((ScoreQueryHeadItem) item).getTitle());
//                setImageResource(R.id.iv_collapse_expand, !lv0.isExpanded()
//                        ? R.drawable.ic_arrow_expand : R.drawable.ic_arrow_collapse);
                break;

            case TYPE_LEVEL_1:

                final ScoreQueryItem lv1 = (ScoreQueryItem) item;

                helper.setText(R.id.tv_subject_name, lv1.getData().getTitle());//考试课程
                helper.setText(R.id.tv_credits, lv1.getData().getScore());//考试成绩
                helper.setText(R.id.tv_score, lv1.getData().getType());//考试类型

                if (TextUtils.isEmpty(lv1.getData().getTitle()) && TextUtils.isEmpty(lv1.getData().getScore())) {
                    helper.setVisible(R.id.ll, true);
                } else {
                    helper.setVisible(R.id.ll, false);
                }
//                try {
//                    double score = Double.valueOf(lv1.getData().getScore());
//                    helper.setTextColor(R.id.tv_credits, mContext.getResources().getColor(
//                            (score < 60.0 ? R.color.colorTextScoreRed : R.color.text333)));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    helper.setTextColor(R.id.tv_credits, mContext.getResources().getColor(R.color.text333));
//                }
//                helper.setBackgroundColor(R.id.ll_item_score_query, mContext.getResources().getColor(
//                        helper.getAdapterPosition() % 2 == 0 ? R.color.white : R.color.colorBgGrayF9));
                break;
        }
    }

}
