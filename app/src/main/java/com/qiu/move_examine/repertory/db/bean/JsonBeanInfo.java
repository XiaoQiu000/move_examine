package com.qiu.move_examine.repertory.db.bean;

import com.google.gson.Gson;
import com.satsoftec.frame.SFrame;
import com.satsoftec.frame.repertory.dbTool.BaseEntity;
import com.satsoftec.frame.repertory.dbTool.DatabaseManage;
import com.satsoftec.frame.repertory.dbTool.Table;
import com.satsoftec.frame.util.CommonUtil;


/**
 * @author soap
 * @date 16/2/27
 */
@Table(name = "JsonBeanInfo")
public class JsonBeanInfo extends BaseEntity {
    private Long ownerId;
    private Long jsonId;
    private Integer type;
    private String json;

    public Long getJsonId() {
        return jsonId;
    }

    public void setJsonId(Long jsonId) {
        this.jsonId = jsonId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public class Type {
        public final static int TARGET_LIST = 1;
    }

    public static <T> T getBean(Long ownerId, Long jsonId, Integer type, Class<T> clazz) {
        Gson gson = SFrame.getGson();
        JsonBeanInfo jsonBeanInfo = DatabaseManage.getBean(JsonBeanInfo.class, "jsonId=" + jsonId + " and ownerId=" + ownerId + " and type=" + type);
        if (jsonBeanInfo != null && jsonBeanInfo.getType().equals(type) && CommonUtil.isNotNull(jsonBeanInfo.getJson())) {
            try {
                System.out.println("本地数据:" + jsonBeanInfo.getJson());
                return gson.fromJson(jsonBeanInfo.getJson(), clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <T> T getBean(Long ownerId, Long jsonId, Integer type, java.lang.reflect.Type typeOfT) {
        Gson gson = SFrame.getGson();
        JsonBeanInfo jsonBeanInfo = DatabaseManage.getBean(JsonBeanInfo.class, "jsonId=" + jsonId + " and ownerId=" + ownerId + " and type=" + type);
        if (jsonBeanInfo != null && jsonBeanInfo.getType().equals(type) && CommonUtil.isNotNull(jsonBeanInfo.getJson())) {
            try {
                return gson.fromJson(jsonBeanInfo.getJson(), typeOfT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <T> BeanInfo<T> getBeanWithCreateDate(Long ownerId, Long jsonId, Integer type, Class<T> clazz) {
        Gson gson = SFrame.getGson();
        JsonBeanInfo jsonBeanInfo = DatabaseManage.getBean(JsonBeanInfo.class, "jsonId=" + jsonId + " and ownerId=" + ownerId + " and type=" + type);
        if (jsonBeanInfo != null && jsonBeanInfo.getType().equals(type) && CommonUtil.isNotNull(jsonBeanInfo.getJson())) {
            try {
                System.out.println("本地数据:" + jsonBeanInfo.getJson());
                return new BeanInfo<>(gson.fromJson(jsonBeanInfo.getJson(), clazz), jsonBeanInfo.getCreateDate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <T> BeanInfo<T> getBeanWithCreateDate(Long ownerId, Long jsonId, Integer type, java.lang.reflect.Type typeOfT) {
        Gson gson = SFrame.getGson();
        JsonBeanInfo jsonBeanInfo = DatabaseManage.getBean(JsonBeanInfo.class, "jsonId=" + jsonId + " and ownerId=" + ownerId + " and type=" + type);
        if (jsonBeanInfo != null && jsonBeanInfo.getType().equals(type) && CommonUtil.isNotNull(jsonBeanInfo.getJson())) {
            try {
                return new BeanInfo<>((T) gson.fromJson(jsonBeanInfo.getJson(), typeOfT), jsonBeanInfo.getCreateDate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean saveBean(Long ownerId, Long jsonId, Integer type, Object obj) {
        Gson gson = SFrame.getGson();
        String json = gson.toJson(obj);
        JsonBeanInfo jsonBeanInfo = DatabaseManage.getBean(JsonBeanInfo.class, "jsonId=" + jsonId + " and ownerId=" + ownerId + " and type=" + type);
        if (jsonBeanInfo != null) {
            jsonBeanInfo.setJson(json);
            DatabaseManage.update(jsonBeanInfo, "jsonId=" + jsonId + " and ownerId=" + ownerId + " and type=" + type);
        } else {
            jsonBeanInfo = new JsonBeanInfo();
            jsonBeanInfo.setOwnerId(ownerId);
            jsonBeanInfo.setJsonId(jsonId);
            jsonBeanInfo.setType(type);
            jsonBeanInfo.setJson(json);
            DatabaseManage.insert(jsonBeanInfo);
        }
        return true;
    }

    public static class BeanInfo<T> {
        private String createDate;// 创建日期
        private T bean;// 修改日期

        public BeanInfo(T bean, String createDate) {
            this.bean = bean;
            this.createDate = createDate;
        }

        public T getBean() {
            return bean;
        }

        public void setBean(T bean) {
            this.bean = bean;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
