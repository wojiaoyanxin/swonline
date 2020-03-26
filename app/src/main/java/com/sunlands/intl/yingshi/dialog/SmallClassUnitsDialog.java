package com.sunlands.intl.yingshi.dialog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sunlands.comm_core.helper.BaseDialogHelper;
import com.sunlands.comm_core.helper.EventBusHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.event.EventMessage;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassUnitsBean;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.util.Utils;

import java.util.List;

public class SmallClassUnitsDialog extends BaseDialogHelper {

    static List<SmallClassUnitsBean.ListBean> listBeans;
    static String mPrice;

    public static SmallClassUnitsDialog getInstance(List<SmallClassUnitsBean.ListBean> listBean, String price) {
        listBeans = listBean;
        mPrice = price;
        SmallClassUnitsDialog dialogFragment = new SmallClassUnitsDialog();
        dialogFragment.setCanceledBack(true);
        dialogFragment.setCanceledOnTouchOutside(false);
        dialogFragment.setGravity(Gravity.BOTTOM);
        return dialogFragment;
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        window.setAttributes(windowParams);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_small_class_units_layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if (getDialog().getWindow() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        if (Utils.isEmpty(listBeans)) {
            view.findViewById(R.id.no_handout_layout).setVisibility(View.VISIBLE);
        }
        TextView tv_class_counts = view.findViewById(R.id.tv_class_counts);
        TextView tv_jiesuo_all_class = view.findViewById(R.id.tv_jiesuo_all_class);
        tv_jiesuo_all_class.setText(mPrice + "元立即解锁全部课程");
        tv_jiesuo_all_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBusHelper.post(new EventMessage(EventMessage.EVENT_SMALL_PAY));
            }
        });
        tv_class_counts.setText("课程列表（" + listBeans.size() + "）");
        RecyclerView recycle_view = view.findViewById(R.id.recycle_view);
        recycle_view.setLayoutManager(new LinearLayoutManager(Utils.getApp()));
        recycle_view.setAdapter(new CommonAdapter<SmallClassUnitsBean.ListBean>(Utils.getApp(), listBeans, R.layout.item_small_class_units) {
            @Override
            public void convert(ViewHolder holder, SmallClassUnitsBean.ListBean o) {
                GlideUtils.loadRoundImage(Utils.getApp(), o.getThumb(), holder.getView(R.id.iv_teacher_head));
                ((TextView) holder.getView(R.id.tv_class_name)).setText(o.getTeach_unit_name());
                ((TextView) holder.getView(R.id.tv_teacher_name)).setText(o.getTeacher());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBusHelper.post(new EventMessage(EventMessage.EVENT_SMALL_PAY));
                    }
                });
            }
        });
    }
}
