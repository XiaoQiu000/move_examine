package com.qiu.move_examine.repertory.db.bean;

import com.satsoftec.frame.repertory.dbTool.BaseEntity;
import com.satsoftec.frame.repertory.dbTool.Table;

/**
 * @author Mr.Qiu
 * @date 2017/10/12
 */
@Table(name = "noticeInfo")
public class MessageInfo extends BaseEntity {
    private Long ownerId;
    private Long noticeId;
    private String title;
    private String content;
    private String noticeType;
    private String extraData;
    private Boolean noticeHaveRead;
    /**
     * 0未处理 1已同意 2已忽略
     */
    private Integer operateType;

    public Integer getOperateType() {
        if (operateType == null) {
            return 0;
        }
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
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
