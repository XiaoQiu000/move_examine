package com.qiu.move_examine.repertory.webservice.response;

import com.qiu.move_examine.common.bean.ErrorBean;

import java.util.List;

/**
 * @author Mr.Qiu
 */
public class QueryResponse {


    /**
     * jsonrpc : 2.0
     * id : 1
     * result : {"code":"1","msg":"OK","data":[{"sourceId":"DS-01","fieldValues":[{"field":"name","value":"小明","isCode":0,"codeValue":""},{"field":"age","value":"20","isCode":0,"codeValue":""},{"field":"sex","value":"男","isCode":1,"codeValue":"2"},{"field":"phone","value":"15501011234","isCode":0,"codeValue":""}]}],"page":{"pageSize":10,"pageNo":1,"total":1},"sign":"1qqqqwwwwwwwwwwwwww"}
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
         * data : [{"sourceId":"DS-01","fieldValues":[{"field":"name","value":"小明","isCode":0,"codeValue":""},{"field":"age","value":"20","isCode":0,"codeValue":""},{"field":"sex","value":"男","isCode":1,"codeValue":"2"},{"field":"phone","value":"15501011234","isCode":0,"codeValue":""}]}]
         * page : {"pageSize":10,"pageNo":1,"total":1}
         * sign : 1qqqqwwwwwwwwwwwwww
         */

        private String code;
        private String msg;
        private PageBean page;
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

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
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

        public static class PageBean {
            /**
             * pageSize : 10
             * pageNo : 1
             * total : 1
             */

            private int pageSize;
            private int pageNo;
            private int total;

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

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class DataBean {
            /**
             * sourceId : DS-01
             * fieldValues : [{"field":"name","value":"小明","isCode":0,"codeValue":""},{"field":"age","value":"20","isCode":0,"codeValue":""},{"field":"sex","value":"男","isCode":1,"codeValue":"2"},{"field":"phone","value":"15501011234","isCode":0,"codeValue":""}]
             */

            private String sourceId;
            private List<FieldValuesBean> fieldValues;

            public String getSourceId() {
                return sourceId;
            }

            public void setSourceId(String sourceId) {
                this.sourceId = sourceId;
            }

            public List<FieldValuesBean> getFieldValues() {
                return fieldValues;
            }

            public void setFieldValues(List<FieldValuesBean> fieldValues) {
                this.fieldValues = fieldValues;
            }

            public static class FieldValuesBean {
                /**
                 * field : name
                 * value : 小明
                 * isCode : 0
                 * codeValue :
                 */

                private String field;
                private String value;
                private int isCode;
                private String codeValue;

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

                public int getIsCode() {
                    return isCode;
                }

                public void setIsCode(int isCode) {
                    this.isCode = isCode;
                }

                public String getCodeValue() {
                    return codeValue;
                }

                public void setCodeValue(String codeValue) {
                    this.codeValue = codeValue;
                }
            }
        }
    }
}
