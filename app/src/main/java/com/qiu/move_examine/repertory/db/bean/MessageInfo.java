package com.qiu.move_examine.repertory.db.bean;

import com.satsoftec.frame.repertory.dbTool.BaseEntity;
import com.satsoftec.frame.repertory.dbTool.Table;

/**
 * @author Mr.Qiu
 * @date 2019/09/12
 */
@Table(name = "MessageInfo")
public class MessageInfo extends BaseEntity {
    private Long ownerId;
    private Long noticeId;
    private String title;
    private String content;
    private Boolean noticeHaveRead;

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
