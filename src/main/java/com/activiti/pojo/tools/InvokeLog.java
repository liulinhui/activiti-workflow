package com.activiti.pojo.tools;

import java.io.Serializable;

public class InvokeLog implements Serializable {
    private static final long serialVersionUID = 2120869894112984147L;
    private long uuid;
    private long invokeTime;
    private String params;
    private String result;
    private String email;
    private String requestUri;

    public InvokeLog() {
    }

    public InvokeLog(long uuid, String params, String result, String email, String requestUri) {
        this.uuid = uuid;
        this.params = params;
        this.result = result;
        this.email = email;
        this.requestUri = requestUri;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public long getInvokeTime() {
        return invokeTime;
    }

    public void setInvokeTime(long invokeTime) {
        this.invokeTime = invokeTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    @Override
    public String toString() {
        return "InvokeLog{" +
                "uuid='" + uuid + '\'' +
                ", invokeTime=" + invokeTime +
                ", params='" + params + '\'' +
                ", result='" + result + '\'' +
                ", email='" + email + '\'' +
                ", requestUri='" + requestUri + '\'' +
                '}';
    }
}
