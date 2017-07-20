package com.zone.hospital.model.bean;

import com.zone.hospital.model.adapter.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class Healthbean extends Entity implements Serializable{
    private String sickness;
    private String[] question={""};
    private String[] description={""};
      public Healthbean(String sickness, String[] question, String[] description){
          this.sickness = sickness;
          this.question = question;
          this.description = description;
      }
    public String getSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public String[] getquestion() {
        return question;
    }

    public void setquestion(String[] question) {
        this.question = question;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }
}
