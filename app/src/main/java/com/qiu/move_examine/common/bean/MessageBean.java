package com.qiu.move_examine.common.bean;

/**
 * @author Mr.Qiu
 * @date 2019/09/12
 */
public class MessageBean {
    private int id;
    private String targetType;//目标类型:01人02车03物
    private String monitorType;//布控类型:01抓捕02拦截03通知
    private String cover;//图片
    private String involveCase;//涉及案件
    private String characterDescription;//异常特征描述
    private String pushTime;

    private String inspectionUnit;//稽查单位
    private String unitPer;//稽查单位联系人
    private String unitTel;//稽查单位联系方式

    private MessageBean.PersonBean person;//人
    private MessageBean.CarBean car;//车
    private MessageBean.ThingsBean things;//物


    public String getInspectionUnit() {
        return inspectionUnit;
    }

    public void setInspectionUnit(String inspectionUnit) {
        this.inspectionUnit = inspectionUnit;
    }

    public String getUnitPer() {
        return unitPer;
    }

    public void setUnitPer(String unitPer) {
        this.unitPer = unitPer;
    }

    public String getUnitTel() {
        return unitTel;
    }

    public void setUnitTel(String unitTel) {
        this.unitTel = unitTel;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public void setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getInvolveCase() {
        return involveCase;
    }

    public void setInvolveCase(String involveCase) {
        this.involveCase = involveCase;
    }

    public MessageBean.PersonBean getPerson() {
        return person;
    }

    public void setPerson(MessageBean.PersonBean person) {
        this.person = person;
    }

    public MessageBean.CarBean getCar() {
        return car;
    }

    public void setCar(MessageBean.CarBean car) {
        this.car = car;
    }

    public MessageBean.ThingsBean getThings() {
        return things;
    }

    public void setThings(MessageBean.ThingsBean things) {
        this.things = things;
    }

    public static class PersonBean {
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

    public static class CarBean {
        private String brand;//品牌
        private String color;//颜色
        private String carNo;//车牌号

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }
    }

    public static class ThingsBean {
        private String items;//物品
        private String shape;//形状
        private String color;//颜色

        public String getItems() {
            return items;
        }

        public void setItems(String items) {
            this.items = items;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
