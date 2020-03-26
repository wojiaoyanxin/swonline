package com.sunlands.intl.yingshi.ui.community.view;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.sunlands.comm_core.base.DActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.OnItemClick;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.intl.yingshi.R;

import java.util.Arrays;

/**
 * 举报页面
 */
public class ReportActivity extends DActivity {
    String[] strs = {"广告", "色情", "政治有害", "图片/文章侵权", "图片/文章侵权", "身份作假", "其他（辱骂、恶意灌水等）"};
    private TextView mTv_title_right;
    private ImageView mIv_title_back;
    private RecyclerView mRv_report;
    private EditText mEt_report_content;
    private ConstraintLayout mNsl_report;

    @Override
    public void onClick(View v) {
        if (v == mIv_title_back) {
            onBackPressed();
        } else if (v == mTv_title_right) {

        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_report;
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        TextView tv_title_content = FBIA(R.id.tv_title_content);
        tv_title_content.setText("举报");
        mTv_title_right = FBIA(R.id.tv_title_right);
        mTv_title_right.setText("提交");
        mTv_title_right.setVisibility(View.VISIBLE);
        mIv_title_back = FBIA(R.id.iv_title_back);
        mRv_report = FBIA(R.id.rv_report);
        mEt_report_content = FBIA(R.id.et_report_content);
        mNsl_report = FBIA(R.id.nsl_report);
    }

    @Override
    public void initDataAfterView() {
        mRv_report.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<String> commonAdapter = new CommonAdapter<String>(this, Arrays.asList(strs), R.layout.item_report_layout) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.tv_report_name, s);
            }
        };
        mRv_report.setAdapter(commonAdapter);
        commonAdapter.setOnItemClickListener(new OnItemClick() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    if (i == position) {
                        ((RadioButton) parent.getChildAt(i).findViewById(R.id.rb_report)).setChecked(true);
                    } else {
                        ((RadioButton) parent.getChildAt(i).findViewById(R.id.rb_report)).setChecked(false);
                    }
                }
            }
        });
        KeyboardUtils.registerSoftInputChangedListener(this, new KeyboardUtils.OnSoftInputChangedListener() {
            @Override
            public void onSoftInputChanged(int height) {
                if (KeyboardUtils.isSoftInputVisible(ReportActivity.this)) {
                    mNsl_report.scrollTo(0, height);
                } else {
                    mNsl_report.scrollTo(0, 0);
                }
            }
        });
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mTv_title_right, this);
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyboardUtils.unregisterSoftInputChangedListener(this);
    }
}
