package com.zone.hospital.global;

import android.util.Base64;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by jack on 2017/6/13.
 */

public class URLaddress {
    /**
     * 导航位置服务器URL
     */

    public static String ADDRESS_URL=null;
    /**
     * 科飞语音服务器URL
     */
    public static String KF_URL=null;

    public  String   base64(String text){
        String text1= Base64.encodeToString(text.getBytes(),Base64.DEFAULT);
        return text1;
    }
    public String ttsword(String word){
        HashMap<String, String> params = new HashMap<>();
//        params.put("key1", "value1");
//        params.put("key2", "这里是需要提交的json格式数据");
//        params.put("key3", "也可以使用三方工具将对象转成json字符串");
        params.put("data",base64(word));
       JSONObject  jsonObject = new JSONObject(params);
        return jsonObject.toString();
    }
    public String address(String word){
        HashMap<String, String> params = new HashMap<>();
//        params.put("key1", "value1");
//        params.put("key2", "这里是需要提交的json格式数据");
//        params.put("key3", "也可以使用三方工具将对象转成json字符串");
        params.put("name",base64(word));
        JSONObject  jsonObject = new JSONObject(params);
        return jsonObject.toString();
    }
}
