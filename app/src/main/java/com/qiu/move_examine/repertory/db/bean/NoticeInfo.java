package com.qiu.move_examine.repertory.db.bean;

import com.qiu.move_examine.common.bean.CarBean;
import com.qiu.move_examine.common.bean.PersonBean;
import com.qiu.move_examine.common.bean.ThingsBean;
import com.satsoftec.frame.repertory.dbTool.BaseEntity;
import com.satsoftec.frame.repertory.dbTool.Table;

/**
 * @author Mr.Qiu
 * @date 2019/09/12
 */
@Table(name = "NoticeInfo")
public class NoticeInfo extends BaseEntity {
    private String ownerId;
    private int noticeId;
    private String targetType;//目标类型:01人02车03物
    private String monitorType;//布控类型:01抓捕02拦截03通知
    private String cover;//图片
    private String pushTime;

    private String person;//人
    private String car;//车
    private String things;//物

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }


    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getThings() {
        return things;
    }

    public void setThings(String things) {
        this.things = things;
    }

    private Boolean noticeHaveRead;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public Boolean getNoticeHaveRead() {
        if (noticeHaveRead == null) {
            return false;
        }
        return noticeHaveRead;
    }

    public void setNoticeHaveRead(Boolean noticeHaveRead) {
        this.noticeHaveRead = noticeHaveRead;
    }
}
