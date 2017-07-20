package com.zone.hospital;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.zone.hospital.SQL.dao.evalutedao;
import com.zone.hospital.base.BaseActivity2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jack on 2017/6/14.
 */

public class EvaluateActivity extends BaseActivity2 {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ls_evaluate)
    ListView lsEvaluate;
    @InjectView(R.id.sp_time)
    Spinner spTime;
    @InjectView(R.id.sp_satifaction)
    Spinner spSatifaction;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.tv_content_time)
    TextView tvContentTime;

    private List<String> findall;
    private static final String[] time = {"一个礼拜", "一个月", "半年"};
    private static final String[] Satifaction = {"非常满意", "满意", "不满意"};
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter1;
    private int TIME_DESCRIBE = 1;
    private int SATIFACTION_DESCRIBE = 2;
    private List<String> findtime;

    @Override
    public int setLayoutId() {
        return R.layout.evaluate_show;
    }

    @Override
    public void init() {
        tvTitle.setText("评价内容");
        evalutedao dao = evalutedao.getInstance(this);
        findall = dao.findall();
        findtime = dao.findtime();
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, time);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Satifaction);
        //设置下拉列表的风格
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spTime.setAdapter(adapter);
        //添加事件Spinner事件监听
        spTime.setOnItemSelectedListener(new SpinnerSelectedListener(TIME_DESCRIBE));
        //设置默认值
        spSatifaction.setVisibility(View.VISIBLE);
        //将adapter 添加到spinner中
        spSatifaction.setAdapter(adapter1);
        //添加事件Spinner事件监听
        spSatifaction.setOnItemSelectedListener(new SpinnerSelectedListener(SATIFACTION_DESCRIBE));
        //设置默认值
        spSatifaction.setVisibility(View.VISIBLE);
        lsEvaluate.setAdapter(new myadater());
    }

    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        private int witch;

        public SpinnerSelectedListener(int witch) {
            this.witch = witch;
        }

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {

            switch (witch) {
                case 1:
                    switch (arg2) {
                        case 0:

                            setfindtimecontent(7);
//                            int a = Integer.parseInt(findtime.get(1).substring(findtime.get(1).indexOf("-") + 2, findtime.get(1).indexOf("-") + 3)) - Integer.parseInt(findtime.get(1).substring(findtime.get(1).indexOf("-") + 2, findtime.get(1).indexOf("-") + 3));
//
//                            Log.e("time", " " + a);
                            break;
                        case 1:
                            setfindtimecontent(30);
                            break;
                        case 2:
                            setfindtimecontent(180);
                            break;
                    }

                    break;
                case 2:

                    switch (arg2) {
                        case 0:
                            setfindcontent("非常满意");
                            break;

                        case 1:
                            setfindcontent("比较满意");
                            break;
                        case 2:
                            setfindcontent("不满意");
                            break;
                    }

                    break;


            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    @Override
    public boolean isUseToolbar() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    public class myadater extends BaseAdapter {

        @Override
        public int getCount() {
            return findall.size();
        }

        @Override
        public String getItem(int position) {
            return findall.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewholder holder;
            if (convertView == null) {
                holder = new viewholder();
                convertView = View.inflate(getApplicationContext(), R.layout.item_evaluate, null);
                holder.tv_concent = (TextView) convertView.findViewById(R.id.tv_evaluate_content);
                holder.tv_current_time = (TextView) convertView.findViewById(R.id.tv_current_time);
                convertView.setTag(holder);
            } else {
                holder = (viewholder) convertView.getTag();
            }
            holder.tv_concent.setText(getItem(position));
            holder.tv_current_time.setText(findtime.get(position));
            return convertView;
        }
    }

    class viewholder {
        TextView tv_concent;
        TextView tv_current_time;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public String getcurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(new Date());
        return time;
    }

    /**
     * 查询结果显示在tv
     *
     * @param satifaction
     */
    public void setfindcontent(final String satifaction) {
        new Thread() {
            @Override
            public void run() {
                int all = 0;
                for (int i = 0; i < findall.size(); i++) {
                    if (findall.get(i).contains(satifaction)) {
                        all++;
                    }
                }
                final int finalAll = all;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvContent.setText(satifaction + "一共有" + finalAll + "个");
                    }
                });
            }
        }.start();
    }

    public void setfindtimecontent(final int time) {
        new Thread() {
            @Override
            public void run() {
                int all = 0;
                for (int i = 0; i < findtime.size(); i++) {
                    try {
                        if(daysBetween(findtime.get(i),getcurrentTime())<=time){
                               all++;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                final int finalAll = all;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch (time){
                            case 7:
                                tvContentTime.setText("一个礼拜内共有评价"+ finalAll+"条");
                                break;
                            case 30:
                                tvContentTime.setText("一个月内共有评价"+ finalAll+"条");
                                break;
                            case 180:
                                tvContentTime.setText("半年内共有评价"+ finalAll+"条");
                                break;
                        }

                    }
                });
            }
        }.start();
    }

    /**
     * 46. *字符串的日期格式的计算
     * 47.
     */
    public static int daysBetween(String smdate, String bdate) throws
            ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

}

