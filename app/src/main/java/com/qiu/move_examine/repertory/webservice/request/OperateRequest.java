package com.qiu.move_examine.repertory.webservice.request;

import java.util.List;

public class OperateRequest {
    /**
     * jsonrpc : 2.0
     * id : 1
     * method : operate
     * params : {"data":{"version":"20180523","sessionId":"e68f26bb70ab41b8a9fa2a37b34ab1bc","userInfo":{"userId":"001234","userName":"张三","userDeptNo":"440000000000","sn":"39a22c9d353f4ea8bbf5a8703799f400","sfzh":"411123456789741258963","extAttr":{"ip":"192.168.15.12","port":"80"}},"transaction":1,"operations":[{"operationType":1,"operationId":"cd2a138e502e4ae5ba5dfb5a9af9df61","sourceId":"DS-01","dataObjId":"czrk","condition":null,"data":[{"fieldValues":[{"field":"name","value":"小花"},{"field":"age","value":"25"},{"field":"sex","value":"2"},{"field":"phone","value":"15501011001"}]}]},{"operationType":2,"operationId":"8148f9d3731d4cbe9cecff48bee319df","sourceId":"DS-01","dataObjId":"czrk","condition":"name='小军'","data":[{"fieldValues":[{"field":"age","value":"25"},{"field":"sex","value":"2"},{"field":"phone","value":"15501011002"}]}]},{"operationType":3,"operationId":"d407b887a9fd4ee79253e1dd2d7dbd9e","sourceId":"DS-01","dataObjId":"czrk","condition":"name='小明'","data":[]}]},"sign":"1qqqqwwwwwwwwwwwwww"}
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
         * data : {"version":"20180523","sessionId":"e68f26bb70ab41b8a9fa2a37b34ab1bc","userInfo":{"userId":"001234","userName":"张三","userDeptNo":"440000000000","sn":"39a22c9d353f4ea8bbf5a8703799f400","sfzh":"411123456789741258963","extAttr":{"ip":"192.168.15.12","port":"80"}},"transaction":1,"operations":[{"operationType":1,"operationId":"cd2a138e502e4ae5ba5dfb5a9af9df61","sourceId":"DS-01","dataObjId":"czrk","condition":null,"data":[{"fieldValues":[{"field":"name","value":"小花"},{"field":"age","value":"25"},{"field":"sex","value":"2"},{"field":"phone","value":"15501011001"}]}]},{"operationType":2,"operationId":"8148f9d3731d4cbe9cecff48bee319df","sourceId":"DS-01","dataObjId":"czrk","condition":"name='小军'","data":[{"fieldValues":[{"field":"age","value":"25"},{"field":"sex","value":"2"},{"field":"phone","value":"15501011002"}]}]},{"operationType":3,"operationId":"d407b887a9fd4ee79253e1dd2d7dbd9e","sourceId":"DS-01","dataObjId":"czrk","condition":"name='小明'","data":[]}]}
         * sign : 1qqqqwwwwwwwwwwwwww
         */

        private DataBeanX data;
        private String sign;

        public DataBeanX getData() {
            return data;
        }

        public void setData(DataBeanX data) {
            this.data = data;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public static class DataBeanX {
            /**
             * version : 20180523
             * sessionId : e68f26bb70ab41b8a9fa2a37b34ab1bc
             * userInfo : {"userId":"001234","userName":"张三","userDeptNo":"440000000000","sn":"39a22c9d353f4ea8bbf5a8703799f400","sfzh":"411123456789741258963","extAttr":{"ip":"192.168.15.12","port":"80"}}
             * transaction : 1
             * operations : [{"operationType":1,"operationId":"cd2a138e502e4ae5ba5dfb5a9af9df61","sourceId":"DS-01","dataObjId":"czrk","condition":null,"data":[{"fieldValues":[{"field":"name","value":"小花"},{"field":"age","value":"25"},{"field":"sex","value":"2"},{"field":"phone","value":"15501011001"}]}]},{"operationType":2,"operationId":"8148f9d3731d4cbe9cecff48bee319df","sourceId":"DS-01","dataObjId":"czrk","condition":"name='小军'","data":[{"fieldValues":[{"field":"age","value":"25"},{"field":"sex","value":"2"},{"field":"phone","value":"15501011002"}]}]},{"operationType":3,"operationId":"d407b887a9fd4ee79253e1dd2d7dbd9e","sourceId":"DS-01","dataObjId":"czrk","condition":"name='小明'","data":[]}]
             */

            private String version;
            private String sessionId;
            private UserInfoBean userInfo;
            private int transaction;
            private List<OperationsBean> operations;

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getSessionId() {
                return sessionId;
            }

            public void setSessionId(String sessionId) {
                this.sessionId = sessionId;
            }

            public UserInfoBean getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfoBean userInfo) {
                this.userInfo = userInfo;
            }

            public int getTransaction() {
                return transaction;
            }

            public void setTransaction(int transaction) {
                this.transaction = transaction;
            }

            public List<OperationsBean> getOperations() {
                return operations;
            }

            public void setOperations(List<OperationsBean> operations) {
                this.operations = operations;
            }

            public static class UserInfoBean {
                /**
                 * userId : 001234
                 * userName : 张三
                 * userDeptNo : 440000000000
                 * sn : 39a22c9d353f4ea8bbf5a8703799f400
                 * sfzh : 411123456789741258963
                 * extAttr : {"ip":"192.168.15.12","port":"80"}
                 */

                private String userId;
                private String userName;
                private String userDeptNo;
                private String sn;
                private String sfzh;
                private ExtAttrBean extAttr;

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getUserDeptNo() {
                    return userDeptNo;
                }

                public void setUserDeptNo(String userDeptNo) {
                    this.userDeptNo = userDeptNo;
                }

                public String getSn() {
                    return sn;
                }

                public void setSn(String sn) {
                    this.sn = sn;
                }

                public String getSfzh() {
                    return sfzh;
                }

                public void setSfzh(String sfzh) {
                    this.sfzh = sfzh;
                }

                public ExtAttrBean getExtAttr() {
                    return extAttr;
                }

                public void setExtAttr(ExtAttrBean extAttr) {
                    this.extAttr = extAttr;
                }

                public static class ExtAttrBean {
                    /**
                     * ip : 192.168.15.12
                     * port : 80
                     */

                    private String ip;
                    private String port;

                    public String getIp() {
                        return ip;
                    }

                    public void setIp(String ip) {
                        this.ip = ip;
                    }

                    public String getPort() {
                        return port;
                    }

                    public void setPort(String port) {
                        this.port = port;
                    }
                }
            }

            public static class OperationsBean {
                /**
                 * operationType : 1
                 * operationId : cd2a138e502e4ae5ba5dfb5a9af9df61
                 * sourceId : DS-01
                 * dataObjId : czrk
                 * condition : null
                 * data : [{"fieldValues":[{"field":"name","value":"小花"},{"field":"age","value":"25"},{"field":"sex","value":"2"},{"field":"phone","value":"15501011001"}]}]
                 */

                private int operationType;
                private String operationId;
                private String sourceId;
                private String dataObjId;
                private Object condition;
                private List<DataBean> data;

                public int getOperationType() {
                    return operationType;
                }

                public void setOperationType(int operationType) {
                    this.operationType = operationType;
                }

                public String getOperationId() {
                    return operationId;
                }

                public void setOperationId(String operationId) {
                    this.operationId = operationId;
                }

                public String getSourceId() {
                    return sourceId;
                }

                public void setSourceId(String sourceId) {
                    this.sourceId = sourceId;
                }

                public String getDataObjId() {
                    return dataObjId;
                }

                public void setDataObjId(String dataObjId) {
                    this.dataObjId = dataObjId;
                }

                public Object getCondition() {
                    return condition;
                }

                public void setCondition(Object condition) {
                    this.condition = condition;
                }

                public List<DataBean> getData() {
                    return data;
                }

                public void setData(List<DataBean> data) {
                    this.data = data;
                }

                public static class DataBean {
                    private List<FieldValuesBean> fieldValues;

                    public List<FieldValuesBean> getFieldValues() {
                        return fieldValues;
                    }

                    public void setFieldValues(List<FieldValuesBean> fieldValues) {
                        this.fieldValues = fieldValues;
                    }

                    public static class FieldValuesBean {
                        /**
                         * field : name
                         * value : 小花
                         */

                        private String field;
                        private String value;

                        public String getField() {
                            return field;
                        }

                        public void setField(String field) {
                            this.field = field;
                        }

                        public String getValue() {
                            return value;
                        }

                        public void setValue(String value) {
                            this.value = value;
                        }
                    }
                }
            }
        }
    }
}
