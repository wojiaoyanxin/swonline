package com.sunlands.intl.yingshi.ui.activity.home.studystat.view;

import android.graphics.Color;

import com.blankj.utilcode.util.ObjectUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.StudyStatBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.studystat
 * 创 建 人: xueh
 * 创建日期: 2019/3/14 13:18
 * 备注：
 */
public class LineChartUtils {
    public static void setLineChartData(LineChart mLinchart, StudyStatBean data) {
        List<Entry> entries = new ArrayList<>();
        boolean isReset = false;
        if (!ObjectUtils.isEmpty(data.getTrendLineData())) {
            entries.clear();
            for (int i = 0; i < data.getTrendLineData().getY().size(); i++) {
                if (data.getTrendLineData().getY().get(i) > 250) {
                    isReset = true;
                }
                entries.add(new Entry(i, data.getTrendLineData().getY().get(i)));
            }
            setLineChart(mLinchart, entries, data.getTrendLineData().getX(), isReset);
        } else {
            mLinchart.setNoDataTextColor(CommonUtils.getColor(R.color.cl_252A3A));
            mLinchart.setNoDataText("暂无数据");
        }
    }
    private static void setLineChart(LineChart mLinchart, List<Entry> entries, List<String> list, boolean isreset) {
        mLinchart.setTouchEnabled(false);
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        //线颜色
        lineDataSet.setColor(CommonUtils.getColor(R.color.cl_252A3A));
        lineDataSet.setDrawHighlightIndicators(false);
        //线宽度
        lineDataSet.setLineWidth(1.0f);
        //圆点
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setCircleColor(CommonUtils.getColor(R.color.cl_252A3A));
        lineDataSet.setHighlightEnabled(false);
        //线条平滑
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        //设置样式
        YAxis rightAxis = mLinchart.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        YAxis leftAxis = mLinchart.getAxisLeft();
        //设置图表左边的y轴禁用
        leftAxis.setEnabled(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(250f);
        leftAxis.setSpaceMax(50);
        leftAxis.setLabelCount(5, false);
        if (isreset) {
            leftAxis.resetAxisMaximum();
        }
        //设置x轴
        XAxis xAxis = mLinchart.getXAxis();
        xAxis.setLabelCount(list.size());
        xAxis.setTextColor(Color.parseColor("#00000000"));
        xAxis.setTextSize(10f);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisLineColor(Color.parseColor("#00000000"));
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setGranularity(1f);//禁止放大后x轴标签重绘
        //设置x轴文字
        xAxis.setTextColor(Color.parseColor("#8c252A3A"));
        //透明化图例
        Legend legend = mLinchart.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        legend.setTextColor(Color.WHITE);

        //隐藏x轴描述
        Description description = new Description();
        description.setEnabled(false);
        mLinchart.setDescription(description);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));
        //chart设置数据
        LineData lineData = new LineData(lineDataSet);
        lineData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int val = (int) value;
                if (val > 999) {
                    return "   " + val + "分";
                } else {
                    return val + "分";
                }
            }
        });
        lineData.setValueTextColor(CommonUtils.getColor(R.color.cl_252A3A));
        lineData.setValueTextSize(12f);
        //是否绘制线条上的文字
        lineData.setDrawValues(true);
        mLinchart.setData(lineData);
        mLinchart.invalidate();
    }
}
