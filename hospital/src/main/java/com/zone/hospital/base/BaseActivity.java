package com.zone.hospital.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zone.hospital.R;
import com.zone.hospital.global.URLaddress;
import com.zone.hospital.utils.T;
import com.zone.hospital.utils.okhttppostjson;


/**
 * Created by john on 2016/7/26.
 */
public abstract class BaseActivity extends AutoLayoutActivity {

    private AlertDialog dialog;
    private ProgressDialog mProgressDialog = null;
    private MyReceiver myReceiver;
    private okhttppostjson post;
    private URLaddress j2str;
    TextView tv1;
    TextView tv2;
    TextView tv3;



     class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {



            AlertDialog.Builder builder=new AlertDialog.Builder(BaseActivity.this);
            View view=getLayoutInflater().inflate(R.layout.receive_yuyin_window,null);

            tv1= (TextView) view.findViewById(R.id.hunjian1);
            tv2= (TextView) view.findViewById(R.id.hunjian2);
            tv3= (TextView) view.findViewById(R.id.hunjian3);
            post = new okhttppostjson();
            j2str = new URLaddress();

            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    post.poststring(j2str.ttsword(tv1.getText().toString()));
                }
            });
            tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  post.poststring(j2str.ttsword(tv2.getText().toString()));
                }
            });
            tv3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                post.poststring(j2str.ttsword(tv3.getText().toString()));
                }
            });

            builder.setView(view);
            builder.create().show();

        }
    }




    public interface Convert {
        void transaction(View dialogView, AlertDialog alertDialog);
    }

    public interface DialogOnClickListener {
        void positiveListener(DialogInterface dialog, int which);

        void negativeListener(DialogInterface dialog, int which);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void iniBase() {
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//创建返回键，并实现打开关/闭监听
        getSupportActionBar().setDisplayShowTitleEnabled(false);//隐藏系统默认的Title
    }

    protected void backhome(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
    }

    public void showProgressDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }


    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.hide();
        }
    }


    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    public void showToast(String message) {
        T.showShort(this, message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.MY_BROADCAST");
        myReceiver=new MyReceiver();
        registerReceiver(myReceiver,intentFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(myReceiver!=null){
            unregisterReceiver(myReceiver);
            myReceiver=null;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
