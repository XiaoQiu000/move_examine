package com.qiu.move_examine.repertory.webservice.response;

import com.qiu.move_examine.common.bean.ErrorBean;

import java.util.List;

public class OperateResponse {

    /**
     * jsonrpc : 2.0
     * id : 1
     * result : {"code":"1","msg":"OK","data":[{"operationId":"cd2a138e502e4ae5ba5dfb5a9af9df61","operationCode":1,"operationMsg":"","operationNum":null},{"operationId":"d407b887a9fd4ee79253e1dd2d7dbd9e","operationCode":1,"operationMsg":"","operationNum":1},{"operationId":"8148f9d3731d4cbe9cecff48bee319df","operationCode":1,"operationMsg":"","operationNum":2}],"sign":"1qqqqwwwwwwwwwwwwww"}
     */

    private String jsonrpc;
    private String id;
    private ResultBean result;
    private ErrorBean error;
    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public static class ResultBean {
        /**
         * code : 1
         * msg : OK
         * data : [{"operationId":"cd2a138e502e4ae5ba5dfb5a9af9df61","operationCode":1,"operationMsg":"","operationNum":null},{"operationId":"d407b887a9fd4ee79253e1dd2d7dbd9e","operationCode":1,"operationMsg":"","operationNum":1},{"operationId":"8148f9d3731d4cbe9cecff48bee319df","operationCode":1,"operationMsg":"","operationNum":2}]
         * sign : 1qqqqwwwwwwwwwwwwww
         */

        private String code;
        private String msg;
        private String sign;
        private List<DataBean> data;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * operationId : cd2a138e502e4ae5ba5dfb5a9af9df61
             * operationCode : 1
             * operationMsg :
             * operationNum : null
             */

            private String operationId;
            private int operationCode;
            private String operationMsg;
            private Object operationNum;

            public String getOperationId() {
                return operationId;
            }

            public void setOperationId(String operationId) {
                this.operationId = operationId;
            }

            public int getOperationCode() {
                return operationCode;
            }

            public void setOperationCode(int operationCode) {
                this.operationCode = operationCode;
            }

            public String getOperationMsg() {
                return operationMsg;
            }

            public void setOperationMsg(String operationMsg) {
                this.operationMsg = operationMsg;
            }

            public Object getOperationNum() {
                return operationNum;
            }

            public void setOperationNum(Object operationNum) {
                this.operationNum = operationNum;
            }
        }
    }
}
