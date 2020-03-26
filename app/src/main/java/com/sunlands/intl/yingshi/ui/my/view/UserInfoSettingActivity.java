package com.sunlands.intl.yingshi.ui.my.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.intl.yingshi.BuildConfig;
import com.sunlands.intl.yingshi.R;

import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.ui.my.bean.JsonBean;
import com.sunlands.intl.yingshi.ui.my.bean.NameType;
import com.sunlands.intl.yingshi.ui.my.bean.UploadAvatarResponse;
import com.sunlands.intl.yingshi.ui.my.bean.UserInfo;
import com.sunlands.intl.yingshi.util.CropUtils;
import com.sunlands.intl.yingshi.util.DLog;
import com.sunlands.intl.yingshi.util.FileUtils;
import com.sunlands.intl.yingshi.util.GetJsonDataUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.util.Utils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class UserInfoSettingActivity extends CommonActivity<Object> {

    TextView mTvHeaderRight;
    ImageView mIvAvatar;
    TextView mEtRealName;
    TextView mCity;
    ImageView nan;
    ImageView imgNextArrow;
    ImageView nv;
    TextView work;
    TextView company;
    TextView currentPosition;
    RelativeLayout rlCurrentPosition;
    RelativeLayout rlCity;
    RelativeLayout rlHangye;
    RelativeLayout rlCompany;

    private UserInfo mUserInfo;
    private File file;
    private Uri uri;
    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    private static final int REQUEST_CODE_ALBUM = 2;
    private static final int REQUEST_CODE_CROUP_PHOTO = 3;
    private OptionsPickerView pvOptions;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);

        mTvHeaderRight = FBIA(R.id.tv_header_right);
        currentPosition = FBIA(R.id.currentPosition);
        company = FBIA(R.id.company);
        nan = FBIA(R.id.nan);
        nv = FBIA(R.id.nv);
        work = FBIA(R.id.work);
        mCity = FBIA(R.id.city);
        mEtRealName = FBIA(R.id.et_real_name);
        mIvAvatar = FBIA(R.id.iv_avatar);
        imgNextArrow = FBIA(R.id.imgNextArrow);
        rlCity = FBIA(R.id.ll_city);
        rlCurrentPosition = FBIA(R.id.dangqianzhiwei);
        rlCompany = FBIA(R.id.rl_company);
        rlHangye = FBIA(R.id.gongzuohangye);
    }


    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        initJsonData();
        getDataNet(true, "");
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mTvHeaderRight.setText(getResources().getString(R.string.save));
    }

    @Override
    public String getTitleText() {
        return "个人主页";
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(mTvHeaderRight, this);
        RxBindingHelper.setOnClickListener(nan, this);
        RxBindingHelper.setOnClickListener(nv, this);
        RxBindingHelper.setOnClickListener(mIvAvatar, this);
        RxBindingHelper.setOnClickListener(imgNextArrow, this);
        RxBindingHelper.setOnClickListener(rlCity, this);
        RxBindingHelper.setOnClickListener(rlCompany, this);
        RxBindingHelper.setOnClickListener(rlCurrentPosition, this);
        RxBindingHelper.setOnClickListener(rlHangye, this);
    }

    @Override
    public void onClick(View v) {

        super.onClick(v);

        int id = v.getId();
        if (id == R.id.vg_back) {
            onBackPressed();
        } else if (id == R.id.ll_city) {
            setWheel();
        } else if (id == R.id.tv_header_right) {
            getDataNet(true, mUserInfo.getHeadUrl(),
                    mUserInfo.getIndustry(),
                    mUserInfo.getCompany(),
                    mUserInfo.getPosition(),
                    mUserInfo.getSex(),
                    mUserInfo.getCity());
        } else if (id == R.id.iv_avatar || id == R.id.imgNextArrow) {
            onClickAvatar();
        } else if (id == R.id.gongzuohangye) {
            setWheelIndustry();
        } else if (id == R.id.nan) {
            if (mUserInfo.getEdit() == 0) {
                ToastUtils.showShort("请联系班主任修改");
                return;
            }
            selectNan();
        } else if (id == R.id.nv) {
            if (mUserInfo.getEdit() == 0) {
                ToastUtils.showShort("请联系班主任修改");
                return;
            }
            selectNv();
        } else if (id == R.id.rl_company) {
            if (mUserInfo.getEdit() == 0) {
                ToastUtils.showShort("请联系班主任修改");
                return;
            }
            Intent intent = new Intent(this, CompanyNameActivity.class);
            intent.putExtra("name", company.getText().toString());

            startActivity(intent);
        } else if (id == R.id.dangqianzhiwei) {
            Intent intent;
            if (mUserInfo.getEdit() == 0) {
                ToastUtils.showShort("请联系班主任修改");
                return;
            }
            intent = new Intent(this, PositionNameActivity.class);
            intent.putExtra("name", currentPosition.getText().toString());
            startActivity(intent);
        }


    }

    @Override
    public void onSuccess(Object bean) {

        if (bean instanceof UserInfo) {
            UserInfo userInfo = (UserInfo) bean;
            mUserInfo = userInfo;
            GlideUtils.loadRoundAvatarImage(UserInfoSettingActivity.this, mUserInfo.getHeadUrl(), mIvAvatar);
            mEtRealName.setText(mUserInfo.getName());
            mCity.setText(mUserInfo.getCity());
            if (("男").equals(mUserInfo.getSex())) {
                selectNan();
            } else if (("女").equals(mUserInfo.getSex())) {
                selectNv();
            }
            String position = mUserInfo.getPosition();
            String industry = mUserInfo.getIndustry();
            String mCompany = mUserInfo.getCompany();
            work.setText(industry);
            company.setText(mCompany);
            currentPosition.setText(position);
            List<UserInfo.IndustryListBean> industryList = mUserInfo.getIndustryList();
            initJson(industryList);
        } else if (bean instanceof EmptyBean) {
            onBackPressed();
        } else if (bean instanceof UploadAvatarResponse) {
            UploadAvatarResponse uploadAvatarResponse = (UploadAvatarResponse) bean;
            GlideUtils.loadRoundAvatarImage(UserInfoSettingActivity.this, uploadAvatarResponse.getSrc(), mIvAvatar);
            if (mUserInfo != null) {
                mUserInfo.setHeadUrl(uploadAvatarResponse.getSrc());
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info_setting;
    }

    public void onClickAvatar() {

        initAvatarFile();
        final String[] items = {" 相册", " 拍照", " 取消"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(this);
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        openSystemAlbum();
                        break;
                    case 1:
                        takePhoto();
                        break;
                    default:
                        break;
                }
            }
        });
        listDialog.show();

    }

    private void uploadAvatar() {

        file = FileUtils.getSmallBitmap(this, file.getAbsolutePath());

        getDataNet(true,file);
    }

    private void takePhoto() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.CAMERA
                        , Permission.READ_EXTERNAL_STORAGE
                        , Permission.WRITE_EXTERNAL_STORAGE
                )
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        ToastUtils.showShort("未授予权限");
                    }
                })
                .start();
    }

    private void initAvatarFile() {
        file = new File(FileUtils.getCachePath(this), String.format("avatar%s.jpg", System.currentTimeMillis()));
        if (file.exists()) {
            boolean delete = file.delete();
            DLog.d("delete: " + delete);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            uri = Uri.fromFile(file);
        } else {
            //通过FileProvider创建一个content类型的Uri(android 7.0需要这样的方法跨应用访问)
            uri = FileProvider.getUriForFile(Utils.getApp(),
                    BuildConfig.APPLICATION_ID + ".provider", file);
        }
    }

    private void openSystemAlbum() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DLog.d("requestCode: " + requestCode + data);
        if (resultCode != -1) {
            return;
        }
        if (requestCode == REQUEST_CODE_ALBUM && data != null) {
            Uri newUri;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                newUri = Uri.parse("file:///" + CropUtils.getPath(this, data.getData()));
            } else {
                newUri = data.getData();
            }
            if (newUri != null) {
                startPhotoZoom(newUri);
            } else {
                ToastUtils.showShort("没有得到相册图片");
            }
        } else if (requestCode == REQUEST_CODE_TAKE_PHOTO) {
            startPhotoZoom(uri);
        } else if (requestCode == REQUEST_CODE_CROUP_PHOTO) {
            uploadAvatar();
        }
    }

    /**
     * 裁剪拍照裁剪
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        DLog.d("startPhotoZoom: " + uri);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("crop", "true");// crop=true 有这句才能出来最后的裁剪页面.
        intent.putExtra("aspectX", 1);// 这两项为裁剪框的比例.
        intent.putExtra("aspectY", 1);// x:y=1:1
//        intent.putExtra("outputX", 400);//图片输出大小
//        intent.putExtra("outputY", 400);
        intent.putExtra("output", Uri.fromFile(file));
        intent.putExtra("outputFormat", "JPEG");// 返回格式
        startActivityForResult(intent, REQUEST_CODE_CROUP_PHOTO);
    }

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    //省市区代码集合
    private ArrayList<String> province = new ArrayList<>();
    private ArrayList<ArrayList<String>> city = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> country = new ArrayList<>();

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         * */
        String JsonData = new GetJsonDataUtil()
                .getJson(this, "city.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            province.add(jsonBean.get(i).getRegion_id());//添加省代码
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<String> CityCode = new ArrayList<>();//该省的城市列表（第二级）

            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            ArrayList<ArrayList<String>> Province_Code = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCity().size(); c++) {//遍历该省份的所有城市

                String CityName = jsonBean.get(i).getCity().get(c).getName();

                String region_id = jsonBean.get(i).getCity().get(c).getRegion_id();

                CityList.add(CityName);//添加城市

                CityCode.add(region_id);//添加城市代码

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                ArrayList<String> City_Code = new ArrayList<>();//该城市的所有地区代码

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCity().get(c).getArea() == null
                        || jsonBean.get(i).getCity().get(c).getArea().size() == 0) {
                    City_AreaList.add("");

                } else {

                    for (int d = 0; d < jsonBean.get(i).getCity().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        JsonBean.CityBean.AreaBean areaBean = jsonBean.get(i).getCity().get(c).getArea().get(d);
                        City_AreaList.add(areaBean.getName());//添加该城市所有地区数据
                        City_Code.add(areaBean.getRegion_id());//添加该城市所有地区数据

                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                Province_Code.add(City_Code);//添加该省所有地区代码
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);
            city.add(CityCode);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
            country.add(Province_Code);
        }

    }

    private void initJson(List<UserInfo.IndustryListBean> industryList) {//解析数据

        options1 = industryList;

        for (int i = 0; i < options1.size(); i++) {//遍历省份

            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）

            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < options1.get(i).getData().size(); c++) {//遍历该省份的所有城市

                String CityName = options1.get(i).getData().get(c);

                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
            if (options1.get(i).getData() == null
                    || options1.get(i).getData().size() == 0) {
                CityList.add("");
            }

            /**
             * 添加城市数据
             */
            options22.add(CityList);

        }

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析

        ArrayList<JsonBean> detail = new ArrayList<>();//省的数据

        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    public void selectNan() {
        nan.setImageResource(R.drawable.xuanzhongsex);
        nv.setImageResource(R.drawable.weixuanzhongsex);
        mUserInfo.setSex("男");
    }

    public void selectNv() {
        nan.setImageResource(R.drawable.weixuanzhongsex);
        nv.setImageResource(R.drawable.xuanzhongsex);
        mUserInfo.setSex("女");
    }

    private void setWheel() {
        
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                String tx = options1Items.get(options1).getPickerViewText() + "-" +
                        options2Items.get(options1).get(option2);
                if (options1Items.get(options1).getPickerViewText().equals(options2Items.get(options1).get(option2))) {
                    tx = options1Items.get(options1).getPickerViewText();
                }
                mCity.setText(tx);
                mUserInfo.setCity(tx);
            }
        })
                .setTitleText("选择城市")
                .setTitleSize(16)
                .setContentTextSize(18)
                .build();

        pvOptions.setPicker(options1Items, options2Items, null);
        pvOptions.show();

    }

    List<UserInfo.IndustryListBean> options1 = new ArrayList<>();
    List<ArrayList<String>> options22 = new ArrayList<>();

    int position = 0;

    private void setWheelIndustry() {

        String industry = mUserInfo.getIndustry();

        if (TextUtils.isEmpty(industry)) {
            position = 0;
        } else {
            //截取字符串
            if (industry.contains("-")) {
                industry = industry.substring(0, industry.indexOf("-"));
            }
            for (int i = 0; i < options1.size(); i++) {
                UserInfo.IndustryListBean industryListBean = options1.get(i);
                if (industryListBean.getNameX().equals(industry)) {
                    position = i;
                    break;
                }
            }
        }

        if (mUserInfo.getEdit() == 1) {
            position = 0;
        }

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options, int option2, int options3, View v) {

                if (mUserInfo.getEdit() == 0) {
                    options = position;
                }
                String tx = options1.get(options).getPickerViewText() + "-" +
                        options22.get(options).get(option2);
                work.setText(tx);
                mUserInfo.setIndustry(tx);
            }
        })
                .setTitleText("")
                .setTitleSize(16)
                .setContentTextSize(18)
                .setSelectOptions(position)
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        if (mUserInfo.getEdit() == 0) {
                            pvOptions.setSelectOptions(position, options2);
                        }
                    }
                })
                .build();

        pvOptions.setPicker(options1, options22, null);
        pvOptions.show();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NameType type) {
        if (type.getType().equals("1")) {
            company.setText(type.getName());
            mUserInfo.setCompany(type.getName());
        } else if (type.getType().equals("2")) {
            currentPosition.setText(type.getName());
            mUserInfo.setPosition(type.getName());
        }
    }

    public static void show(Context context) {
        Intent intent = new Intent(context, UserInfoSettingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
      //  mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.cl_ffffff).autoDarkModeEnable(false).navigationBarEnable(false).init();
       hideBottomUIMenu();
    }
}
