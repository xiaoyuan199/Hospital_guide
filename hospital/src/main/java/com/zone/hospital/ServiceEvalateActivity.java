package com.zone.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zone.hospital.SQL.dao.evalutedao;
import com.zone.hospital.base.BaseActivity2;
import com.zone.hospital.global.URLaddress;
import com.zone.hospital.utils.okhttppostjson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ServiceEvalateActivity extends BaseActivity2 {
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.client_feedback)
    EditText et_content;
    @InjectView(R.id.rb_ever_satisfaction)
    RadioButton rbEverSatisfaction;
    @InjectView(R.id.rb_satisfaction)
    RadioButton rbSatisfaction;
    @InjectView(R.id.rb_unsatisfaction)
    RadioButton rbUnsatisfaction;
    @InjectView(R.id.cv_submit)
    CardView cvSubmit;
    @InjectView(R.id.parent)
    LinearLayout parent;
    private okhttppostjson post;
    private URLaddress j2str;
   public ArrayList<String> content;
    private String evaluate="非常满意";
    private evalutedao dao;

    @Override
    public int setLayoutId() {
        return R.layout.activity_service_evaluate;
    }

    @Override
    public void init() {

        mToolbar.setTitle("");
        mTvTitle.setText("服务评价");
        // mSpeechSynthesizer.speak("这是服务评价");
        post = new okhttppostjson();
        j2str = new URLaddress();
        post.poststring(j2str.ttsword("这是服务评价"));
        //content = new ArrayList<>();
        dao = evalutedao.getInstance(this);
    }

    @Override
    public boolean isUseToolbar() {
        return true;
    }

    @OnClick(R.id.cv_submit)
    public void onClick() {
       // if (!et_content.getText().toString().trim().equals("")) {
//            mSpeechSynthesizer.speak("谢谢您的评价");
            post.poststring(j2str.ttsword("谢谢您的评价"));
            //content.add(evaluate+ et_content.getText().toString());
            dao.insert(evaluate+" "+ et_content.getText().toString(),getcurrentTime());
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

      //  }

    }
    public String getcurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(new Date());
        return time;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.rb_ever_satisfaction, R.id.rb_satisfaction, R.id.rb_unsatisfaction})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_ever_satisfaction:
                evaluate="非常满意";
                break;
            case R.id.rb_satisfaction:
                evaluate="比较满意";
                break;
            case R.id.rb_unsatisfaction:
                evaluate="不满意";
                break;
        }
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_navigation);
//    }
}
