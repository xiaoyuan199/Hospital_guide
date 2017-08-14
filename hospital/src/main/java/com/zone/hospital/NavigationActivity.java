package com.zone.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.zone.hospital.base.BaseActivity2;
import com.zone.hospital.global.URLaddress;
import com.zone.hospital.utils.okhttppostjson;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class NavigationActivity extends BaseActivity2 {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_six)
    TextView tvSix;
    @InjectView(R.id.tv_five)
    TextView tvFive;
    @InjectView(R.id.tv_four)
    TextView tvFour;
    @InjectView(R.id.tv_there)
    TextView tvThere;
    @InjectView(R.id.tv_second)
    TextView tvSecond;
    @InjectView(R.id.tv_first)
    TextView tvFirst;

    @Override
    public int setLayoutId() {
        return R.layout.activity_navigation;
    }

    @Override
    public void init() {
        mToolbar.setTitle("");
        mTvTitle.setText("问诊导航");
        // mSpeechSynthesizer.speak("这是医院介绍");
        okhttppostjson post = new okhttppostjson();
        URLaddress j2str = new URLaddress();
        post.poststring(j2str.ttsword("这是问诊导航"));
    }

    @Override
    public boolean isUseToolbar() {
        return true;
    }

//
//    @OnClick(R.id.tv_first)
//    public void onClick() {
//        startActivity(new Intent(NavigationActivity.this, NavigationDetailActivity.class));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.tv_six, R.id.tv_five, R.id.tv_four, R.id.tv_there, R.id.tv_second, R.id.tv_first})
    public void onViewClicked(View view) {
        Intent intent=new Intent(NavigationActivity.this, NavigationDetailActivity.class);
        switch (view.getId()) {
            case R.id.tv_six:
               // intent.putExtra("floor",6);
             //   startActivity(intent);
                break;
            case R.id.tv_five:
                intent.putExtra("floor",5);
                startActivity(intent);
                break;
            case R.id.tv_four:
                intent.putExtra("floor",4);
                startActivity(intent);
                break;
            case R.id.tv_there:
                intent.putExtra("floor",3);
                startActivity(intent);
                break;
            case R.id.tv_second:
                intent.putExtra("floor",2);
                startActivity(intent);
                break;
            case R.id.tv_first:
                intent.putExtra("floor",1);
                startActivity(intent);
                break;
        }
    }
}
