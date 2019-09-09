package com.qiu.move_examine.repertory.webservice.request;

/**
 * @author Mr.Qiu
 */
public class LoginRequest {
    private String perNo;
    private String perPwd;

    public String getPerNo() {
        return perNo;
    }

    public void setPerNo(String perNo) {
        this.perNo = perNo;
    }

    public String getPerPwd() {
        return perPwd;
    }

    public void setPerPwd(String perPwd) {
        this.perPwd = perPwd;
    }
}
