package org.netty.module;

import java.util.Map;

/**
 * ������Ϣ����
 *
 * @author ���
 * @version 2016/02/24 19:40
 */
public class PushMsg extends BaseMsg {

    private String title;
    private String content;
    private String extMap;

    public PushMsg() {
        super();
        setType(MsgType.PUSH);
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
}
