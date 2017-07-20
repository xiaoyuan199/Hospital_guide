package com.zone.hospital;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zone.hospital.base.BaseActivity2;
import com.zone.hospital.global.URLaddress;
import com.zone.hospital.model.bean.SicknessModel;
import com.zone.hospital.utils.okhttppostjson;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class SmartDetailActivity extends BaseActivity2 {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.tv_general)
    TextView mTvGeneral;
    @InjectView(R.id.tv_apartment)
    TextView mTvApartment;
    @InjectView(R.id.tv_description)
    TextView mTvDescription;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.ib_doctor_detail)
    ImageButton ibDoctorDetail;
    @InjectView(R.id.ib_doctor_detail1)
    ImageButton ibDoctorDetail1;
    private okhttppostjson post;
    private URLaddress j2str;
    private SicknessModel model;
    private int finalI;
    private  int contentid;
    @Override
    public int setLayoutId() {
        return R.layout.activity_smart_detail;
    }

    @Override
    public void init() {
        post = new okhttppostjson();
        j2str = new URLaddress();
        mToolbar.setTitle(getIntent().getStringExtra("title"));
        String sickness = getIntent().getStringExtra("sickness");
        model = new SicknessModel();
        for (int i = 0; i < model.getList().size(); i++) {
            if (sickness.contains(model.getList().get(i).getSickness())) {
                mTvGeneral.setText(model.getList().get(i).getGeneral());
                mTvApartment.setText(model.getList().get(i).getApartment() + "                                                    点击前往");
                finalI = i;
                mTvApartment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        contentid=0;
                        showAlertDialog(model.getList().get(finalI).getApartment(), "你确定要去" + model.getList().get(finalI).getApartment() + "吗？");

                    }
                });
                mTvDescription.setText(model.getList().get(i).getDescription());
                showToast(model.getList().get(i).getGeneral());
                showToast(model.getList().get(i).getApartment());
                showToast(model.getList().get(i).getDescription());
                break;
            }
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

        okhttppostjson post = new okhttppostjson();
        URLaddress j2str = new URLaddress();
        post.poststring(j2str.ttsword("病症详情如下"));
    }

    @OnClick(R.id.layout_home)
    public void onClick() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (contentid){
                    case 0:
                        post.postaddress(j2str.address(model.getList().get(finalI).getApartment()));

                        break;
                    case 1:
                        post.postaddress(j2str.address("潘晓苑"));
                        break;
                    case 2:
                        post.postaddress(j2str.address("丘赞梅"));
                        break;
                }

                post.poststring(j2str.ttsword("请跟我来"));
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @OnClick({R.id.ib_doctor_detail, R.id.ib_doctor_detail1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_doctor_detail:
                showAlertDialog("潘晓苑","您要挂潘晓苑的号吗？");
                contentid=1;
                break;
            case R.id.ib_doctor_detail1:
                showAlertDialog("丘赞梅","您要挂丘赞梅的号吗？");
                contentid=2;
                break;
        }
    }
}
