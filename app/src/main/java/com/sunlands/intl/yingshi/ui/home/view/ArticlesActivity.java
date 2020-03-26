package com.sunlands.intl.yingshi.ui.home.view;


import com.sunlands.intl.yingshi.common.CommonActivity;


public class ArticlesActivity extends CommonActivity<Object> {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public String getTitleText() {
        return null;
    }

//    @BindView(R.id.vg_back)
//    RelativeLayout mVgBack;
//    @BindView(R.id.tv_title)
//    TextView mTvTitle;
//    @BindView(R.id.tv_header_right)
//    TextView mTvHeaderRight;
//    @BindView(R.id.iv_menu)
//    ImageView mIvMenu;
//    @BindView(R.id.cl_header)
//    ConstraintLayout mClHeader;
//    @BindView(R.id.recycler_view)
//    RecyclerView mRecyclerView;
//    @BindView(R.id.swipe_refresh_layout)
//    SwipeRefreshLayout mSwipeRefreshLayout;
//    private HomeArticleAdapter mHomeArticleAdapter;
//
//    @Override
//    protected void initPresenter() {
//        mPresenter = new ArticleListPresenter(this, articleListView);
//    }
//
//    ArticleListContract.IArticleListView articleListView = new ArticleListContract.IArticleListView() {
//        @Override
//        public void onGetArticleListSuccess(boolean isRefresh, boolean hasMore, List<ArticleBean> articleBeanList) {
//            setData(isRefresh, hasMore, articleBeanList);
//            mSwipeRefreshLayout.setRefreshing(false);
//            if (isRefresh) mHomeArticleAdapter.setEnableLoadMore(true);
//        }
//
//        @Override
//        public void onGetArticleListFailed(boolean isRefresh, int code, String msg) {
//            mSwipeRefreshLayout.setRefreshing(false);
//            if (isRefresh) mHomeArticleAdapter.setEnableLoadMore(true);
//        }
//
//        @Override
//        public void showToast(String t) {
//            ToastUtils.showShort(t);
//        }
//    };
//
//    private void setData(boolean isRefresh, boolean hasMore, List<ArticleBean> articleBeanList) {
//        if (isRefresh) {
//            mHomeArticleAdapter.setNewData(articleBeanList);
//        } else {
//            if (!Utils.isEmpty(articleBeanList))
//                mHomeArticleAdapter.addData(articleBeanList);
//        }
//        if (!hasMore) {
//            mHomeArticleAdapter.loadMoreEnd();
//        } else {
//            mHomeArticleAdapter.loadMoreComplete();
//        }
//    }
//
//    @Override
//    protected void initView(Bundle savedInstanceState) {
//        mSwipeRefreshLayout.setRefreshing(true);
//        mTvTitle.setText(R.string.college_news);
//        mVgBack.setOnClickListener(v -> onBackPressed());
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.addItemDecoration(new SpaceItemDecoration(
//                CommonUtils.dip2px( 7), 0
//        ));
//        mHomeArticleAdapter = new HomeArticleAdapter();
//        mHomeArticleAdapter.setOnLoadMoreListener(this::loadMore, mRecyclerView);
//        mHomeArticleAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
//        mHomeArticleAdapter.setLoadMoreView(new CustomLoadMoreView());
//        mRecyclerView.setAdapter(mHomeArticleAdapter);
//        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ArticleBean item = mHomeArticleAdapter.getItem(position);
//                if (item != null) {
//                    ArticleDetailActivity.show(ArticlesActivity.this, item.getUrl());
//                }
//            }
//        });
//        mSwipeRefreshLayout.setOnRefreshListener(this::getData);
//    }
//
//    private void loadMore() {
//        mPresenter.getArticleList(false, mPresenter.getNextPageIndex());
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_articles;
//    }
//
//    @Override
//    protected void getData() {
//        mHomeArticleAdapter.setEnableLoadMore(false);
//        mPresenter.getArticleList(true, Constants.DEFAULT_PAGE);
//    }

}
