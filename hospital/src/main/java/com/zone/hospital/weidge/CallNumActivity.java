package com.zone.hospital.weidge;

import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.zone.hospital.R;
import com.zone.hospital.base.BaseActivity;
import com.zone.hospital.model.adapter.HospitalAdapter;
import com.zone.hospital.model.bean.Keshi;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CallNumActivity extends BaseActivity implements  AdapterView.OnItemClickListener{


    RequestQueue mqueue;
    final  String url="http://192.168.4.51:8091/posts";
    private HospitalAdapter adapter;
    //科室列表
    private List<Keshi> keshiLists=new ArrayList<>();
    private ListView listView;

    private LayoutInflater layoutInflater;
    private View poplayout;
    private  View view;
    private PopupWindow popuWindow;
    private TextView textkeshi;
    private Button callnum_button;

    public static final int LEVEL_KESHI=0;

    private  static int   selectNum;
    private  String   selectKeshiname;

    private Toolbar toolbar;

    @InjectView(R.id.tv_title)
    TextView mTvTitle;

    /*
   当前选中的科室
   */
    private int currentkeshi;

    Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callnumber);


        ButterKnife.inject(this);
        String title = getIntent().getStringExtra("title");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        mTvTitle.setText(title);

        view=findViewById(R.id.activity_callnumber);
        mqueue= Volley.newRequestQueue(this);

        listView=(ListView)findViewById(R.id.keshi_list);
        listView.setOnItemClickListener(this);


        adapter=new HospitalAdapter(CallNumActivity.this
                ,R.layout.item_keshi ,keshiLists);

         timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                requestData();
            }
        },1000,5000);


    }



    private void requestData() {

        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try {


                            //Json的解析类对象
                            JsonParser parser = new JsonParser();
                            //将JSON的String 转成一个JsonArray对象
                            JsonArray jsonArray = parser.parse(s).getAsJsonArray();

                            Gson gson = new Gson();

                            if(keshiLists!=null){
                                //先清除掉以前的数据
                                keshiLists.clear();

                                for(JsonElement keshi:jsonArray){

                                    Keshi keshiBean=gson.fromJson(keshi,Keshi.class);
                                    keshiLists.add(keshiBean);
                                    //Log.e("111","====="+keshiBean.getName()+keshiBean.getNumber());
                                }

                            }

                            if(adapter!=null){

                                adapter.notifyDataSetChanged();
                                listView.setSelection(0);
                                listView.setAdapter(adapter);

                            }




                        }catch (JsonSyntaxException E){
                            Toast.makeText(CallNumActivity.this,"网络异常",Toast.LENGTH_SHORT).show();
                            //Log.e("111","网络异常1");
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(CallNumActivity.this,"网络异常",Toast.LENGTH_SHORT).show();
                //Log.e("111","网络异常2");
            }
        });
        mqueue.add(request);

    }


    //列表单击事件
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            selectKeshiname=keshiLists.get(position).getName();
            selectNum=Integer.parseInt(keshiLists.get(position).getNumber());
          // Snackbar.make(listView,"选中"+selectKeshiname,Snackbar.LENGTH_SHORT).show();
            showDocter(selectKeshiname);

    }

    //医生选择对话框
    public void showDocter(final String selectKeshiname){

        //这里还要获取到选中的的科室，再获取选中科室的医生人数，再设置到
        layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        poplayout=layoutInflater.inflate(R.layout.calll_window,null);

        textkeshi= (TextView)poplayout.findViewById(R.id.textkeshi);
        textkeshi.setText(selectKeshiname);

        callnum_button= (Button) poplayout.findViewById(R.id.callnum_button);

        callnum_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           Integer I=new Integer(selectNum+1);
            String num="您当前的排序号为："+I.toString();
           //对话框提示
            showMyDialog(num);

//                popuWindow.dismiss();
//                Snackbar.make(listView,num,Snackbar.LENGTH_SHORT).show();


            }
        });

        popuWindow=new PopupWindow(poplayout,900,900,true);
        popuWindow.setBackgroundDrawable(new BitmapDrawable());
        popuWindow.setFocusable(true);
        popuWindow.setOutsideTouchable(true);
        popuWindow.showAtLocation(view, Gravity.CENTER,0,0);

    }


     private void showMyDialog(String num){

         popuWindow.dismiss();

         AlertDialog.Builder dialog=new AlertDialog.Builder(CallNumActivity.this);
         dialog.setTitle("提示");
         dialog.setMessage(num);
         dialog.setCancelable(false);
         dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();

                 //准备提交当前号码
                 // postData(selectKeshiname,(selectNum+1));
             }
         });
         dialog.show();

}

    @OnClick(R.id.layout_return)
    public void onClick() {
        onBackPressed();
    }

    @Override
    protected void onPause() {
        timer.cancel();
        super.onPause();
    }
}
