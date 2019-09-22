package com.qiu.move_examine.repertory.webservice.response;

import com.qiu.move_examine.common.bean.ErrorBean;

/**
 * @author Mr.Qiu
 * 建立连接Response
 */
public class ConnectResponse {
    /**
     * jsonrpc : 2.0
     * id : 1
     * result : {"code":"1","msg":"OK","data":{"appId":"30d1fca698f449a5853129a43b935637","timestamp":"20180611142304554","nonce":"QswduIyb","sessionId":"e68f26bb70ab41b8a9fa2a37b34ab1bc"},"sign":"1qqqqwwwwwwwwwwwwww"}
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

    public static class ResultBean {
        /**
         * code : 1
         * msg : OK
         * data : {"appId":"30d1fca698f449a5853129a43b935637","timestamp":"20180611142304554","nonce":"QswduIyb","sessionId":"e68f26bb70ab41b8a9fa2a37b34ab1bc"}
         * sign : 1qqqqwwwwwwwwwwwwww
         */

        private String code;
        private String msg;
        private DataBean data;
        private String sign;

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

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public static class DataBean {
            /**
             * appId : 30d1fca698f449a5853129a43b935637
             * timestamp : 20180611142304554
             * nonce : QswduIyb
             * sessionId : e68f26bb70ab41b8a9fa2a37b34ab1bc
             */

            private String appId;
            private String timestamp;
            private String nonce;
            private String sessionId;

            public String getAppId() {
                return appId;
            }

            public void setAppId(String appId) {
                this.appId = appId;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getNonce() {
                return nonce;
            }

            public void setNonce(String nonce) {
                this.nonce = nonce;
            }

            public String getSessionId() {
                return sessionId;
            }

            public void setSessionId(String sessionId) {
                this.sessionId = sessionId;
            }
        }
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }
}
