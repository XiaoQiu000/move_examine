package com.qiu.move_examine.repertory.db.bean;


import com.satsoftec.frame.repertory.dbTool.BaseEntity;
import com.satsoftec.frame.repertory.dbTool.Table;


/**
 * @author soap
 * @date 16/7/7
 */
@Table(name = "UserAccountBean")
public class UserAccountBean extends BaseEntity {

    private Long userId;
    private String nickName;
    private String phone;
    private String avatar;

    public UserAccountBean() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
