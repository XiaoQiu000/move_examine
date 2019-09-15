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

    private PersonBean person;//人
    private CarBean car;//车
    private ThingsBean things;//物

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getInvolveCase() {
        return involveCase;
    }

    public void setInvolveCase(String involveCase) {
        this.involveCase = involveCase;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public void setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

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

    public PersonBean getPerson() {
        return person;
    }

    public void setPerson(PersonBean person) {
        this.person = person;
    }

    public CarBean getCar() {
        return car;
    }

    public void setCar(CarBean car) {
        this.car = car;
    }

    public ThingsBean getThings() {
        return things;
    }

    public void setThings(ThingsBean things) {
        this.things = things;
    }
}
