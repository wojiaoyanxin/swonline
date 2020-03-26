package com.sunlands.intl.yingshi.ui.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.home.bean.ArticleBean;


/**
 * 文件名: HomeArticleAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/7/31
 */
public class HomeArticleAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {

    public HomeArticleAdapter() {
        super(R.layout.item_home_article);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        helper.setText(R.id.tv_information_title, item.getTitle());
        helper.setText(R.id.tv_information_introduce, item.getExplain());
        if (getData().size() == helper.getPosition()) {
            helper.setVisible(R.id.viewLine, false);
        } else {
            helper.setVisible(R.id.viewLine, true);
        }
//        ImageView imageView = (ImageView) helper.getView(R.id.iv_information_cover);
//        GlideUtils.loadImageFix(mContext, item.getPoster(), imageView);
    }
}
