package com.zone.hospital;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zone.hospital.base.BaseFragmtnt;
import com.zone.hospital.utils.SPUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by john on 2016/7/14.
 */
public class MoreFragment extends BaseFragmtnt {


    @InjectView(R.id.btn_change_pwd)
    Button btnChangePwd;
    @InjectView(R.id.btn_show_evaluate)
    Button btnShowEvaluate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View meView = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.inject(this, meView);
        return meView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.btn_change_pwd, R.id.btn_show_evaluate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_pwd:
                showsetDialog();
                break;
            case R.id.btn_show_evaluate:
                startActivity(new Intent(getContext(),EvaluateActivity.class));
                break;
        }
    }
    private void showsetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog Dialog = builder.create();
        final View  view= View.inflate(getContext(), R.layout.dialog_set_pwd, null);
        Dialog.setView(view);
        Dialog.show();
        Button but_commit=(Button)view.findViewById(R.id.but_commit);
        Button but_cancel=(Button)view.findViewById(R.id.but_cancel);
        but_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.dismiss();
            }
        });
        but_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText set_pwd=(EditText)view.findViewById(R.id.dialog_set_pwd);
                EditText comfir_pwd=(EditText)view.findViewById(R.id.dialog_set_pwds);
                String pwd = set_pwd.getText().toString();
                String cf_pwd = comfir_pwd.getText().toString();
                if(TextUtils.isEmpty(pwd)||TextUtils.isEmpty(cf_pwd)){
                    Toast.makeText(getContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    if(pwd.equals(cf_pwd)){
                        SPUtils.put(getContext(),"more_pwd",cf_pwd);

                        Dialog.dismiss();
                    }else {
                        Toast.makeText(getContext(),"密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
