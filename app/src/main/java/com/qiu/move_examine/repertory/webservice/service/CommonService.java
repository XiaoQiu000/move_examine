package com.qiu.move_examine.repertory.webservice.service;

import com.qiu.move_examine.common.AppContext;
import com.qiu.move_examine.common.ClientConstant;
import com.qiu.move_examine.common.bean.UserInfoBean;
import com.qiu.move_examine.common.utils.RandomUtils;
import com.qiu.move_examine.repertory.webservice.request.ConnectRequest;
import com.qiu.move_examine.repertory.webservice.request.OperateRequest;
import com.qiu.move_examine.repertory.webservice.request.QueryRequest;
import com.qiu.move_examine.repertory.webservice.response.ConnectResponse;
import com.qiu.move_examine.repertory.webservice.response.OperateResponse;
import com.qiu.move_examine.repertory.webservice.response.QueryResponse;
import com.satsoftec.frame.repertory.remote.BaseWebService;
import com.satsoftec.frame.repertory.remote.WebTask;
import com.satsoftec.frame.util.SharedPreferenceUtil;

/**
 * @author Mr.Qiu
 */
public class CommonService extends BaseWebService {
    /**
     * 接口请求相关参数
     */
    public final static String ID = "1";
    public final static String APPID = "APPID";//资源服务提供方 ID
    public final static String JSONRPC = "2.0";
    public final static String VERSON = "20190925";//协议版本号
    public final static String SN = "";//证书SN

    /**
     * 通过账号密码登录
     *
     * @param account
     * @param password
     * @return
     */
    public WebTask<QueryResponse> userLoginByPhone(String account, String password) {
        String condition = "PER_NO = '" + account + "' and PER_PWD = '" + password + "'";
        return queryInterface(condition, "loginApp", 0, 0);
    }

    /**
     * 查询目标库列表
     *
     * @return
     */
    public WebTask<QueryResponse> targetList(String condition, int page, int pageSize) {
        return queryInterface(condition, "pushDataQryAll", page, pageSize);
    }

    /**
     * 推送消息查询
     *
     * @return
     */
    public WebTask<QueryResponse> pushList() {
        return queryInterface("", "pushDataQryUnPush", 0, 0);
    }

    /**
     * 消息详情
     *
     * @return
     */

    public WebTask<QueryResponse> targetDetails(String id) {
        String condition = "id = '" + id + "'";
        return queryInterface(condition, "pushDataQryInfo", 0, 0);
    }

    /**
     * 建立连接接口
     *
     * @return
     */
    public WebTask<ConnectResponse> connectInterface() {
        ConnectRequest request = new ConnectRequest();
        request.setId(ID);
        request.setJsonrpc(JSONRPC);
        request.setMethod("connect");
        ConnectRequest.ParamsBean pb = new ConnectRequest.ParamsBean();
        ConnectRequest.ParamsBean.DataBean cpd = new ConnectRequest.ParamsBean.DataBean();
        cpd.setVersion(VERSON);
        cpd.setAppId(APPID);
        cpd.setTimestamp(System.currentTimeMillis() + "");
        cpd.setNonce(RandomUtils.getRandomString(26));
        pb.setData(cpd);
        pb.setSign("");
        request.setParams(pb);
        return request("", request, null, ConnectResponse.class);
    }

    /**
     * 查询接口
     *
     * @param condition 查询条件
     * @return
     */
    public WebTask<QueryResponse> queryInterface(String condition, String dataObjId, int page, int pageSize) {
        String userId = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_ID);
        String perName = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_NAME);
        String perNo = SharedPreferenceUtil.getSharedPreString(ClientConstant.SPREFERENCES_LOGIN_ACCOUNT);
        QueryRequest request = new QueryRequest();
        request.setId(ID);
        request.setJsonrpc(JSONRPC);
        request.setMethod("query");
        QueryRequest.ParamsBean pb = new QueryRequest.ParamsBean();
        QueryRequest.ParamsBean.DataBean cpd = new QueryRequest.ParamsBean.DataBean();
        cpd.setVersion(VERSON);
        cpd.setSessionId(ClientConstant.sessionId);
        QueryRequest.ParamsBean.DataBean.UserInfoBean uib = new QueryRequest.ParamsBean.DataBean.UserInfoBean();
        uib.setUserId(userId == null ? "" : (userId + ""));
        uib.setUserName(perName);
        uib.setUserDeptNo(perNo);
        uib.setSn(SN);
        uib.setSfzh("");
        QueryRequest.ParamsBean.DataBean.UserInfoBean.ExtAttrBean eab = new QueryRequest.ParamsBean.DataBean.UserInfoBean.ExtAttrBean();
        uib.setExtAttr(eab);
        cpd.setUserInfo(uib);
        QueryRequest.ParamsBean.DataBean.SourceBean sb = new QueryRequest.ParamsBean.DataBean.SourceBean();
        sb.setSourceId("DS-01,DS-02");
        cpd.setSource(sb);
        cpd.setDataObjId(dataObjId);
        cpd.setCondition(condition);
        cpd.setFields("");
        cpd.setOrderBy("");
        QueryRequest.ParamsBean.DataBean.PageBean dpb = new QueryRequest.ParamsBean.DataBean.PageBean();
        dpb.setPageNo(page);
        dpb.setPageSize(pageSize);
        cpd.setPage(dpb);
        pb.setData(cpd);
        pb.setSign("");
        request.setParams(pb);
        return request("", request, null, QueryResponse.class);
    }
}
