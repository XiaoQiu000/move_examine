package com.qiu.move_examine.repertory.webservice.request;

/**
 * @author Mr.Qiu
 */
public class QueryRequest {

    /**
     * jsonrpc : 2.0
     * id : 1
     * method : query
     * params : {"data":{"version":"20180523","sessionId":"e68f26bb70ab41b8a9fa2a37b34ab1bc","userInfo":{"userId":"001234","userName":"张三","userDeptNo":"440000000000","sn":"39a22c9d353f4ea8bbf5a8703799f400","sfzh":"4111231234566789741256893","extAttr":{"ip":"192.168.15.12","port":"80"}},"source":{"sourceId":"DS-01,DS-02"},"dataObjId":"czrk","condition":"name = '李四' and age < 30","fields":"name,age,sex,phone","orderBy":"name desc, age desc","page":{"pageSize":10,"pageNo":1}},"sign":"1qqqqwwwwwwwwwwwwww"}
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
         * data : {"version":"20180523","sessionId":"e68f26bb70ab41b8a9fa2a37b34ab1bc","userInfo":{"userId":"001234","userName":"张三","userDeptNo":"440000000000","sn":"39a22c9d353f4ea8bbf5a8703799f400","sfzh":"4111231234566789741256893","extAttr":{"ip":"192.168.15.12","port":"80"}},"source":{"sourceId":"DS-01,DS-02"},"dataObjId":"czrk","condition":"name = '李四' and age < 30","fields":"name,age,sex,phone","orderBy":"name desc, age desc","page":{"pageSize":10,"pageNo":1}}
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
             * sessionId : e68f26bb70ab41b8a9fa2a37b34ab1bc
             * userInfo : {"userId":"001234","userName":"张三","userDeptNo":"440000000000","sn":"39a22c9d353f4ea8bbf5a8703799f400","sfzh":"4111231234566789741256893","extAttr":{"ip":"192.168.15.12","port":"80"}}
             * source : {"sourceId":"DS-01,DS-02"}
             * dataObjId : czrk
             * condition : name = '李四' and age < 30
             * fields : name,age,sex,phone
             * orderBy : name desc, age desc
             * page : {"pageSize":10,"pageNo":1}
             */

            private String version;
            private String sessionId;
            private UserInfoBean userInfo;
            private SourceBean source;
            private String dataObjId;
            private String condition;
            private String fields;
            private String orderBy;
            private PageBean page;

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

            public SourceBean getSource() {
                return source;
            }

            public void setSource(SourceBean source) {
                this.source = source;
            }

            public String getDataObjId() {
                return dataObjId;
            }

            public void setDataObjId(String dataObjId) {
                this.dataObjId = dataObjId;
            }

            public String getCondition() {
                return condition;
            }

            public void setCondition(String condition) {
                this.condition = condition;
            }

            public String getFields() {
                return fields;
            }

            public void setFields(String fields) {
                this.fields = fields;
            }

            public String getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(String orderBy) {
                this.orderBy = orderBy;
            }

            public PageBean getPage() {
                return page;
            }

            public void setPage(PageBean page) {
                this.page = page;
            }

            public static class UserInfoBean {
                /**
                 * userId : 001234
                 * userName : 张三
                 * userDeptNo : 440000000000
                 * sn : 39a22c9d353f4ea8bbf5a8703799f400
                 * sfzh : 4111231234566789741256893
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
                }
            }

            public static class SourceBean {
                /**
                 * sourceId : DS-01,DS-02
                 */

                private String sourceId;

                public String getSourceId() {
                    return sourceId;
                }

                public void setSourceId(String sourceId) {
                    this.sourceId = sourceId;
                }
            }

            public static class PageBean {
                /**
                 * pageSize : 10
                 * pageNo : 1
                 */

                private int pageSize;
                private int pageNo;

                public int getPageSize() {
                    return pageSize;
                }

                public void setPageSize(int pageSize) {
                    this.pageSize = pageSize;
                }

                public int getPageNo() {
                    return pageNo;
                }

                public void setPageNo(int pageNo) {
                    this.pageNo = pageNo;
                }
            }
        }
    }
}
