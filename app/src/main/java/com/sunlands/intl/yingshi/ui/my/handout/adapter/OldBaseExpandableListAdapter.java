package com.sunlands.intl.yingshi.ui.my.handout.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.PageBean;
import com.sunlands.intl.yingshi.greendao.MyAllFileDbBeanDao;
import com.sunlands.intl.yingshi.bean.multi.HandoutItem;
import com.sunlands.intl.yingshi.bean.multi.SubjectItem;
import com.sunlands.intl.yingshi.greendao.db.MyAllFileDbBean;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.util.FileUtils;
import com.sunlands.intl.yingshi.util.SPHelper;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.Map;


public class OldBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private final List<PageBean> datalist;
    public List<Map<String, Object>> parentMapList;
    public List<List<Map<String, Object>>> childMapList_list;
    Context context;

    OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener;
    OnCheckHasGoodsListener onCheckHasGoodsListener;
    OnClickLookListener onClickLookListener;

    public void setOnClickLookListener(OnClickLookListener onClickLookListener) {
        this.onClickLookListener = onClickLookListener;
    }

    public void setOnCheckHasGoodsListener(OnCheckHasGoodsListener onCheckHasGoodsListener) {
        this.onCheckHasGoodsListener = onCheckHasGoodsListener;
    }


    public void setOnAllCheckedBoxNeedChangeListener(OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener) {
        this.onAllCheckedBoxNeedChangeListener = onAllCheckedBoxNeedChangeListener;
    }

    ExpandableListView expandableListView;

    public OldBaseExpandableListAdapter(Context context, List<Map<String, Object>> parentMapList, List<List<Map<String, Object>>> childMapList_list, ExpandableListView expandableListView) {
        this.parentMapList = parentMapList;
        this.childMapList_list = childMapList_list;
        this.context = context;
        this.expandableListView = expandableListView;
        datalist = SPHelper.getDataList();
    }


    //获取当前父item的数据数量
    @Override
    public int getGroupCount() {
        return parentMapList.size();
    }

    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return childMapList_list.get(groupPosition).size();
    }

    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return parentMapList.get(groupPosition);
    }

    //得到子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childMapList_list.get(groupPosition).get(childPosition);
    }

    //得到父item的ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //得到子item的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        //return false;
        return true;
    }

    //设置父item组件
    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_handout_old_parent, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tv_title_parent = (TextView) convertView
                    .findViewById(R.id.tv_college_name);
            groupViewHolder.id_tv_counts = (TextView) convertView
                    .findViewById(R.id.tv_handout_count);
            groupViewHolder.id_cb_select_parent = (CheckBox) convertView
                    .findViewById(R.id.imgStatus_subject);
            groupViewHolder.iv_x = (ImageView) convertView
                    .findViewById(R.id.iv_collapse_expand);

            convertView.setTag(groupViewHolder);

        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        setCheckBoxShow(groupViewHolder.id_cb_select_parent);
        if (parentMapList.size() > 0) {
            SubjectItem subjectItem = (SubjectItem) parentMapList.get(groupPosition).get("parentName");
            groupViewHolder.tv_title_parent.setText(subjectItem.title);
            groupViewHolder.id_tv_counts.setText(subjectItem.subTitle);
            groupViewHolder.id_cb_select_parent.setChecked(subjectItem.isSelect());
            final boolean nowBeanChecked = subjectItem.isSelect();
            groupViewHolder.id_cb_select_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setupOneParentAllChildChecked(!nowBeanChecked, groupPosition);
                    //控制总checkedbox 接口
                    onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllParentIsChecked());
                }
            });
        }

        //覆盖原有收起展开事件
        GroupViewHolder finalGroupViewHolder = groupViewHolder;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isExpanded) {
                    finalGroupViewHolder.iv_x.setImageResource(R.drawable.ic_arrow_expand);
                    expandableListView.collapseGroup(groupPosition);
                } else {
                    finalGroupViewHolder.iv_x.setImageResource(R.drawable.ic_arrow_collapse);
                    expandableListView.expandGroup(groupPosition);
                }
            }
        });

        return convertView;
    }

    //设置子item的组件
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_handout_old_child, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tv_items_title = (TextView) convertView
                    .findViewById(R.id.tv_handout_name);
            childViewHolder.id_cb_select_child = (CheckBox) convertView
                    .findViewById(R.id.imgStatus_item);
            childViewHolder.tv_items_time = (TextView) convertView
                    .findViewById(R.id.tv_handout_download_time);
            childViewHolder.id_tv_look = (TextView) convertView
                    .findViewById(R.id.tv_click_to_see);
            childViewHolder.watchProgress = (TextView) convertView
                    .findViewById(R.id.watch_progress);

            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        setCheckBoxShow(childViewHolder.id_cb_select_child);

        if (childMapList_list.size() > 0) {

            final HandoutItem handoutItem = (HandoutItem) childMapList_list.get(groupPosition).get(childPosition).get("childName");

            childViewHolder.tv_items_time.setText(handoutItem.getTimestamp());
            childViewHolder.tv_items_title.setText(handoutItem.getFileName());

            for (int i = 0; i < datalist.size(); i++) {
                PageBean pageBean = datalist.get(i);
                if (handoutItem.getPath().equals(pageBean.getFilePath())) {
                    if (TextUtils.isEmpty(pageBean.getFilePath())) {
                        childViewHolder.watchProgress.setText("观看至0%");
                    } else {
                        childViewHolder.watchProgress.setText("观看至" + pageBean.getProgress());
                    }
                }
            }
            childViewHolder.watchProgress.setText(handoutItem.getProgress());
            childViewHolder.id_cb_select_child.setChecked(handoutItem.isSelect());
            childViewHolder.id_cb_select_child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean nowBeanChecked = handoutItem.isSelect();
                    //更新数据
                    handoutItem.setSelect(!nowBeanChecked);
                    boolean isOneParentAllChildIsChecked = dealOneParentAllChildIsChecked(groupPosition);

                    SubjectItem subjectItem = (SubjectItem) parentMapList.get(groupPosition).get("parentName");
                    subjectItem.setSelect(isOneParentAllChildIsChecked);

                    notifyDataSetChanged();
                    //控制总checkedbox 接口
                    onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllParentIsChecked());
                }
            });
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean nowBeanChecked = handoutItem.isSelect();
                    //更新数据
                    handoutItem.setSelect(!nowBeanChecked);
                    boolean isOneParentAllChildIsChecked = dealOneParentAllChildIsChecked(groupPosition);

                    SubjectItem subjectItem = (SubjectItem) parentMapList.get(groupPosition).get("parentName");
                    subjectItem.setSelect(isOneParentAllChildIsChecked);

                    notifyDataSetChanged();
                    //控制总checkedbox 接口
                    onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllParentIsChecked());
                }
            });
            childViewHolder.id_tv_look.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickLookListener.onClickLook(handoutItem.getPath(),handoutItem.getFileName());
                }
            });
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // return false;
        return true;
    }

    //供全选按钮调用
    public void setupAllChecked(boolean isChecked) {

        for (int i = 0; i < parentMapList.size(); i++) {
            SubjectItem storeBean = (SubjectItem) parentMapList.get(i).get("parentName");
            storeBean.setSelect(isChecked);

            List<Map<String, Object>> childMapList = childMapList_list.get(i);
            for (int j = 0; j < childMapList.size(); j++) {
                HandoutItem goodsBean = (HandoutItem) childMapList.get(j).get("childName");
                goodsBean.setSelect(isChecked);
            }
        }
        notifyDataSetChanged();

    }

    private void setupOneParentAllChildChecked(boolean isChecked, int groupPosition) {

        SubjectItem storeBean = (SubjectItem) parentMapList.get(groupPosition).get("parentName");
        storeBean.setSelect(isChecked);

        List<Map<String, Object>> childMapList = childMapList_list.get(groupPosition);
        for (int j = 0; j < childMapList.size(); j++) {
            HandoutItem goodsBean = (HandoutItem) childMapList.get(j).get("childName");
            goodsBean.setSelect(isChecked);
        }
        notifyDataSetChanged();
    }

    //checkBox的显示和隐藏

    private void setCheckBoxShow(CheckBox checkBox) {

        boolean selectAllShow = SPHelper.getSelectAllShow();
        if (selectAllShow == true) {
            checkBox.setVisibility(View.VISIBLE);
        } else {
            checkBox.setVisibility(View.GONE);
        }
    }

    public boolean dealOneParentAllChildIsChecked(int groupPosition) {
        // StoreBean storeBean= (StoreBean) parentMapList.get(groupPosition).get("parentName");
        List<Map<String, Object>> childMapList = childMapList_list.get(groupPosition);
        for (int j = 0; j < childMapList.size(); j++) {
            HandoutItem goodsBean = (HandoutItem) childMapList.get(j).get("childName");
            if (!goodsBean.isSelect()) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }

    public boolean dealAllParentIsChecked() {

        for (int i = 0; i < parentMapList.size(); i++) {
            SubjectItem storeBean = (SubjectItem) parentMapList.get(i).get("parentName");
            if (!storeBean.isSelect()) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }

    public void removeGoods() {

        for (int i = parentMapList.size() - 1; i >= 0; i--) {//倒过来遍历  remove

            SubjectItem storeBean = (SubjectItem) parentMapList.get(i).get("parentName");

            if (storeBean.isSelect()) {

                parentMapList.remove(i);
                //  childMapList_list.remove(i);

                List<Map<String, Object>> childMapList = childMapList_list.get(i);
                for (int j = childMapList.size() - 1; j >= 0; j--) {//倒过来遍历  remove
                    HandoutItem goodsBean = (HandoutItem) childMapList.get(j).get("childName");
                    if (goodsBean.isSelect()) {
                        childMapList.remove(j);
                        deleteFile(goodsBean.getPath(), goodsBean.getFileName(), goodsBean.getCourseId(), goodsBean.getFileUrl(), goodsBean.getSid());
                    }
                }

            } else {

                List<Map<String, Object>> childMapList = childMapList_list.get(i);
                for (int j = childMapList.size() - 1; j >= 0; j--) {//倒过来遍历  remove
                    HandoutItem goodsBean = (HandoutItem) childMapList.get(j).get("childName");
                    if (goodsBean.isSelect()) {
                        childMapList.remove(j);
                        deleteFile(goodsBean.getPath(), goodsBean.getFileName(), goodsBean.getCourseId(), goodsBean.getFileUrl(), goodsBean.getSid());
                    }
                }
            }

        }
        if (parentMapList != null && parentMapList.size() > 0) {
            onCheckHasGoodsListener.onCheckHasGoods(true);//
        } else {
            onCheckHasGoodsListener.onCheckHasGoods(false);//
        }
        notifyDataSetChanged();
    }


    public boolean isCheck() {

        for (int i = parentMapList.size() - 1; i >= 0; i--) {//倒过来遍历  remove
            SubjectItem storeBean = (SubjectItem) parentMapList.get(i).get("parentName");
            if (storeBean.isSelect()) {
                return true;
            } else {
                List<Map<String, Object>> childMapList = childMapList_list.get(i);
                for (int j = childMapList.size() - 1; j >= 0; j--) {//倒过来遍历  remove
                    HandoutItem goodsBean = (HandoutItem) childMapList.get(j).get("childName");
                    if (goodsBean.isSelect()) {
                        return true;
                    }
                }
            }

        }


        return false;

    }


    private void resetViewAfterDelete() {
        for (int i = 0; i < parentMapList.size(); i++) {
            SubjectItem storeBean = (SubjectItem) parentMapList.get(i).get("parentName");
            storeBean.setSelect(false);
            List<Map<String, Object>> childMapList = childMapList_list.get(i);

            for (int j = 0; j < childMapList.size(); j++) {
                HandoutItem goodsBean = (HandoutItem) childMapList.get(j).get("childName");
                goodsBean.setSelect(false);
            }
        }
    }

    public interface OnAllCheckedBoxNeedChangeListener {
        void onCheckedBoxNeedChange(boolean allParentIsChecked);
    }

    public interface OnCheckHasGoodsListener {
        void onCheckHasGoods(boolean isHasGoods);
    }
    public interface OnClickLookListener {
        void onClickLook(String s, String s1);
    }


    class GroupViewHolder {
        TextView tv_title_parent;
        TextView id_tv_counts;
        CheckBox id_cb_select_parent;
        ImageView iv_x;
    }

    class ChildViewHolder {

        TextView tv_items_title;
        TextView watchProgress;
        CheckBox id_cb_select_child;
        TextView tv_items_time;
        TextView id_tv_look;

    }

    private void deleteFileData(String courseId, String fileName, int sid) {

        QueryBuilder<MyAllFileDbBean> myAllFileDbBeanQueryBuilder = DbHelper.mDaoSession.queryBuilder(MyAllFileDbBean.class);

        if (!TextUtils.isEmpty(fileName)) {   //删除一个文件

            List<MyAllFileDbBean> list = myAllFileDbBeanQueryBuilder
                    .where(MyAllFileDbBeanDao.Properties.Sid.eq(sid))
                    .where(MyAllFileDbBeanDao.Properties.CourseId.eq(courseId)).list();

            for (int i = 0; i < list.size(); i++) {
                DbHelper.getInstance().getAllFileDbBeanDao().delete(list.get(i));
            }

        } else {   //删除当前课程所有文件

            List<MyAllFileDbBean> list = myAllFileDbBeanQueryBuilder
                    .where(MyAllFileDbBeanDao.Properties.CourseId.eq(courseId)).list();

            for (int i = 0; i < list.size(); i++) {
                DbHelper.getInstance().getAllFileDbBeanDao().delete(list.get(i));
            }

        }

    }

    private void deleteFile(String path, String fileName, String couesrId, String fileUrl, int sid) {

        FileUtils.deleteFile(path);
        deleteFileData(couesrId, fileName, sid);
        DownloadEntity downloadEntity = Aria.download(context).getDownloadEntity(fileUrl);
        if (downloadEntity != null) {
            Aria.download(context).load(downloadEntity).cancel(true);
        }
    }
}
