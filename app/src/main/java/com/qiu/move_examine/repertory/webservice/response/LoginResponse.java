package com.qiu.move_examine.repertory.webservice.response;

/**
 * @author Mr.Qiu
 */
public class LoginResponse {

    private MessageBean message;
    private String status;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class MessageBean {
        /**
         * id : 123456
         * perName : 张三
         * perIcon : null
         * inspectionUnit : 城阳xxx分局
         * perTel : 13888888888
         * perNo : 3714220001
         * perPwd : 123456
         * cid : null
         * perType : null
         * crtPerNo : null
         * crtTime : null
         * uptPerNo : null
         * uptTime : null
         * dtSta : N
         * field1 : null
         * field2 : null
         * field3 : null
         */

        private int id;
        private String perName;
        private Object perIcon;
        private String inspectionUnit;
        private String perTel;
        private String perNo;
        private String perPwd;
        private Object cid;
        private Object perType;
        private Object crtPerNo;
        private Object crtTime;
        private Object uptPerNo;
        private Object uptTime;
        private String dtSta;
        private Object field1;
        private Object field2;
        private Object field3;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPerName() {
            return perName;
        }

        public void setPerName(String perName) {
            this.perName = perName;
        }

        public Object getPerIcon() {
            return perIcon;
        }

        public void setPerIcon(Object perIcon) {
            this.perIcon = perIcon;
        }

        public String getInspectionUnit() {
            return inspectionUnit;
        }

        public void setInspectionUnit(String inspectionUnit) {
            this.inspectionUnit = inspectionUnit;
        }

        public String getPerTel() {
            return perTel;
        }

        public void setPerTel(String perTel) {
            this.perTel = perTel;
        }

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

        public Object getCid() {
            return cid;
        }

        public void setCid(Object cid) {
            this.cid = cid;
        }

        public Object getPerType() {
            return perType;
        }

        public void setPerType(Object perType) {
            this.perType = perType;
        }

        public Object getCrtPerNo() {
            return crtPerNo;
        }

        public void setCrtPerNo(Object crtPerNo) {
            this.crtPerNo = crtPerNo;
        }

        public Object getCrtTime() {
            return crtTime;
        }

        public void setCrtTime(Object crtTime) {
            this.crtTime = crtTime;
        }

        public Object getUptPerNo() {
            return uptPerNo;
        }

        public void setUptPerNo(Object uptPerNo) {
            this.uptPerNo = uptPerNo;
        }

        public Object getUptTime() {
            return uptTime;
        }

        public void setUptTime(Object uptTime) {
            this.uptTime = uptTime;
        }

        public String getDtSta() {
            return dtSta;
        }

        public void setDtSta(String dtSta) {
            this.dtSta = dtSta;
        }

        public Object getField1() {
            return field1;
        }

        public void setField1(Object field1) {
            this.field1 = field1;
        }

        public Object getField2() {
            return field2;
        }

        public void setField2(Object field2) {
            this.field2 = field2;
        }

        public Object getField3() {
            return field3;
        }

        public void setField3(Object field3) {
            this.field3 = field3;
        }
    }
}
