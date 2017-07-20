package com.zone.hospital;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zone.hospital.base.BaseActivity2;
import com.zone.hospital.global.URLaddress;
import com.zone.hospital.utils.okhttppostjson;

import butterknife.InjectView;
import butterknife.OnClick;


public class NavigationDetailActivity extends BaseActivity2 {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.btn3)
    Button btn3;
    @InjectView(R.id.btn4)
    Button btn4;
    @InjectView(R.id.btn5)
    Button btn5;
    @InjectView(R.id.btn6)
    Button btn6;
    @InjectView(R.id.btn7)
    Button btn7;
    @InjectView(R.id.btn8)
    Button btn8;
    @InjectView(R.id.btn9)
    Button btn9;
    @InjectView(R.id.btn10)
    Button btn10;
    @InjectView(R.id.btn11)
    Button btn11;
    @InjectView(R.id.btn12)
    Button btn12;
    @InjectView(R.id.btn13)
    Button btn13;
    @InjectView(R.id.btn14)
    Button btn14;
    @InjectView(R.id.btn15)
    Button btn15;
    @InjectView(R.id.btn16)
    Button btn16;
    @InjectView(R.id.btn17)
    Button btn17;
    @InjectView(R.id.btn18)
    Button btn18;
    @InjectView(R.id.btn_18)
    Button btn_18;
    @InjectView(R.id.btn19)
    Button btn19;
    @InjectView(R.id.btn20)
    Button btn20;
    @InjectView(R.id.btn21)
    Button btn21;
    @InjectView(R.id.btn22)
    Button btn22;
    @InjectView(R.id.btn23)
    Button btn23;
    @InjectView(R.id.btn24)
    Button btn24;
    @InjectView(R.id.btn25)
    Button btn25;
    @InjectView(R.id.btn26)
    Button btn26;
    @InjectView(R.id.btn27)
    Button btn27;
    @InjectView(R.id.btn28)
    Button btn28;
    @InjectView(R.id.btn29)
    Button btn29;
    @InjectView(R.id.btn30)
    Button btn30;
    @InjectView(R.id.btn31)
    Button btn31;
    @InjectView(R.id.btn32)
    Button btn32;
    @InjectView(R.id.btn33)
    Button btn33;
    @InjectView(R.id.btn34)
    Button btn34;
    @InjectView(R.id.btn35)
    Button btn35;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    private int floor_adress;


    private okhttppostjson post;
    private URLaddress j2str;
    private int floor;

    @Override
    public int setLayoutId() {
        return R.layout.activity_detail_navigation;
    }

    @Override
    public void init() {
        tvTitle.setText("导诊");
        post = new okhttppostjson();
        j2str = new URLaddress();
        floor = getIntent().getIntExtra("floor", 1);
        switch (floor) {
            case 1:
                post.poststring(j2str.ttsword("这是一楼"));
                break;
            case 2:
                post.poststring(j2str.ttsword("这是二楼"));
                break;
            case 3:
                post.poststring(j2str.ttsword("这是三楼"));
                break;
            case 4:
                post.poststring(j2str.ttsword("这是四楼"));
                break;
            case 5:
                post.poststring(j2str.ttsword("这是五楼"));
                break;

        }

    }

    @Override
    public boolean isUseToolbar() {
        return true;
    }


    //    @OnClick({R.id.tv_first,R.id.tv_second})
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.tv_first:
//                i=1;
//                showToast("click");
//                showAlertDialog("缴费处","您确定要去缴费处吗");
//                mSpeechSynthesizer.speak("确定要去交费处吗");
//                break;
//            case R.id.tv_second:
//                i=2;
//                showToast("click");
//                showAlertDialog("缴费处","您确定要去缴费处吗");
//                mSpeechSynthesizer.speak("确定要去交费处2吗");
//                break;
//        }
//
//    }
    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (floor_adress) {
                    case 1:

                        post.postaddress(j2str.address(btn1.getText().toString()).trim().replaceAll("\\s*|\t|\r|\n", ""));
                        Log.e("ee", btn1.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", ""));
                        break;
                    case 2:
                        post.postaddress(j2str.address(btn2.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 3:
                        post.postaddress(j2str.address(btn3.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 4:
                        post.postaddress(j2str.address(btn4.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 5:
                        post.postaddress(j2str.address(btn5.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;

                    case 6:
                        post.postaddress(j2str.address(btn6.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 7:
                        post.postaddress(j2str.address(btn7.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 8:
                        post.postaddress(j2str.address(btn8.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 9:
                        post.postaddress(j2str.address(btn9.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 10:
                        post.postaddress(j2str.address(btn10.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 11:
                        post.postaddress(j2str.address(btn11.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 12:
                        post.postaddress(j2str.address(btn12.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 13:
                        post.postaddress(j2str.address(btn13.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;

                    case 14:
                        post.postaddress(j2str.address(btn14.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 15:
                        post.postaddress(j2str.address(btn15.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 16:
                        post.postaddress(j2str.address(btn16.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 17:
                        post.postaddress(j2str.address(btn17.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 18:
                        post.postaddress(j2str.address(btn_18.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 19:
                        post.postaddress(j2str.address(btn19.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 20:
                        post.postaddress(j2str.address(btn20.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 21:
                        post.postaddress(j2str.address(btn21.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;

                    case 22:
                        post.postaddress(j2str.address(btn22.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 23:
                        post.postaddress(j2str.address(btn23.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 24:
                        post.postaddress(j2str.address(btn24.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 25:
                        post.postaddress(j2str.address(btn25.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 26:
                        post.postaddress(j2str.address(btn26.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 27:
                        post.postaddress(j2str.address(btn27.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 28:
                        post.postaddress(j2str.address(btn28.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 29:
                        post.postaddress(j2str.address(btn29.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;

                    case 30:
                        post.postaddress(j2str.address(btn30.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 31:
                        post.postaddress(j2str.address(btn31.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 32:
                        post.postaddress(j2str.address(btn32.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 33:
                        post.postaddress(j2str.address(btn33.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 34:
                        post.postaddress(j2str.address(btn34.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;
                    case 35:
                        post.postaddress(j2str.address(btn35.getText().toString().trim().replaceAll("\\s*|\t|\r|\n", "")));
                        break;

                }

                post.poststring(j2str.ttsword("请跟我来"));


            }
        });
        builder.setNegativeButton("取消", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn_18, R.id.btn19, R.id.btn20, R.id.btn21, R.id.btn22, R.id.btn23, R.id.btn24, R.id.btn25, R.id.btn26, R.id.btn27, R.id.btn28, R.id.btn29, R.id.btn30, R.id.btn31, R.id.btn32, R.id.btn33, R.id.btn34, R.id.btn35})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                floor_adress = 1;
                showAlertDialog("楼梯", "您确定要去楼梯吗");
                post.poststring(j2str.ttsword("您确定要去楼梯吗"));
                break;
            case R.id.btn2:
                floor_adress = 2;
                showAlertDialog("厕所", "您确定要去厕所吗");
                post.poststring(j2str.ttsword("您确定要去厕所吗"));
                break;
            case R.id.btn3:
                floor_adress = 3;
                showAlertDialog("妇诊", "您确定要去妇诊吗");
                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn4:
                floor_adress = 4;
                showAlertDialog("妇诊", "您确定要去妇诊吗");
                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn5:
                floor_adress = 5;
                showAlertDialog("妇诊", "您确定要去妇诊吗");
                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn6:
                floor_adress = 6;
                showAlertDialog("妇诊", "您确定要去妇诊吗");
                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn7:
                floor_adress = 7;
                showAlertDialog("妇诊", "您确定要去妇诊吗");
                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn8:
                floor_adress = 8;
                showAlertDialog("妇诊", "您确定要去妇诊吗");
                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn9:
                floor_adress = 9;
                showAlertDialog("妇诊", "您确定要去妇诊吗");
                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn10:
                floor_adress = 10;
                showAlertDialog("挂号处", "您确定要去挂号处吗");
                post.poststring(j2str.ttsword("您确定要去挂号处吗"));
                break;
            case R.id.btn11:
                floor_adress = 11;
                showAlertDialog("电梯", "您确定要去电梯吗");
                post.poststring(j2str.ttsword("您确定要去电梯吗"));
                break;
            case R.id.btn12:
                floor_adress = 12;
                showAlertDialog("输液观察室", "您确定要去输液观察室吗");
                post.poststring(j2str.ttsword("您确定要去输液观察室吗"));
                break;
            case R.id.btn13:
                floor_adress = 13;
                showAlertDialog("治疗室", "您确定要去治疗室吗");
                post.poststring(j2str.ttsword("您确定要去治疗室吗"));
                break;
            case R.id.btn14:
                floor_adress = 14;
                showAlertDialog("配药处", "您确定要去配药处吗");
                post.poststring(j2str.ttsword("您确定要去配药处吗"));
                break;
            case R.id.btn15:
                floor_adress = 15;
                showAlertDialog("输液观察室", "您确定要去输液观察室吗");
                post.poststring(j2str.ttsword("您确定要去输液观察室吗"));
                break;
            case R.id.btn16:
//                floor_adress=4;
//                showAlertDialog("妇诊","您确定要去妇诊吗");
//                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn17:
//                floor_adress=4;
//                showAlertDialog("妇诊","您确定要去妇诊吗");
//                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn18:
//                floor_adress=4;
//                showAlertDialog("妇诊","您确定要去妇诊吗");
//                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn_18:
                floor_adress = 18;
                showAlertDialog("楼梯", "您确定要去楼梯吗");
                post.poststring(j2str.ttsword("您确定要去楼梯吗"));
                break;
            case R.id.btn19:
                floor_adress = 19;
                showAlertDialog("药物处理", "您确定要去药物处理吗");
                post.poststring(j2str.ttsword("您确定要去药物处理吗"));
                break;
            case R.id.btn20:
                floor_adress = 20;
                showAlertDialog("前厅", "您确定要去前厅吗");
                post.poststring(j2str.ttsword("您确定要去前厅吗"));
                break;
            case R.id.btn21:
                floor_adress = 21;
                showAlertDialog("病人等候室", "您确定要去病人等候室吗");
                post.poststring(j2str.ttsword("您确定要去病人等候室吗"));
                break;
            case R.id.btn22:
                floor_adress = 22;
                showAlertDialog("手术室", "您确定要去手术室吗");
                post.poststring(j2str.ttsword("您确定要去手术室吗"));
                break;
            case R.id.btn23:
//                floor_adress=4;
//                showAlertDialog("妇诊","您确定要去妇诊吗");
//                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn24:
//                floor_adress=4;
//                showAlertDialog("妇诊","您确定要去妇诊吗");
//                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn25:
//                floor_adress=4;
//                showAlertDialog("妇诊","您确定要去妇诊吗");
//                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn26:
                floor_adress = 26;
                showAlertDialog("办公室", "您确定要去办公室吗");
                post.poststring(j2str.ttsword("您确定要去办公室吗"));
                break;
            case R.id.btn27:
                floor_adress = 27;
                showAlertDialog("术后休息室", "您确定要去术后休息室吗");
                post.poststring(j2str.ttsword("您确定要去术后休息室吗"));
                break;
            case R.id.btn28:
                floor_adress = 28;
                showAlertDialog("取药处", "您确定要去取药处吗");
                post.poststring(j2str.ttsword("您确定要去取药处吗"));
                break;
            case R.id.btn29:
                floor_adress = 29;
                showAlertDialog("更衣室", "您确定要去更衣室吗");
                post.poststring(j2str.ttsword("您确定要去更衣室吗"));
                break;
            case R.id.btn30:
                floor_adress = 30;
                showAlertDialog("医护办公室", "您确定要去医护办公室吗");
                post.poststring(j2str.ttsword("您确定要去医护办公室吗"));
                break;
            case R.id.btn31:
                floor_adress = 31;
                showAlertDialog("抢救室", "您确定要去抢救室吗");
                post.poststring(j2str.ttsword("您确定要去抢救室吗"));
                break;
            case R.id.btn32:
                floor_adress = 32;
                showAlertDialog("前厅", "您确定要去前厅吗");
                post.poststring(j2str.ttsword("您确定要去前厅吗"));
                break;
            case R.id.btn33:
//                floor_adress=4;
//                showAlertDialog("妇诊","您确定要去妇诊吗");
//                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn34:
//                floor_adress=4;
//                showAlertDialog("妇诊","您确定要去妇诊吗");
//                post.poststring(j2str.ttsword("您确定要去妇诊吗"));
                break;
            case R.id.btn35:
                floor_adress = 35;
                showAlertDialog("紧急出口", "您确定要去紧急出口吗");
                post.poststring(j2str.ttsword("您确定要去紧急出口吗"));
                break;
        }
    }
}
