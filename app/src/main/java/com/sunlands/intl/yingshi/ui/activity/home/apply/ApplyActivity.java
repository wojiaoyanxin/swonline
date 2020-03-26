package com.sunlands.intl.yingshi.ui.activity.home.apply;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;

import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.intl.yingshi.R;

import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.ui.dialog.ApplySuccessDialog;
import com.sunlands.intl.yingshi.util.StringUtils;


/**
 * 申请页面
 */
public class ApplyActivity extends CommonActivity<Object> {

    EditText mEtRealName;
    EditText mEtPhoneNumber;
    EditText mEtCity;
    EditText mEtHighestDegree;
    EditText mEtWorkEngage;
    EditText mEtTypeOfPosition;
    TextView mTvApplyNow;
    TextView mTvChooseUniversity;
    LinearLayout mLlChooseUniversity;

    String[] mTypeArray;
    String mType;
    RadioButton mRbUndergraduate;
    RadioButton mRbMaster;
    RadioButton mRbDoctor;
    RadioGroup mRgType;

    private UniversityAdapter mUniversityAdapter;
    private ListPopupWindow mListPopupWindow;
    private ApplyUniversityBean mApplyUniversityBean;

    public static void show(Context context, ApplyUniversityBean applyUniversityBean) {
        Intent intent = new Intent(context, ApplyActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.Key.KEY_APPLY_UNIVERSITY, applyUniversityBean);
        context.startActivity(intent);
    }

    private String mSelectedUniversityId;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);

        mTypeArray = new String[]{"BBA", "MBA", "MED"};
        mEtRealName = FBIA(R.id.et_real_name);
        mEtPhoneNumber = FBIA(R.id.et_phone_number);
        mEtCity = FBIA(R.id.et_city);
        mEtHighestDegree = FBIA(R.id.et_highest_degree);
        mEtWorkEngage = FBIA(R.id.et_work_engage);
        mEtTypeOfPosition = FBIA(R.id.et_type_of_position);
        mTvApplyNow = FBIA(R.id.tv_apply_now);
        mTvChooseUniversity = FBIA(R.id.tv_choose_university);
        mLlChooseUniversity = FBIA(R.id.ll_choose_university);

        mRbUndergraduate = FBIA(R.id.rb_undergraduate);
        mRbMaster = FBIA(R.id.rb_master);
        mRbDoctor = FBIA(R.id.rb_doctor);
        mRgType = FBIA(R.id.rg_type);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(mTvApplyNow, this);
        mLlChooseUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickChooseUniversity();
            }
        });
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mApplyUniversityBean = getIntent().getParcelableExtra(Constants
                .Key.KEY_APPLY_UNIVERSITY);
        mListPopupWindow = new ListPopupWindow(this);
        mListPopupWindow.setAnchorView(mLlChooseUniversity);
        mUniversityAdapter = new UniversityAdapter(this);
        mListPopupWindow.setAdapter(mUniversityAdapter);
        mListPopupWindow.setOnItemClickListener((parent, view, position, id) -> {
            UniversityListResponse.UniversityBean item = mUniversityAdapter.getItem(position);
            mSelectedUniversityId = item.getId();
            mTvChooseUniversity.setText(item.getName());
            mListPopupWindow.dismiss();
        });
        mType = ApplyParam.DegreeType.BBA.name();
        mRgType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_undergraduate) {
                mType = mTypeArray[0];
            } else if (checkedId == R.id.rb_master) {
                mType = mTypeArray[1];
            } else if (checkedId == R.id.rb_doctor) {
                mType = mTypeArray[2];
            }
        });

        if (mApplyUniversityBean != null) {
            String type = mApplyUniversityBean.getType();
            if (!StringUtils.isEmpty(type)) {
                mType = type;
                try {
                    ApplyParam.DegreeType degreeType = ApplyParam.DegreeType.valueOf(mType);
                    switch (degreeType) {
                        case BBA:
                            mRgType.check(R.id.rb_undergraduate);
                            break;
                        case MBA:
                            mRgType.check(R.id.rb_master);
                            break;
                        case MED:
                            mRgType.check(R.id.rb_doctor);
                            break;
                        default:
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            mSelectedUniversityId = mApplyUniversityBean.getUniversityId();
            mTvChooseUniversity.setText(mApplyUniversityBean.getName());
        }
    }

    @Override
    public void onSuccess(Object bean) {

        super.onSuccess(bean);

        if (bean instanceof UniversityListResponse) {

            UniversityListResponse universityListResponse = (UniversityListResponse) bean;
            mUniversityAdapter.setData(universityListResponse.getUniversityList());

        } else {
            ApplySuccessDialog applySuccessDialog = ApplySuccessDialog.newInstance();
            applySuccessDialog.show(getSupportFragmentManager(), "ApplySuccessDialog");
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (v.getId() == R.id.tv_apply_now) {
            onClickApply();
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply;
    }

    @Override
    public String getTitleText() {
        return getResources().getString(R.string.app_name);
    }

    public void onClickApply() {

        ApplyParam applyParam = new ApplyParam();
        applyParam.setUniversityId(mSelectedUniversityId)
                .setType(mType)
                .setName(mEtRealName.getText().toString())
                .setTel(mEtPhoneNumber.getText().toString())
                .setPlace(mEtCity.getText().toString())
                .setEducational(mEtHighestDegree.getText().toString())
                .setIndustry(mEtWorkEngage.getText().toString())
                .setPositionType(mEtTypeOfPosition.getText().toString());
        getDataNet(true, applyParam);
    }

    public void onClickChooseUniversity() {
        if (mListPopupWindow != null) {
            mListPopupWindow.show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataNet(true, "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mListPopupWindow != null) {
            mListPopupWindow.dismiss();
            mListPopupWindow = null;
        }
    }
}
