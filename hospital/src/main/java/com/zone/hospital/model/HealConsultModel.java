package com.zone.hospital.model;

import com.zone.hospital.model.bean.Healthbean;
import com.zone.hospital.model.bean.SicknissBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class HealConsultModel {
    private static List<Healthbean> illnessList;//10
    private static List<Healthbean> drugList;//11
    private static List<Healthbean> aidList;//12
    public static List<Healthbean> drugList() {
        return drugList;
    }

    public static void setdrugList(List<Healthbean> drugList) {
        HealConsultModel.drugList = drugList;
    }
    public  static List<Healthbean> getdrugList() {
        return drugList;
    }
    public static List<Healthbean> getillnessList() {
        return illnessList;
    }

    public static void setillnessList(List<Healthbean> illnessList) {
        HealConsultModel.illnessList = illnessList;
    }
    public static List<Healthbean> getaidList() {
        return aidList;
    }

    public static void setaidList(List<Healthbean> aidList) {
        HealConsultModel.aidList = aidList;
    }
    public HealConsultModel(){

        illnessList=new ArrayList<>();
        illnessList.add(new Healthbean("常见疾病",new String[]{"感冒","慢性支气管炎","冠状动脉性心脏病","慢性胃炎"},  new String[]{"常见疾病","按部位查询","按科室查询"}));
        illnessList.add(new Healthbean("按部位查询", new String[]{"感冒","慢性支气管炎"}, new String[]{"常见疾病","按部位查询","按科室查询"}));
        illnessList.add(new Healthbean("按科室查询", new String[]{"感冒","慢性支气管炎"},new String[]{"常见疾病","按部位查询","按科室查询"}));
        drugList=new ArrayList<>();
        drugList.add(new Healthbean("常用药物",new String[]{"芬必得","新康泰克","吗丁啉"},   new String[]{"常用药物","用药须知","抢救药物"}));
        drugList.add(new Healthbean("用药须知",new String[]{"润喉片不可乱用"},   new String[]{"常用药物","用药须知","抢救药物"}));
        drugList.add(new Healthbean("抢救药物",   new String[]{"肾上腺素","多巴胺"},new String[]{"感冒","高血压"}));
        aidList=new ArrayList<>();
        aidList.add(new Healthbean("疾病急救",new String[]{"窒息的急救","休克急救"},  new String[]{"","",""}));
        aidList.add(new Healthbean("运动急救",new String[]{"骨折","脑震荡","脱臼"},  new String[]{"","",""}));
        aidList.add(new Healthbean("日常急救",new String[]{"自缢者急救","洗澡是晕倒急救"},  new String[]{"","",""}));
    }

}
