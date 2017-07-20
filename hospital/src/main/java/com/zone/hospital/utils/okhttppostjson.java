package com.zone.hospital.utils;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zone.hospital.global.URLaddress;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class okhttppostjson  {

    public void postaddress(String address){

        if(URLaddress.ADDRESS_URL!=null) {
            //这里的地址指的是机器人被指定要去的地址
            OkGo.post(URLaddress.ADDRESS_URL)//
                    .tag(this)//
                    //	.params("param1", "paramValue1")//  这里不要使用params，upJson 与 params 是互斥的，只有 upJson 的数据会被上传
                    .upJson(address)//
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            //上传成功
                            Log.e("++", s);
                        }

                        @Override
                        public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                            //这里回调上传进度(该回调在主线程,可以直接更新ui)
                        }
                    });
        }
    }


    public void poststring(String word){
        Log.d("222","   "+ URLaddress.ADDRESS_URL);
        Log.d("222","   "+ URLaddress.KF_URL);
        if(URLaddress.KF_URL!=null){
        OkGo.post(URLaddress.KF_URL)//
                .tag(this)//
                //	.params("param1", "paramValue1")//  这里不要使用params，upJson 与 params 是互斥的，只有 upJson 的数据会被上传
                .upJson(word)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //上传成功
                        Log.e("++",s);
                    }
                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
                    }
                });
    }

    }
}
