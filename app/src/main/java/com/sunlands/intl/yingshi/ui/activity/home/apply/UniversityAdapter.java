package com.sunlands.intl.yingshi.ui.activity.home.apply;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sunlands.intl.yingshi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名: UniversityAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/18
 */
public class UniversityAdapter extends BaseAdapter {
    Context mContext;
    private List<UniversityListResponse.UniversityBean> mUniversityBeans = new ArrayList<>();

    public UniversityAdapter(@NonNull Context context) {
        mContext = context;
    }

    public void setData(List<UniversityListResponse.UniversityBean> universityBeans) {
        mUniversityBeans.clear();
        mUniversityBeans.addAll(universityBeans);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mUniversityBeans.size();
    }

    @Override
    public UniversityListResponse.UniversityBean getItem(int position) {
        return mUniversityBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_university, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.tv_university_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTextView.setText(mUniversityBeans.get(position).getName());
        return convertView;
    }

    public class ViewHolder {
        TextView mTextView;
    }


}
