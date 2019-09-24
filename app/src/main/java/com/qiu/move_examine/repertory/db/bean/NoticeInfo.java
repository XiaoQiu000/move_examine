package com.qiu.move_examine.repertory.db.bean;

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
    private String title;
    private String content;
    private String extMap;
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

    public String getExtMap() {
        return extMap;
    }

    public void setExtMap(String extMap) {
        this.extMap = extMap;
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
