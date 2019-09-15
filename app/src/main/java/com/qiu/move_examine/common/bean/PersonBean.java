package com.qiu.move_examine.common.bean;

public class PersonBean {
    private String perName;//姓名
    private String perSex;//性别:01男02女03其他(比如金星,也可能没有03)
    private String perFigure;//体态:01胖02瘦
    private String perIdNo;//身份证号

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getPerSex() {
        return perSex;
    }

    public void setPerSex(String perSex) {
        this.perSex = perSex;
    }

    public String getPerFigure() {
        return perFigure;
    }

    public void setPerFigure(String perFigure) {
        this.perFigure = perFigure;
    }

    public String getPerIdNo() {
        return perIdNo;
    }

    public void setPerIdNo(String perIdNo) {
        this.perIdNo = perIdNo;
    }
}
