package com.qiu.move_examine.repertory.webservice.response;

import com.qiu.move_examine.common.bean.UserInfoBean;

/**
 * @author Mr.Qiu
 */
public class LoginResponse {
    private String message;
    private String status;
    private UserInfoBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserInfoBean getData() {
        return data;
    }

    public void setData(UserInfoBean data) {
        this.data = data;
    }
}
