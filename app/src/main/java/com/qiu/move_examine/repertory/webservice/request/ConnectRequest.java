package com.qiu.move_examine.repertory.webservice.request;

/**
 * @author Mr.Qiu
 * 建立连接Request
 */
public class ConnectRequest {
    /**
     * jsonrpc : 2.0
     * id : 1
     * method : connect
     * params : {"data":{"version":"20180523","appId":"08e955d0e030499ba77dc0639a0bb13a","timestamp":"20180611142246134","nonce":"vMksKOwd"},"sign":"1qqqqwwwwwwwwwwwwww"}
     */

    private String jsonrpc;
    private String id;
    private String method;
    private ParamsBean params;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {
        /**
         * data : {"version":"20180523","appId":"08e955d0e030499ba77dc0639a0bb13a","timestamp":"20180611142246134","nonce":"vMksKOwd"}
         * sign : 1qqqqwwwwwwwwwwwwww
         */

        private DataBean data;
        private String sign;

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
             * version : 20180523
             * appId : 08e955d0e030499ba77dc0639a0bb13a
             * timestamp : 20180611142246134
             * nonce : vMksKOwd
             */

            private String version;
            private String appId;
            private String timestamp;
            private String nonce;

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

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
        }
    }
}
