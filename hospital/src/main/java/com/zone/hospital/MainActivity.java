package com.zone.hospital;


import android.app.AlertDialog;
import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zone.hospital.base.BaseActivity2;
import com.zone.hospital.global.URLaddress;
import com.zone.hospital.model.adapter.MyFragmentPageAdapter;
import com.zone.hospital.utils.SPUtils;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity2 {
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.vp_page)
    ViewPager mVpPage;
    @InjectView(R.id.layout_home)
    LinearLayout mLayoutHome;
    @InjectView(R.id.layout_hospital)
    LinearLayout mLayoutHospital;
    @InjectView(R.id.layout_person)
    LinearLayout mLayoutPerson;
    @InjectView(R.id.layout_more)
    LinearLayout mLayoutMore;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
//    @InjectView(R.id.ivHome)
//    ImageView mIvHome;
//    @InjectView(R.id.ivHospital)
//    ImageView mIvHospital;

    NsdManager mNsdManager;
    NsdManager.DiscoveryListener mDiscoveryListener;
    NsdManager.ResolveListener mResolveListener;
    String TAG="111";

    String SERVICE_TYPE="_ros-master._tcp.";
    String mServiceName="NsdChat";

    int port;
    InetAddress host;

    NsdServiceInfo mService;

    public  boolean isfinish=true;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
//        mToolbar.setTitle("奥讯机器人智能导诊系统");
        HomeFragment homeFragment = new HomeFragment();
        HospotalFragment hospotalFragment = new HospotalFragment();
        PersonFragment personFragment = new PersonFragment();
        MoreFragment moreFragment = new MoreFragment();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(homeFragment);
        fragmentList.add(hospotalFragment);
        fragmentList.add(personFragment);
        fragmentList.add(moreFragment);
        MyFragmentPageAdapter mFragmentAdapter = new MyFragmentPageAdapter(getSupportFragmentManager(), fragmentList);
        mVpPage.setAdapter(mFragmentAdapter);
        mLayoutHome.setBackgroundColor(getResources().getColor(R.color.colorBackGround));
        mVpPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetTabBackGround();
                switch (position) {
                    case 0:
                        mLayoutHome.setBackgroundColor(getResources().getColor(R.color.colorBackGround));

                        break;
                    case 1:
                        mLayoutHospital.setBackgroundColor(getResources().getColor(R.color.colorBackGround));

                        break;
                    case 2:
                        mLayoutPerson.setBackgroundColor(getResources().getColor(R.color.colorBackGround));

                        break;
                    case 3:
                        mLayoutMore.setBackgroundColor(getResources().getColor(R.color.colorBackGround));

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void shouwDialoge() {
        String mobile_seve_pwd = (String) SPUtils.get(getApplicationContext(), "more_pwd", "");
        if (TextUtils.isEmpty(mobile_seve_pwd)) {
            showsetDialog();
        } else {
            showConfirDialog();
        }
    }

    /**
     * 设置初次进入 设置密码对话框
     */
    private void showsetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog Dialog = builder.create();
        final View view = View.inflate(this, R.layout.dialog_set_pwd, null);
        Dialog.setView(view);
        Dialog.show();
        Button but_commit = (Button) view.findViewById(R.id.but_commit);
        Button but_cancel = (Button) view.findViewById(R.id.but_cancel);
        but_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.dismiss();
            }
        });
        but_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText set_pwd = (EditText) view.findViewById(R.id.dialog_set_pwd);
                EditText comfir_pwd = (EditText) view.findViewById(R.id.dialog_set_pwds);
                String pwd = set_pwd.getText().toString();
                String cf_pwd = comfir_pwd.getText().toString();
                if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(cf_pwd)) {
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (pwd.equals(cf_pwd)) {
                        SPUtils.put(getApplicationContext(), "more_pwd", cf_pwd);
                        mVpPage.setCurrentItem(3);
                        Dialog.dismiss();
                    } else {
                        Toast.makeText(getApplicationContext(), "密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


    /**
     * 设置再次进入  填写密码对话框
     */
    private void showConfirDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog Dialog = builder.create();
        final View view = View.inflate(this, R.layout.dialog_comfir_pwd, null);
        Dialog.setView(view);
        Dialog.show();
        Button but_commit = (Button) view.findViewById(R.id.but_commit);
        Button but_cancel = (Button) view.findViewById(R.id.but_cancel);
        but_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.dismiss();
            }
        });
        but_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText comfir_pwd = (EditText) view.findViewById(R.id.dialog_set_pwds);
                String cf_pwd = comfir_pwd.getText().toString();
                String savepwd = (String) SPUtils.get(getApplicationContext(), "more_pwd", "");
                if (TextUtils.isEmpty(cf_pwd)) {
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (savepwd.equals(cf_pwd)) {
                        mVpPage.setCurrentItem(3);
                        Dialog.dismiss();
                    } else {
                        Toast.makeText(getApplicationContext(), "密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean isUseToolbar() {
        return false;
    }


    @OnClick({R.id.layout_home, R.id.layout_hospital, R.id.layout_person, R.id.layout_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_home:
                mVpPage.setCurrentItem(0);
                resetTabBackGround();
                mLayoutHome.setBackgroundColor(getResources().getColor(R.color.colorBackGround));
                break;
            case R.id.layout_hospital:
                resetTabBackGround();
                mLayoutHospital.setBackgroundColor(getResources().getColor(R.color.colorBackGround));

                mVpPage.setCurrentItem(1);
                break;
            case R.id.layout_person:
                resetTabBackGround();
                mLayoutPerson.setBackgroundColor(getResources().getColor(R.color.colorBackGround));

                mVpPage.setCurrentItem(2);
                break;
            case R.id.layout_more:
                resetTabBackGround();
                mLayoutMore.setBackgroundColor(getResources().getColor(R.color.colorBackGround));

                shouwDialoge();

                break;
        }
    }

    private void resetTabBackGround() {
        mLayoutHome.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        mLayoutHospital.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        mLayoutMore.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        mLayoutPerson.setBackgroundColor(getResources().getColor(R.color.colorWhite));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        new Thread(new Runnable() {
            @Override
            public void run() {



                    dicoverservice();


            }
        }).start();
        Log.d(TAG, "Service discovery started");
    }

    private void dicoverservice() {


        mNsdManager =(NsdManager)getBaseContext().getSystemService(Context.NSD_SERVICE);


        // Instantiate a new DiscoveryListener
        mDiscoveryListener = new NsdManager.DiscoveryListener() {

            //  Called as soon as service discovery begins.
            @Override
            public void onDiscoveryStarted(String regType) {
                Log.d(TAG, "Service discovery started");
            }

            @Override
            public void onServiceFound(NsdServiceInfo service) {
                // A service was found!  Do something with it.
                Log.d(TAG, "Service discovery success" + service);
                mNsdManager.resolveService(service, mResolveListener);
                if (service.getServiceType().equals(SERVICE_TYPE)) {
                    // Service type is the string containing the protocol and
                    // transport layer for this service.

                    Log.d(TAG, "Unknown Service Type: " + service.getServiceType());
                } else if (service.getServiceName().equals(mServiceName)) {
                    // The name of the service tells the user what they'd be
                    // connecting to. It could be "Bob's Chat App".
                    Log.d(TAG, "Same machine: " + mServiceName);
                    mNsdManager.resolveService(service, mResolveListener);
                } else if (service.getServiceName().contains("NsdChat")){
                    mNsdManager.resolveService(service, mResolveListener);
                }
            }

            @Override
            public void onServiceLost(NsdServiceInfo service) {
                // When the network service is no longer available.
                // Internal bookkeeping code goes here.
                Log.e(TAG, "service lost" + service);
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.i(TAG, "Discovery stopped: " + serviceType);
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                mNsdManager.stopServiceDiscovery(this);
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                mNsdManager.stopServiceDiscovery(this);
            }
        };


        //当有设备注册NSD服务的时候，其他设备就可进行查找
        mNsdManager.discoverServices(
                "_ros-master._tcp.", NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);




        //连接服务监听器
        mResolveListener = new NsdManager.ResolveListener() {

            @Override
            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
                // Called when the resolve fails.  Use the error code to debug.
                Log.e(TAG, "Res    olve failed" + errorCode);
            }

            @Override
            public void onServiceResolved(NsdServiceInfo serviceInfo) {

                Log.e(TAG, "Resolve Succeeded. " + serviceInfo);
                mService = serviceInfo;
                port = serviceInfo.getPort();
                host = serviceInfo.getHost();

                if(host!=null){

               //  Toast.makeText(MainActivity.this,"端口："+port+"\n"+"IP:"+host,Toast.LENGTH_LONG).show();

                    /**
                     * 导航位置服务器URL
                     */
                    URLaddress.ADDRESS_URL="http:/"+host+":"+port+"/ros/move_to";
                    /**
                     * 科飞语音服务器URL
                     */
                    URLaddress.KF_URL="http:/"+host+":"+port+"/ros/write";
                }

                Log.d(TAG,"   "+ URLaddress.ADDRESS_URL);
                Log.d(TAG,"   "+ URLaddress.KF_URL);

            }
        };



    }

}
